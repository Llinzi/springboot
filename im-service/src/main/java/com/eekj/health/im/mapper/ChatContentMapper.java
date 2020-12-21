package com.eekj.health.im.mapper;

import com.eekj.health.im.entity.ChatContentEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChatContentMapper extends Mapper<ChatContentEntity> {

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

}