package com.cellosquare.adminapp.admin.apihistory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author walker
 * @since 2023-08-230 11:03:00
 */
@Getter
@Setter
@TableName("mk_api_history")
public class ApiHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("type")
    private String type;

    @TableField("url")
    private String url;

    @TableField("request_body")
    private String requestBody;

    @TableField("response_body")
    private String responseBody;

    @TableField("exception_msg")
    private String exceptionMsg;

    @TableField("status")
    private Integer status;

    @TableField("create_date")
    private Timestamp createDate;

    @TableField(exist = false)
    private String startDate;
    @TableField(exist = false)
    private String endDate;
    @TableField(exist = false)
    private String page = "1"; //초기값 설정
    @TableField(exist = false)
    private String rowPerPage = "20"; //초기값 설정
    @TableField(exist = false)
    private String searchValue; //초기값 설정
    @TableField(exist = false)
    private String typeName; //초기값 설정
     @TableField(exist = false)
    private String statusName; //초기값 설정
}
