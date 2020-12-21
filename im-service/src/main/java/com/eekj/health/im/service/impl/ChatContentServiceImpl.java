package com.eekj.health.im.service.impl;

import com.eekj.health.im.entity.ChatContentEntity;
import com.eekj.health.im.entity.ChatListEntity;
import com.eekj.health.im.mapper.ChatListMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.eekj.health.im.mapper.ChatContentMapper;
import com.eekj.health.im.service.ChatContentService;

import java.util.List;

@Service
public class ChatContentServiceImpl implements ChatContentService{

    @Resource
    private ChatContentMapper chatContentMapper;

    @Resource
    private ChatListMapper chatListMapper;


    public int addChatContent(ChatContentEntity chatContentEntity) {
        return chatContentMapper.insertSelective(chatContentEntity);
    }

    public int addChatContentList(List<ChatContentEntity> list) {
        return chatContentMapper.addChatContentList(list);
    }

    public List<ChatContentEntity> selectChatContentList(int senderId, int receiveId) {
        return chatContentMapper.selectChatContentList(senderId,receiveId);
    }

    public List<ChatContentEntity> selectUnreadContentList(int userId) {
        return chatContentMapper.selectUnreadContentList(userId);
    }

    public int updateMessageState(List<Integer> list) {
        return chatContentMapper.updateMessageState(list);
    }

    public int addChatLists(ChatListEntity chatListEntity) {
        return chatListMapper.insertSelective(chatListEntity);
    }

    public List<ChatListEntity> selectChatList(ChatListEntity chatListEntity) {
        return chatListMapper.selectChatList(chatListEntity);
    }

    public int updateChatLists(ChatListEntity chatListEntity) {
        return chatListMapper.updateChatLists(chatListEntity);
    }

    public int deleteChatLitById(Integer listId) {
        return chatListMapper.deleteByPrimaryKey(listId);
    }
}
