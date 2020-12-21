package com.eekj.health.im.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 聊天内容表
    */
@ApiModel(value="com-eekj-health-im-entity-ChatContentEntity")
@Getter
@Setter
@ToString
@Table(name = "chat_content")
public class ChatContentEntity implements Serializable {
    /**
     * 聊天类容 id
     */
    @Id
    @Column(name = "chat_id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="聊天类容 id")
    private Integer chatId;

    /**
     * 聊天内容
     */
    @Column(name = "content")
    @ApiModelProperty(value="聊天内容")
    private String content;


    /**
     * 发送者 id
     */
    @Column(name = "sender_id")
    @ApiModelProperty(value="发送者 id")
    private Integer senderId;

    /**
     * 发送者名称
     */
    @Column(name = "sender_name")
    @ApiModelProperty(value="发送者名称")
    private String senderName;

    /**
     * 发送者 id
     */
    @Column(name = "receive_id")
    @ApiModelProperty(value="发送者 id")
    private Integer receiveId;

    /**
     * 发送者名称
     */
    @Column(name = "receive_name")
    @ApiModelProperty(value="发送者名称")
    private String receiveName;

    /**
     * 发送的时间
     */
    @Column(name = "send_time")
    @ApiModelProperty(value="发送的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 消息状态（0：未读，1：已读）
     */
    @Column(name = "message_state")
    @ApiModelProperty(value="消息状态（0：未读，1：已读）")
    private Integer messageState;

    /**
     * 房间号
     */
    @Column(name = "room_id")
    @ApiModelProperty(value="房间号")
    private String roomId;

    /**
     * 消息类型（1：文字，2：图片）
     */
    @Column(name = "message_type")
    @ApiModelProperty(value=" 消息类型（1：文字，2：图片）")
    private Integer messageType;


    private static final long serialVersionUID = 1L;
}