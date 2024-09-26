package com.cellosquare.adminapp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationEnum {
    INSERT("10", "新增"),
    UPDATE("20", "修改"),
    ;
    private String code;
    private String desc;
}
