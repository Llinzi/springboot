package com.eekj.health.im.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 即时通讯消息发送
 * @author: linzi
 * @create: 2020-07-08 16:55
 **/
@Getter
@Setter
public class MessageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 进入
     */
    public static final String ENTER = "ENTER";

    /**
     * 发言
     */
    public static final String SPEAK = "SPEAK";

    /**
     * 退出
     */
    public static final String QUIT = "QUIT";


    private String messageType;

    /**
     * 发送人手机号
     */
    private String senderPhone;

    /**
     * 发送的消息
     */
    private String message;

    /**
     * 在线人数
     */
    private Integer onlineCount;

    /**
     * 用来存储用户手机号
     */
    private List<String> phoneList;


    public MessageModel(String messageType, String senderPhone, String message, Integer onlineCount) {
        this.messageType = messageType;
        this.senderPhone = senderPhone;
        this.message = message;
        this.onlineCount = onlineCount;
    }

    public MessageModel(String messageType, String senderPhone, String message, Integer onlineCount, List<String> phoneList) {
        this.messageType = messageType;
        this.senderPhone = senderPhone;
        this.message = message;
        this.onlineCount = onlineCount;
        this.phoneList = phoneList;
    }

    /**
     * 聊天消息
     * @return
     */
    public static String jsonStr(String messageType, String senderPhone,String message, Integer onlineCount) {
        return JSON.toJSONString(new MessageModel(messageType, senderPhone, message, onlineCount));
    }

    public static String jsonStr(String messageType, String senderPhone,String message, Integer onlineCount, List<String> phoneList) {
        return JSON.toJSONString(new MessageModel(messageType, senderPhone, message, onlineCount, phoneList));
    }

}
