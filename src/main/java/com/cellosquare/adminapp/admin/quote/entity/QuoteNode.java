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
@TableName("mk_quote_node")
public class QuoteNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("is_hot")
    private Integer isHot;

    @TableField("ordb")
    private String ordb;

    @TableField("node_cd")
    private String nodeCd;

    @TableField("nation_seq_no")
    private Long nationSeqNo;

    @TableField("node_eng_nm")
    private String nodeEngNm;

    @TableField("node_cn_nm")
    private String nodeCnNm;

    @TableField("product_mode")
    private String productMode;

    @TableField("city_eng_nm")
    private String cityEngNm;
    @TableField("city_cn_nm")
    private String cityCnNm;

    @TableField("search_key_word")
    private String searchKeyWord;

    @TableField("node_status")
    private String nodeStatus;

    @TableField("node_source")
    private String nodeSource;

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
