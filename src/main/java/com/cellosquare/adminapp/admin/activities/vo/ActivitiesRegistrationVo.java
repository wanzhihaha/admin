package com.cellosquare.adminapp.admin.activities.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Data
public class ActivitiesRegistrationVo implements Serializable {


    private String id;

    private String activitiesId;
    private String activitiesName;
    private String activitiesType;

    private String name;

    private String phone;

    private String company;

    private String productCategory;

    private String transportType;

    //private String useYn;
    private Timestamp createDate;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정

    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
}
