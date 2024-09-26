package com.cellosquare.adminapp.admin.registerStatistics.entity;

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
 * @since 2023-07-184 15:36:54
 */
@Getter
@Setter
@TableName("mk_register_app_success")
public class RegisterAppSuccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("from_source_id")
    private Long fromSourceId;

    @TableField("ip")
    private String ip;

    @TableField("from_source")
    private String fromSource;

    @TableField("is_push_baidu")
    private String isPushBaidu;

    @TableField("is_push_gio")
    private String isPushGio;

    @TableField("source_val")
    private String sourceVal;

    @TableField("url")
    private String url;

    @TableField("status")
    private String status;

    @TableField("organization_id")
    private String organizationId;

    @TableField("member1_approval_date")
    private String member1ApprovalDate;

    @TableField("create_time")
    private Timestamp createTime;

    @TableField("start_time")
    private String startTime;

    @TableField("consume_time")
    private String consumeTime;

    @TableField("app_success_identifier")
    private String appSuccessIdentifier;
    @TableField("channel")
    private String channel;
    @TableField("name")
    private String name;
    @TableField("bargain_product_name")
    private String bargainProductName;

    @TableField("bargain_line_name")
    private String bargainLineName;
    @TableField("share_link")
    private String shareLink;
}
