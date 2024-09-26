package com.cellosquare.adminapp.admin.antistopRelation.entity;

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
 * @since 2023-03-68 09:16:43
 */
@Getter
@Setter
@TableName("mk_antistop_relation")
public class AntistopRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;

    @TableField("term_id")
    private Long termId;

    @TableField("article_id")
    private Long articleId;

    @TableField("article_type")
    private String articleType;
    @TableField(value ="ins_dtm",fill = FieldFill.INSERT)
    private Timestamp insDtm;

}
