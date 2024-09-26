package com.cellosquare.adminapp.admin.antistop.entity;

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
 * @since 2023-03-68 09:15:21
 */
@Getter
@Setter
@TableName("mk_antistop")
public class Antistop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id" ,type = IdType.AUTO)
    private Long id;

    @TableField("terminology_name")
    private String terminologyName;

    @TableField("description")
    private String description;

    @TableField("use_yn")
    private String useYn;

    @TableField("srch_cnt")
    private Integer srchCnt;

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

    @TableField("meta_seq_no")
    private Long metaSeqNo;

    /**
     * 是否有效0是1否
     */
    @TableField("enable_flag")
    @TableLogic
    private String enableFlag;
}
