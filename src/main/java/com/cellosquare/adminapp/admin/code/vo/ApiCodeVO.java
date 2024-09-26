package com.cellosquare.adminapp.admin.code.vo;

import lombok.Data;

@Data
public class ApiCodeVO {
    // 코드
    private String cd;
    // 코드명
    private String cdNm;
    // 언어코드
    private String langCd;

    private String grpCd;
    private String cdDesc;
}
