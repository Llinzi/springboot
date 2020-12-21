package com.eekj.health.im.controller;

import com.alibaba.fastjson.JSON;
import com.eekj.health.im.common.Result;
import com.eekj.health.im.config.WebSocketOneChatServer;
import com.eekj.health.im.entity.ChatContentEntity;
import com.eekj.health.im.entity.ChatListEntity;
import com.eekj.health.im.service.ChatContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 咨询聊天控制器
 * @author: linzi
 * @create: 2020-07-10 09:17
 **/
@Api(value = "ChatContentController",tags = "咨询聊天控制器")
@RestController
@RequestMapping(value = "/chatContent")
public class ChatContentController {

    @Resource
    private ChatContentService chatContentService;

    @Resource
    private WebSocketOneChatServer webSocketOneChatServer;

    /**
     * 通过id查询指定 用户 专家 的聊天
     * @param senderId
     * @param receiveId
     * @return
     */
    @ApiOperation(value = "通过id查询指定 用户 专家 的聊天", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "senderId",value = "用户 id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "receiveId",value = "专家 id",required = true,paramType = "query",dataType = "int")
    })
    @GetMapping(value = "/selectChatContentList")
    public Result selectChatContentList(@RequestParam Integer senderId,@RequestParam Integer receiveId){
        try {
            List<ChatContentEntity> list = chatContentService.selectChatContentList(senderId, receiveId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("data",list);
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 查询未读消息
     * @param userId 用户 id
     * @return
     */
    @ApiOperation(value = "查询未读的聊天", httpMethod = "GET")
    @ApiImplicitParam(name = "userId",value = "用户 id",required = true,paramType = "query",dataType = "int")
    @GetMapping(value = "/selectUnreadContentList")
    public Result selectUnreadContentList(@RequestParam Integer userId){
        try {
            List<ChatContentEntity> list = chatContentService.selectUnreadContentList(userId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("data",list);
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 查询消息列表
     * @param masterId 列表拥有者
     * @return
     */
    @ApiOperation(value = "查询消息列表",httpMethod = "GET")
    @ApiImplicitParam(name = "masterId",value = "列表拥有者",required = true,paramType = "query",dataType = "int")
    @GetMapping(value = "/selectChatList")
    public Result selectChatList(@RequestParam Integer masterId){
        try {
            ChatListEntity chatListEntity = new ChatListEntity();
            chatListEntity.setMasterId(masterId);
            List<ChatListEntity> list = chatContentService.selectChatList(chatListEntity);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("data",list);
            return Result.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 修改消息状态
     * @param idList
     * @return
     */
    @ApiOperation(value = "修改消息状态",httpMethod = "POST")
    @ApiImplicitParam(name = "idList",value = "消息 id",required = true,paramType = "body",dataType = "int",allowMultiple = true)
    @PostMapping(value = "/updateMessageState")
    public Result updateMessageState(@RequestBody List<Integer> idList){
        try {
            chatContentService.updateMessageState(idList);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 发送消息
     * @param chatContentEntity
     * @return
     */
    @ApiOperation(value = "发送消息",httpMethod = "POST")
    @PostMapping(value = "/sendMessage")
    public Result sendMessage(@RequestBody ChatContentEntity chatContentEntity){
        webSocketOneChatServer.onMessage(JSON.toJSONString(chatContentEntity));
        return Result.ok();
    }

    /**
     * 删除聊天列表
     * @param listId
     * @return
     */
    @ApiOperation(value = "删除聊天列表",httpMethod = "GET")
    @ApiImplicitParam(name = "listId",value = "列表 id",required = true,paramType = "query",dataType = "int")
    @GetMapping(value = "/deleteChatContent")
    public Result deleteChatContent(@RequestParam Integer listId){
        chatContentService.deleteChatLitById(listId);
        return Result.ok();
    }

}
