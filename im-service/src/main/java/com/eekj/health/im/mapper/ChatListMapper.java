package com.eekj.health.im.mapper;

import com.eekj.health.im.entity.ChatListEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChatListMapper extends Mapper<ChatListEntity> {
    /**
     * 批量添加聊天列表
     */
   // int addChatLists(List<ChatListEntity> list);

    /**
     * 修改列表
     * @param chatListEntity
     * @return
     */
    int updateChatLists(ChatListEntity chatListEntity);

    /**
     * 通过条件查询列表
     * @param chatListEntity 查询条件
     * @return
     */
    List<ChatListEntity> selectChatList(ChatListEntity chatListEntity);

}