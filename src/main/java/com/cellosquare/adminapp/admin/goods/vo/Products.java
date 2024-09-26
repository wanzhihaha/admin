package com.cellosquare.adminapp.admin.goods.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2024-01-23 10:54:14
 */
@Getter
@Setter
@TableName("mk_products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("product_seq_no")
    private Integer productSeqNo;

    @TableField("meta_seq_no")
    private Integer metaSeqNo;

    @TableField("lang_cd")
    private String langCd;

    @TableField("product_ctgry")
    private String productCtgry;

    @TableField("product_nm")
    private String productNm;

    @TableField("svc_valid_stat_date")
    private String svcValidStatDate;

    @TableField("svc_valid_end_date")
    private String svcValidEndDate;

    @TableField("product_summary_info")
    private String productSummaryInfo;

    @TableField("pc_list_img_path")
    private String pcListImgPath;

    @TableField("pc_list_img_org_file_nm")
    private String pcListImgOrgFileNm;

    @TableField("pc_list_img_file_nm")
    private String pcListImgFileNm;

    @TableField("pc_list_img_size")
    private Integer pcListImgSize;

    @TableField("pc_list_img_alt")
    private String pcListImgAlt;

    @TableField("pc_detl_img_path")
    private String pcDetlImgPath;

    @TableField("pc_detl_img_org_file_nm")
    private String pcDetlImgOrgFileNm;

    @TableField("pc_detl_img_file_nm")
    private String pcDetlImgFileNm;

    @TableField("pc_detl_img_size")
    private Integer pcDetlImgSize;

    @TableField("pc_detl_img_alt")
    private String pcDetlImgAlt;

    @TableField("mobile_list_img_path")
    private String mobileListImgPath;

    @TableField("mobile_list_img_org_file_nm")
    private String mobileListImgOrgFileNm;

    @TableField("mobile_list_img_file_nm")
    private String mobileListImgFileNm;

    @TableField("mobile_list_img_size")
    private Integer mobileListImgSize;

    @TableField("mobile_list_img_alt")
    private String mobileListImgAlt;

    @TableField("mobile_detl_img_path")
    private String mobileDetlImgPath;

    @TableField("mobile_detl_img_org_file_nm")
    private String mobileDetlImgOrgFileNm;

    @TableField("mobile_detl_img_file_nm")
    private String mobileDetlImgFileNm;

    @TableField("mobile_detl_img_size")
    private Integer mobileDetlImgSize;

    @TableField("mobile_detl_img_alt")
    private String mobileDetlImgAlt;

    @TableField("use_yn")
    private String useYn;

    @TableField("srch_cnt")
    private Integer srchCnt;

    @TableField("ordb")
    private String ordb;

    @TableField("ins_dtm")
    private LocalDateTime insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private LocalDateTime updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("product_contents")
    private String productContents;

    /**
     * 是否有效1是0否
     */
    @TableField("enable_flag")
    private String enableFlag;

    /**
     * 常见问题id
     */
    @TableField("ack_question")
    private String ackQuestion;


}
