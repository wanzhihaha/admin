package com.cellosquare.adminapp.admin.seoInnerChain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2023-03-66 09:03:29
 */
@Getter
@Setter
@TableName("mk_seo_inner_chain")
public class SeoInnerChain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;

    /**
     * 关键词
     */
    @TableField("key_word")
    private String keyWord;

    /**
     * 链接
     */
    @TableField("link")
    private String link;

    /**
     * 关联数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 文章类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Timestamp startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Timestamp endTime;

    /**
     * 排序
     */
    @TableField("ordb")
    private String ordb;

    @TableField(value ="ins_dtm",fill = FieldFill.INSERT)
    private Timestamp insDtm;

    @TableField(value = "ins_person_id",fill = FieldFill.INSERT)
    private String insPersonId;

    @TableField(value = "upd_dtm",fill = FieldFill.UPDATE)
    private Timestamp updDtm;

    @TableField(value = "upd_person_id",fill = FieldFill.UPDATE)
    private String updPersonId;

    /**
     * 有效否
     */
    @TableField("use_yn")
    private String useYn;


}
