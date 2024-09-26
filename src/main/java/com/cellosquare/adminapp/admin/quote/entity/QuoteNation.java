package com.cellosquare.adminapp.admin.quote.entity;

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
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Getter
@Setter
@TableName("mk_quote_nation")
public class QuoteNation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "nation_seq_no",type = IdType.AUTO)
    private Long nationSeqNo;

    @TableField("nation_cd")
    private String nationCd;

    @TableField("nation_nm")
    private String nationNm;

    @TableField("nation_cn_nm")
    private String nationCnNm;

    @TableField("continent_cd")
    private String continentCd;

    @TableField("search_key_word")
    private String searchKeyWord;

    @TableField("use_yn")
    private String useYn;

    @TableField("express_use_yn")
    private String expressUseYn;

    @TableField("img_file_path")
    private String imgFilePath;

    @TableField("img_org_file_nm")
    private String imgOrgFileNm;

    @TableField("img_file_nm")
    private String imgFileNm;

    @TableField("img_size")
    private Integer imgSize;

    @TableField("is_hot")
    private Integer isHot;

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

}
