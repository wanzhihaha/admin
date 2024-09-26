package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 产品枚举
 */
public enum UseEnum {
    USE("Y", "use"),
    NO_USE("N", "Not use"),
    ;

    private String code;
    private String cnValue;

    UseEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }
    public static UseEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (UseEnum enums : UseEnum.values()) {
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
