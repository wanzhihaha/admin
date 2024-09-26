package com.cellosquare.adminapp.admin.quote.entity.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminRouteApiHistoryVO {


    private String seqNo;
    private Integer resultOrd;
    private String apiUrl;
    private String apiRequestBody;
    private String apiResponseBody;
    private Timestamp sendDate;
    private Timestamp receivedDate;
    private String apiStatus;


}
