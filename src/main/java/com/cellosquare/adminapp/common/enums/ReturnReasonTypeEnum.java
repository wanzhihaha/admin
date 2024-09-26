package com.cellosquare.adminapp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnReasonTypeEnum {
    SUCCESS(1, "有报价退出原因"),
    FAIL(2, "无报价退出原因"),
    ;
    private Integer code;
    private String desc;

    public static ReturnReasonTypeEnum getEnumByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ReturnReasonTypeEnum enums : ReturnReasonTypeEnum.values()) {
            if (code == enums.getCode()) {
                return enums;
            }
        }
        return null;
    }
}
