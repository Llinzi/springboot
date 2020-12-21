package com.eekj.health.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 用户表
    */
@ApiModel(value="com-eekj-health-oauth-entity-TbUserEntity")
@Getter
@Setter
@ToString
@Table(name = "tb_user")
public class TbUserEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码，加密存储
     */
    @Column(name = "`password`")
    @ApiModelProperty(value="密码，加密存储")
    private String password;

    /**
     * 注册手机号
     */
    @Column(name = "phone")
    @ApiModelProperty(value="注册手机号")
    private String phone;

    /**
     * 注册邮箱
     */
    @Column(name = "email")
    @ApiModelProperty(value="注册邮箱")
    private String email;

    @Column(name = "created")
    @ApiModelProperty(value="")
    private Date created;

    @Column(name = "updated")
    @ApiModelProperty(value="")
    private Date updated;

    private static final long serialVersionUID = 1L;
}