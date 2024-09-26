package com.cellosquare.adminapp.admin.logisticsQa.entity;

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
 * @since 2023-03-67 09:03:09
 */
@Getter
@Setter
@TableName("mk_logistics_qa")
public class LogisticsQa implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("meta_seq_no")
    private Integer metaSeqNo;

    @TableField("name")
    private String name;

    @TableField("summary_info")
    private String summaryInfo;

    @TableField("contents")
    private String contents;

    @TableField("is_nice")
    private Integer isNice;

    @TableField("use_yn")
    private String useYn;

    @TableField("srch_cnt")
    private Integer srchCnt;

    @TableField("list_img_path")
    private String listImgPath;

    @TableField("list_img_org_file_nm")
    private String listImgOrgFileNm;

    @TableField("list_img_file_nm")
    private String listImgFileNm;

    @TableField("is_set_link")
    private Integer isSetLink;

    @TableField("ordb")
    private String ordb;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    /**
     * 是否有效0是1否
     */
    @TableField("enable_flag")
    @TableLogic
    private String enableFlag;
}
