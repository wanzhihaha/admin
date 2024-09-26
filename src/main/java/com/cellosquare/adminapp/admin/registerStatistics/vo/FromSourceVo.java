package com.cellosquare.adminapp.admin.registerStatistics.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class FromSourceVo implements Serializable {

    private String id;

    private String type;
    private String url;

    private String typeName;

    private String sourceVal;

    private Timestamp insDtm;


    private String insPersonId;


    private Timestamp updDtm;


    private String updPersonId;

    //新增人
    private String insPersonNm;
    //修改人
    private String updPersonNm;

    private String identifier;

    private String appSuccessIdentifier;
    private String shareLink;
    private String remark;
    private String name;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
}
