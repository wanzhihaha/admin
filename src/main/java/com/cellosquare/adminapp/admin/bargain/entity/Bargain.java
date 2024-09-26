package com.cellosquare.adminapp.admin.bargain.entity;

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
 * @since 2023-08-234 08:55:28
 */
@Getter
@Setter
@TableName("mk_bargain")
public class Bargain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;

    /**
     * 始发地
     */
    @TableField("origin")
    private String origin;

    /**
     * 目的地
     */
    @TableField("destination")
    private String destination;

    /**
     * 参考时效
     */
    @TableField("aging")
    private String aging;



    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否热卖
     */
    @TableField("hot_flag")
    private String hotFlag;

    /**
     * 币种
     */
    @TableField("currency")
    private String currency;

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

    @TableField("list_img_path")
    private String listImgPath;

    @TableField("list_img_org_file_nm")
    private String listImgOrgFileNm;

    @TableField("list_img_file_nm")
    private String listImgFileNm;

    /**
     * 埋点标识符
     */
    @TableField("identifier")
    private String identifier;

    /**
     * 产品类型
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 生效日期
     */
    @TableField(value ="effective_date")
    private Timestamp effectiveDate;

    /**
     * 失效日期
     */
    @TableField(value ="expiry_date")
    private Timestamp expiryDate;
}
