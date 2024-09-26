package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
@Data
public class RegisterButtonVo implements Serializable {

    private String id;

    /**
     * 点击者ip
     */
    private String ip;

    /**
     * 来源 1.百度 2.GROWING_io 3、其他
     */
    private String source;

    /**
     * 0未推送 1已推送
     */
    private String isPushBaidu;

    /**
     * 0未推送 1已推送
     */
    private String isPushGio;

    /**
     * 唯一标识
     */
    private String uniqueId;

    /**
     * 请求的url 上级url
     */
    private String url;
    private String channel;
    private String name;
    /**
     * 点击时间
     */
    private String createTime;

    private String identifier;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    private String bargainProductName;
    private String bargainLineName;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
    private String shareLink;
}
