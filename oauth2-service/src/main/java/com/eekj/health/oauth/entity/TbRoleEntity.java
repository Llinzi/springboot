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
    * 角色表
    */
@ApiModel(value="com-eekj-health-oauth-entity-TbRoleEntity")
@Getter
@Setter
@ToString
@Table(name = "tb_role")
public class TbRoleEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 父角色
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value="父角色")
    private Long parentId;

    /**
     * 角色名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="角色名称")
    private String name;

    /**
     * 角色英文名称
     */
    @Column(name = "enname")
    @ApiModelProperty(value="角色英文名称")
    private String enname;

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