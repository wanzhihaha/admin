package com.cellosquare.adminapp.admin.article.vo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2022-12-335 10:57:25
 */
@Getter
@Setter
@TableName("mk_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 文章类型
     */
    @TableField("article_type")
    private String articleType;

    /**
     * 文章标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 文章摘要
     */
    @TableField("article_digest")
    private String articleDigest;

    /**
     * 文章大图url
     */
    @TableField("article_pic_big")
    private String articlePicBig;

    /**
     * 文章辅图url
     */
    @TableField("article_pic_as")
    private String articlePicAs;

    /**
     * 文章缩略图url
     */
    @TableField("article_pic_tb")
    private String articlePicTb;

    /**
     * 文章标签
     */
    @TableField("article_tag")
    private String articleTag;

    /**
     * 是否有效0是1否
     */
    @TableField("enable_flag")
    @TableLogic
    private String enableFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private Timestamp createDate;

    /**
     * 创建人id
     */
    @TableField(value = "create_persion_id",fill = FieldFill.INSERT)
    private String createPersionId;

    /**
     * 修改时间
     */
    @TableField(value = "update_date" ,fill = FieldFill.UPDATE)
    private Timestamp updateDate;

    /**
     * 修改人id
     */
    @TableField(value = "update_persion_id" ,fill = FieldFill.UPDATE)
    private String updatePersionId;

    /**
     * 置顶类型
     */
    @TableField("stick_type")
    private String stickType;

    /**
     * 文章内容
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * meta表id
     */
    @TableField("meta_seq_no")
    private Long metaSeqNo;

    /**
     * 文章大图名称
     */
    @TableField("article_pic_big_name")
    private String articlePicBigName;

    /**
     * 文章辅图名称
     */
    @TableField("article_pic_as_name")
    private String articlePicAsName;

    /**
     * 文章缩略图名称
     */
    @TableField("article_pic_tb_name")
    private String articlePicTbName;

    /**
     * 是否使用
     */
    @TableField("use_yn")
    private String useYn;

    /**
     * 排序
     */
    @TableField("ordb")
    private String ordb;

    /**
     * 浏览量
     */
    @TableField("page_view")
    private Long pageView;

    /**
     * 是否内链
     */
    @TableField("link_yn")
    private Integer linkYn;

    @TableField(exist = false)
    private String AntistopTags;
    @TableField(exist = false)
    private String terminologyTags;
}
