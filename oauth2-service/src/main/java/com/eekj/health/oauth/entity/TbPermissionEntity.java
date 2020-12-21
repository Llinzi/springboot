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
    * 权限表
    */
@ApiModel(value="com-eekj-health-oauth-entity-TbPermissionEntity")
@Getter
@Setter
@ToString
@Table(name = "tb_permission")
public class TbPermissionEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value="父权限")
    private Long parentId;

    /**
     * 权限名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="权限名称")
    private String name;

    /**
     * 权限英文名称
     */
    @Column(name = "enname")
    @ApiModelProperty(value="权限英文名称")
    private String enname;

    /**
     * 授权路径
     */
    @Column(name = "url")
    @ApiModelProperty(value="授权路径")
    private String url;

    /**
     * 备注
     */
    @Column(name = "description")
    @ApiModelProperty(value="备注")
    private String description;

    @Column(name = "created")
    @ApiModelProperty(value="")
    private Date created;

    @Column(name = "updated")
    @ApiModelProperty(value="")
    private Date updated;

    private static final long serialVersionUID = 1L;
}