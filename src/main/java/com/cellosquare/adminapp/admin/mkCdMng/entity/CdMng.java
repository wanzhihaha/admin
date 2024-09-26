package com.cellosquare.adminapp.admin.mkCdMng.entity;

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
 * @since 2022-12-341 19:36:20
 */
@Getter
@Setter
@TableName("mk_cd_mng")
public class CdMng implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("grp_cd")
    private String grpCd;

    @TableField("cd")
    private String cd;

    @TableField("lang_cd")
    private String langCd;

    @TableField("cd_nm")
    private String cdNm;

    @TableField("cd_desc")
    private String cdDesc;

    @TableField("use_yn")
    private String useYn;

    @TableField("ordb")
    private Integer ordb;

    @TableField("ins_dtm")
    private LocalDateTime insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private LocalDateTime updDtm;

    @TableField("upd_person_id")
    private String updPersonId;


}
