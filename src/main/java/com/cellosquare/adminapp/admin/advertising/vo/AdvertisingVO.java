package com.cellosquare.adminapp.admin.advertising.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdvertisingVO extends GeneralVO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 广告名
     */
    private String adName;

    /**
     * 广告位置:10菜单栏20产品页30资源页
     */
    private String adLocation;

    /**
     * 广告跳转链接
     */
    private String adUrl;

    /**
     * 图片地址
     */
    private String adPicUrl;

    /**
     * 图片名称
     */
    private String adPicName;

    /**
     * 使用状态
     */
    private String useYn;

    /**
     * 排序
     */
    private String ordb;

    /**
     * 关键词
     */
    private String adKeyword;

    /**
     * 是否有效0是1否
     */
    private String enableFlag;

    /**
     * 创建时间
     */
    private Timestamp createDate;

    /**
     * 创建人id
     */
    private String createPersionId;

    /**
     * 修改时间
     */
    private Timestamp updateDate;
    /**
     * 生效时间
     */
    private String effectiveTime;
    private String imgKinds;

    /**
     * 失效时间
     */
    private String deadTime;

    private String adMobilePicUrl;
    private String adMobilePicName;
    /**
     * 修改人id
     */
    private String updatePersionId;

    private String[] listSortOrder;
    private String[] listblogSeq;
}
