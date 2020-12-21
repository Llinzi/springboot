package com.eekj.health.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value="com-eekj-health-oauth-entity-TbContentEntity")
@Getter
@Setter
@ToString
@Table(name = "tb_content")
public class TbContentEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 内容类目ID
     */
    @Column(name = "category_id")
    @ApiModelProperty(value="内容类目ID")
    private Long categoryId;

    /**
     * 内容标题
     */
    @Column(name = "title")
    @ApiModelProperty(value="内容标题")
    private String title;

    /**
     * 子标题
     */
    @Column(name = "sub_title")
    @ApiModelProperty(value="子标题")
    private String subTitle;

    /**
     * 标题描述
     */
    @Column(name = "title_desc")
    @ApiModelProperty(value="标题描述")
    private String titleDesc;

    /**
     * 链接
     */
    @Column(name = "url")
    @ApiModelProperty(value="链接")
    private String url;

    /**
     * 图片绝对路径
     */
    @Column(name = "pic")
    @ApiModelProperty(value="图片绝对路径")
    private String pic;

    /**
     * 图片2
     */
    @Column(name = "pic2")
    @ApiModelProperty(value="图片2")
    private String pic2;

    /**
     * 内容
     */
    @Column(name = "content")
    @ApiModelProperty(value="内容")
    private String content;

    @Column(name = "created")
    @ApiModelProperty(value="")
    private Date created;

    @Column(name = "updated")
    @ApiModelProperty(value="")
    private Date updated;

    private static final long serialVersionUID = 1L;
}