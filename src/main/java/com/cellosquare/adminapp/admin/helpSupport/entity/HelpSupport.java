package com.cellosquare.adminapp.admin.helpSupport.entity;

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
 * @since 2023-03-65 09:44:59
 */
@Getter
@Setter
@TableName("mk_help_support")
public class HelpSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("top_id")
    private Long topId;

    @TableField("child_menu_id")
    private Long childMenuId;

    @TableField("meta_seq_no")
    private Integer metaSeqNo;

    @TableField("name")
    private String name;

    @TableField("summary_info")
    private String summaryInfo;

    @TableField("contents")
    private String contents;

    @TableField("use_yn")
    private String useYn;

    @TableField("srch_cnt")
    private Integer srchCnt;

    @TableField("is_nice")
    private Integer isNice;

    @TableField("un_help")
    private Integer unHelp;

    @TableField("complex_content")
    private Integer complexContent;

    @TableField("unresolved_issues")
    private Integer unresolvedIssues;

    @TableField("photo_link_invalid")
    private Integer photoLinkInvalid;

    @TableField("ugly_page")
    private Integer uglyPage;

    @TableField("is_set_link")
    private Integer isSetLink;

    @TableField("ordb")
    private Integer ordb;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("source")
    private Integer source;

    /**
     * 是否有效0是1否
     */
    @TableField("enable_flag")
    @TableLogic
    private String enableFlag;
}
