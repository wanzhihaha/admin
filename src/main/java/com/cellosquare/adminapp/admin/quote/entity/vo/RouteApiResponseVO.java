package com.cellosquare.adminapp.admin.quote.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class RouteApiResponseVO {

    private Integer total;
    private List<RouteApiDetailVO> list;

    }
