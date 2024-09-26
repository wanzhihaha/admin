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
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
@Getter
@Setter
@TableName("mk_register_button")
public class RegisterButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 点击者ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 来源 1.百度 2.GROWING_io 3、其他
     */
    @TableField("source")
    private String source;

    /**
     * 0未推送 1已推送
     */
    @TableField("is_push_baidu")
    private String isPushBaidu;

    /**
     * 0未推送 1已推送
     */
    @TableField("is_push_gio")
    private String isPushGio;

    /**
     * 唯一标识
     */
    @TableField("unique_id")
    private String uniqueId;

    /**
     * 请求的url 上级url
     */
    @TableField("url")
    private String url;

    /**
     * 点击时间
     */
    @TableField("create_time")
    private Timestamp createTime;

    @TableField("identifier")
    private String identifier;

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
