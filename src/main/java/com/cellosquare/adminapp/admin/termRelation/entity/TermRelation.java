package com.cellosquare.adminapp.admin.termRelation.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2023-03-62 16:35:16
 */
@Getter
@Setter
@TableName("mk_term_relation")
public class TermRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 术语id
     */
    @TableField("term_id")
    private Long termId;

    @TableField("article_id")
    private Long articleId;

    @TableField("article_type")
    private String articleType;

    @TableField(value ="ins_dtm",fill = FieldFill.INSERT)
    private Timestamp insDtm;
}
