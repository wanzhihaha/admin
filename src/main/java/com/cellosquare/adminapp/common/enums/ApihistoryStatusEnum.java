package com.cellosquare.adminapp.common.enums;

import java.util.Objects;

/**
 *
 */
public enum ApihistoryStatusEnum {
    SUCCESS(1, "成功"),
    FAIL(0, "失败"),
    ;

    private Integer code;
    private String cnValue;

    ApihistoryStatusEnum(Integer code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static ApihistoryStatusEnum getEnumByCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (ApihistoryStatusEnum enums : ApihistoryStatusEnum.values()) {
            if (code == enums.getCode()) {
                return enums;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getCnValue() {
        return cnValue;
    }
}
