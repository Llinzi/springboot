package com.eekj.health.im.config;



import com.alibaba.fastjson.JSON;
import com.eekj.health.im.common.RedisUtils;
import com.eekj.health.im.entity.ChatContentEntity;
import com.eekj.health.im.model.MessageModel;
import com.eekj.health.im.service.ChatContentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{roomId}/{phone}")
public class WebSocketChatServer {

    /**
     * 用 map 来存储一个房间里，所有的用户,Key为房间名，value为在这个房间的用户的集合
     */
    private static Map<String, Set<Session>> rooms = new ConcurrentHashMap<String, Set<Session>>();

    /**
     * 用来存储一个房间内的 用户
     */
    private static Map<String, Set<String>> phoneMap = new ConcurrentHashMap<String, Set<String>>();


    private static RedisUtils redisUtils;

    private static ChatContentService chatContentService;

    @Resource
    public void setChatContentService(ChatContentService chatContentService) {
        WebSocketChatServer.chatContentService = chatContentService;
    }

    @Resource
    public void setRedisUtils(RedisUtils redisUtils) {
        WebSocketChatServer.redisUtils = redisUtils;
    }

    /**
     * 当通讯发生异常，打印错误日志
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
    }

    /**
     * 当连接成功时
     * @param session
     * @param roomId
     * @param phone
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId,@PathParam("phone") String phone){
        if (!rooms.containsKey(roomId)){
            Set<Session> setSession = new HashSet<Session>();
            setSession.add(session);
            rooms.put(roomId,setSession);

            Set<String> setPhone = new HashSet<String>();
            setPhone.add(phone);
            phoneMap.put(roomId,setPhone);

        }else {
            rooms.get(roomId).add(session);
            phoneMap.get(roomId).add(phone);
        }
        //JSON.toJSONString(rooms);
        sendMessage("系统通知:"+phone+"加入聊天",roomId);
    }

    /**
     *
     * @param session
     * @param jsonStr 发送的内容（websocket不能发送对象，只能发送字符串）
     * @param roomId 房间号
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr, @PathParam("roomId") String roomId){
        sendMessage(jsonStr,roomId);
        //把 json 转换为对象
        ChatContentEntity chatContentEntity = JSON.parseObject(jsonStr,ChatContentEntity.class);
        //把消息存入MySQL
        addChatContent(chatContentEntity);
        //把消息存入redis
        //redisUtils.lPush(roomId,chatContentEntity);
        //redisUtils.expire(roomId,60); //设置过期时间

    }

    @OnClose
    public void onClose(Session session,@PathParam("phone") String phone,@PathParam("roomId") String roomId){
        rooms.get(roomId).remove(session);
        phoneMap.get(roomId).remove(phone);
        sendMessage("系统通知:"+phone+"退出聊天",roomId);
    }

    /**
     * 发送消息（）把消息发送给房间内的所有用户
     * @param message 发送的消息
     * @param roomId  房间号
     */
    private static void sendMessage(String message,String roomId){
        for (Session session : rooms.get(roomId)){
            try {
                session.getBasicRemote().sendText(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取房间内的人数
     * @param roomId 房间号
     * @return
     */
    private List<String> phoneList(String roomId){
        List<String> list = new ArrayList<String>();
        for (String str : phoneMap.get(roomId)){
            list.add(str);
        }
        return list;
    }

    /**
     * 添加消息
     * @param chatContentEntity
     * @return
     */
    private int addChatContent(ChatContentEntity chatContentEntity){
        try {
            int i = chatContentService.addChatContent(chatContentEntity);
            if (i > 0){
                return i;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
