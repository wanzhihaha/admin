package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 活动类型枚举
 */
public enum ActivitiesEnum {
    ONLINE("1", "线上活动"),
    OFFINE("2", "线下活动"),
    ;

    private String code;
    private String cnValue;

    ActivitiesEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static ActivitiesEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ActivitiesEnum enums : ActivitiesEnum.values()) {
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
