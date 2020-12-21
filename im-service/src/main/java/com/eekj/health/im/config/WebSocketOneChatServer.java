package com.eekj.health.im.config;

import com.alibaba.fastjson.JSON;
import com.eekj.health.im.entity.ChatContentEntity;
import com.eekj.health.im.entity.ChatListEntity;
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
@ServerEndpoint("/chat/{senderId}")
public class WebSocketOneChatServer {

    private static Map<Integer, Session> sessionMap = new ConcurrentHashMap<Integer, Session>();

    private static ChatContentService chatContentService;

    @Resource
    public void setChatContentService(ChatContentService chatContentService) {
        WebSocketOneChatServer.chatContentService = chatContentService;
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
     * @param senderId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("senderId") Integer senderId){
        if (sessionMap.get(senderId) == null){
            sessionMap.put(senderId,session);
            System.out.println(senderId + "上线");
        }
    }

    /**
     *
     * @param jsonStr 发送的内容（websocket不能发送对象，只能发送字符串）
     */
    @OnMessage
    public void onMessage(String jsonStr){
        sendMessage(jsonStr);
        //把 json 转换为对象
        ChatContentEntity chatContentEntity = JSON.parseObject(jsonStr,ChatContentEntity.class);
        chatContentEntity.setMessageState(0);
        chatContentEntity.setSendTime(new Date());
        List list1 = selectChatList1(chatContentEntity);
        List list2 = selectChatList2(chatContentEntity);
        if (list1.size() <= 0 && list2.size() <= 0){
            addChatList1(chatContentEntity);
            addChatList2(chatContentEntity);
        }else if (list1.size() <= 0 && list2.size() >= 0){
            addChatList1(chatContentEntity);
            updateChatLists2(chatContentEntity);
        }else if (list1.size() > 0 && list2.size() <= 0){
            addChatList2(chatContentEntity);
            updateChatLists1(chatContentEntity);
        }else {
            updateChatLists1(chatContentEntity);
            updateChatLists2(chatContentEntity);
        }
        addChatContent(chatContentEntity);
    }

    @OnClose
    public void onClose(Session session,@PathParam("senderId") Integer senderId){
        sessionMap.remove(senderId);
        System.out.println(senderId + "离线");
    }

    /**
     * 把消息发送给指定的所有用户
     * @param message 发送的消息
     */
    private void sendMessage(String message){
        ChatContentEntity chatContentEntity = JSON.parseObject(message,ChatContentEntity.class);
        try {
            Session session = sessionMap.get(chatContentEntity.getReceiveId());
            if (session == null){
                return;
            }else {
                session.getBasicRemote().sendText(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询列表1
     * @param chatContentEntity
     * @return
     */
    private List selectChatList1(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity = new ChatListEntity();
        chatListEntity.setMasterId(chatContentEntity.getSenderId());
        chatListEntity.setOtherId(chatContentEntity.getReceiveId());
        try {
            List<ChatListEntity> list = chatContentService.selectChatList(chatListEntity);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表2
     * @param chatContentEntity
     * @return
     */
    private List selectChatList2(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity = new ChatListEntity();
        chatListEntity.setMasterId(chatContentEntity.getReceiveId());
        chatListEntity.setOtherId(chatContentEntity.getSenderId());
        try {
            List<ChatListEntity> list = chatContentService.selectChatList(chatListEntity);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加消息列表1
     * @param chatContentEntity
     * @return
     */
    private int addChatList1(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity1 = new ChatListEntity();
        chatListEntity1.setMasterId(chatContentEntity.getSenderId());
        chatListEntity1.setOtherId(chatContentEntity.getReceiveId());
        chatListEntity1.setOtherName(chatContentEntity.getReceiveName());
        chatListEntity1.setContent(chatContentEntity.getContent());
        chatListEntity1.setMessageType(chatContentEntity.getMessageType());
        chatListEntity1.setCreateTime(new Date());
        try {
            int i = chatContentService.addChatLists(chatListEntity1);
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 添加消息列表2
     * @param chatContentEntity
     * @return
     */
    private int addChatList2(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity2 = new ChatListEntity();
        chatListEntity2.setMasterId(chatContentEntity.getReceiveId());
        chatListEntity2.setOtherId(chatContentEntity.getSenderId());
        chatListEntity2.setOtherName(chatContentEntity.getSenderName());
        chatListEntity2.setContent(chatContentEntity.getContent());
        chatListEntity2.setMessageType(chatContentEntity.getMessageType());
        chatListEntity2.setCreateTime(new Date());
        try {
            int i = chatContentService.addChatLists(chatListEntity2);
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
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

    /**
     * 修改消息
     * @param chatContentEntity
     * @return
     */
    private int updateChatLists1(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity1 = new ChatListEntity();
        chatListEntity1.setMasterId(chatContentEntity.getSenderId());
        chatListEntity1.setOtherId(chatContentEntity.getReceiveId());
        chatListEntity1.setOtherName(chatContentEntity.getReceiveName());
        chatListEntity1.setContent(chatContentEntity.getContent());
        chatListEntity1.setMessageType(chatContentEntity.getMessageType());
        chatListEntity1.setUpdateTime(new Date());
        try {
            chatContentService.updateChatLists(chatListEntity1);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private int updateChatLists2(ChatContentEntity chatContentEntity){
        ChatListEntity chatListEntity2 = new ChatListEntity();
        chatListEntity2.setMasterId(chatContentEntity.getReceiveId());
        chatListEntity2.setOtherId(chatContentEntity.getSenderId());
        chatListEntity2.setOtherName(chatContentEntity.getSenderName());
        chatListEntity2.setContent(chatContentEntity.getContent());
        chatListEntity2.setMessageType(chatContentEntity.getMessageType());
        chatListEntity2.setUpdateTime(new Date());
        try {
            chatContentService.updateChatLists(chatListEntity2);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
