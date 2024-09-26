package com.cellosquare.adminapp.admin.quote.entity;

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
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Getter
@Setter
@TableName("mk_quote_return_reason")
public class QuoteReturnReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("type")
    private Integer type;
    @TableField("ordb")
    private String ordb;

    @TableField("reason")
    private String reason;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("use_yn")
    private String useYn;

}
