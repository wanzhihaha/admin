package com.cellosquare.adminapp.admin.continent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-11-314 14:07:21
 */
@Getter
@Setter
@TableName("mk_continent")
public class Continent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("use_yn")
    private String useYn;

    @TableField("fcl_ordb")
    private Integer fclOrdb;

    @TableField("lcl_ordb")
    private Integer lclOrdb;

    @TableField("air_ordb")
    private Integer airOrdb;

    @TableField("express_ordb")
    private Integer expressOrdb;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("remark")
    private String remark;

    @TableField("cd")
    private String cd;


}
