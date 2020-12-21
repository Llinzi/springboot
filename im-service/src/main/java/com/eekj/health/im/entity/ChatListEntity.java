package com.eekj.health.im.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value="com-eekj-health-im-entity-ChatListEntity")
@Getter
@Setter
@ToString
@Table(name = "chat_list")
public class ChatListEntity implements Serializable {
    /**
     * 聊天列表 id
     */
    @Id
    @Column(name = "list_id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="聊天列表 id")
    private Integer listId;

    @Column(name = "master_id")
    @ApiModelProperty(value="列表拥有者id")
    private Integer masterId;

    @Column(name = "other_id")
    @ApiModelProperty(value="")
    private Integer otherId;

    @Column(name = "other_name")
    @ApiModelProperty(value="")
    private String otherName;

    @Column(name = "content")
    @ApiModelProperty(value="聊天内容")
    private String content;

    @Column(name = "message_type")
    @ApiModelProperty(value="消息类型（1：文字，2图片）")
    private Integer messageType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}