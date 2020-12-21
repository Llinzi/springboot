package com.eekj.health.im.service;

import com.eekj.health.im.entity.ChatContentEntity;
import com.eekj.health.im.entity.ChatListEntity;

import java.util.List;

public interface ChatContentService{


    /**
     * 添加消息
     * @param chatContentEntity
     * @return
     */
    int addChatContent(ChatContentEntity chatContentEntity);

    /**
     * 批量添加消息
     * @param list
     * @return
     */
    int addChatContentList(List<ChatContentEntity> list);

    /**
     * 查询历史消息
     * @return
     */
    List<ChatContentEntity> selectChatContentList(int senderId,int receiveId);

    /**
     * 查询未读消息
     * @return
     */
    List<ChatContentEntity> selectUnreadContentList(int userId);

    /**
     * 修改消息状态
     * @param list 消息 id
     * @return
     */
    int updateMessageState(List<Integer> list);

    /**
     * 添加聊天列表
     */
    int addChatLists(ChatListEntity chatListEntity);

    /**
     * 通过条件查询消息列表
     * @param chatListEntity 查询条件
     * @return
     */
    List<ChatListEntity> selectChatList(ChatListEntity chatListEntity);

    /**
     * 批量更新
     * @param chatListEntity
     * @return
     */
    int updateChatLists(ChatListEntity chatListEntity);

    /**
     * 通过 id 删除 消息列表
     * @param listId
     * @return
     */
    int deleteChatLitById(Integer listId);

}
