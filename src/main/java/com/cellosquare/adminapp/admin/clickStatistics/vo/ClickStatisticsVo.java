package com.cellosquare.adminapp.admin.clickStatistics.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
@Data
public class ClickStatisticsVo implements Serializable {

    private String id;

    /**
     * 点击者ip
     */
    private String ip;

    /**
     * 来源 1.chatbot
     */
    private String source;

    /**
     * 请求的url 上级url
     */
    private String url;

    /**
     * 点击时间
     */
    private String createTime;


    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
}
