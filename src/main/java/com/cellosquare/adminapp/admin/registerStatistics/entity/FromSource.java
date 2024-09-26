package com.cellosquare.adminapp.admin.registerStatistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author walker
 * @since 2023-07-184 15:36:54
 */
@Getter
@Setter
@TableName("mk_from_source")
public class FromSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("type")
    private String type;

    @TableField("source_val")
    private String sourceVal;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("identifier")
    private String identifier;

    @TableField("remark")
    private String remark;

    @TableField("app_success_identifier")
    private String appSuccessIdentifier;
    @TableField("name")
    private String name;
    @TableField("share_link")
    private String shareLink;

    public FromSource() {
    }

    public FromSource(String type, String sourceVal, String identifier,
                      String remark, String appSuccessIdentifier, String name, String shareLink) {
        this.type = type;
        this.sourceVal = sourceVal;
        this.identifier = identifier;
        this.remark = remark;
        this.appSuccessIdentifier = appSuccessIdentifier;
        this.name = name;
        this.shareLink = shareLink;
    }
}
