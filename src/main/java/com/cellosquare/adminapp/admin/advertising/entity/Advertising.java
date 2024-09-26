package com.cellosquare.adminapp.admin.advertising.entity;

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
 * @since 2022-12-340 15:25:29
 */
@Getter
@Setter
@TableName("mk_advertising")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * meta表id
     */
    @TableField("meta_seq_no")
    private Long metaSeqNo;
    /**
     * 广告名
     */
    @TableField("ad_name")
    private String adName;

    /**
     * 广告位置:10菜单栏20产品页30资源页
     */
    @TableField("ad_location")
    private String adLocation;

    /**
     * 广告跳转链接
     */
    @TableField("ad_url")
    private String adUrl;

    /**
     * 图片地址
     */
    @TableField("ad_pic_url")
    private String adPicUrl;
    @TableField("ad_mobile_pic_url")
    private String adMobilePicUrl;
    @TableField("ad_mobile_pic_name")
    private String adMobilePicName;

    /**
     * 图片名称
     */
    @TableField("ad_pic_name")
    private String adPicName;

    /**
     * 使用状态
     */
    @TableField("use_yn")
    private String useYn;

    /**
     * 排序
     */
    @TableField("ordb")
    private String ordb;

    /**
     * 关键词
     */
    @TableField("ad_keyword")
    private String adKeyword;

    /**
     * 是否有效0是1否
     */
    @TableField("enable_flag")
    @TableLogic
    private String enableFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Timestamp createDate;

    /**
     * 创建人id
     */
    @TableField(value = "create_persion_id", fill = FieldFill.INSERT)
    private String createPersionId;

    /**
     * 修改时间
     */
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Timestamp updateDate;

    /**
     * 修改人id
     */
    @TableField(value = "update_persion_id", fill = FieldFill.UPDATE)
    private String updatePersionId;

    /**
     * 生效时间
     */
    @TableField(value = "effective_time")
    private Timestamp effectiveTime;

    /**
     * 失效时间
     */
    @TableField(value = "dead_time")
    private Timestamp deadTime;
}
