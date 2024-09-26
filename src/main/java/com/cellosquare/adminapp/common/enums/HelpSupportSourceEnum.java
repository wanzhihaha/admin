package com.cellosquare.adminapp.common.enums;

import lombok.Getter;


@Getter
public enum HelpSupportSourceEnum {
    ADD(1, "新增"),
    IMPORT(2, "导入");


    private Integer code;
    private String value;

    HelpSupportSourceEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
