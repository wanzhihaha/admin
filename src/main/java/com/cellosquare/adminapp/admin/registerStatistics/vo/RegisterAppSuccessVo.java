package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;


@Data
public class RegisterAppSuccessVo implements Serializable {

    private String id;
    private String fromSourceId;
    private String ip;

    private String fromSource;

    private String isPushBaidu;

    private String isPushGio;

    private String sourceVal;

    private String url;

    private String status;

    private String organizationId;

    private String member1ApprovalDate;
    private String createTime;
    private String name;
    private String startTime;

    private String consumeTime;
    private String appSuccessIdentifier;
    private String channel;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    private String bargainProductName;
    private String bargainLineName;
    private String shareLink;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
}
