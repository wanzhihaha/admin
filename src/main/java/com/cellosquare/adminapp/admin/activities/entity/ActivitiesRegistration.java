package com.cellosquare.adminapp.admin.activities.entity;

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
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Getter
@Setter
@TableName("mk_activities_registration")
public class ActivitiesRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("activities_id")
    private Long activitiesId;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("company")
    private String company;

    @TableField("product_category")
    private String productCategory;

    @TableField("transport_type")
    private String transportType;

    @TableField("use_yn")
    private String useYn;

    @TableField("create_date")
    private Timestamp createDate;


}
