package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;


public enum BooleanEnum {
    TRUE("1", "是"),
    FALSE("0", "否"),
    ;

    private String code;
    private String cnValue;

    BooleanEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static BooleanEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (BooleanEnum enums : BooleanEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getCnValue() {
        return cnValue;
    }
}
