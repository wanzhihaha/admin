package com.cellosquare.adminapp.admin.bargainProduct.entity;

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
 * @since 2023-08-230 10:35:01
 */
@Getter
@Setter
@TableName("mk_bargain_product")
public class BargainProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    @TableField("list_img_path")
    private String listImgPath;

    @TableField("list_img_org_file_nm")
    private String listImgOrgFileNm;

    @TableField("list_img_file_nm")
    private String listImgFileNm;

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

    @TableField("multi_flag")
    private String multiFlag;

    @TableField("mobile_list_img_path")
    private String mobileListImgPath;

    @TableField("mobile_list_img_org_file_Nm")
    private String mobileListImgOrgFileNm;

    @TableField("mobile_list_img_file_nm")
    private String mobileListImgFileNm;

}
