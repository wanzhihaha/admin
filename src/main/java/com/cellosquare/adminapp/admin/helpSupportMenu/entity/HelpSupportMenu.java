package com.cellosquare.adminapp.admin.helpSupportMenu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-03-65 09:48:42
 */
@Getter
@Setter
@TableName("mk_help_support_menu")
public class HelpSupportMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("p_id")
    private Long pId;

    @TableField("menu_name")
    private String menuName;

    @TableField("is_child")
    private Long isChild;

    @TableField("description")
    private String description;

    @TableField("use_yn")
    private String useYn;

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


}
