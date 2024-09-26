package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 联系我们来源枚举
 */
public enum ContactSourceEnum {
    PC_SIDE("1", "官网"),
    BAIDU_SIDE("2", "百度推广"),
    MINIPROGRAM_SIDE("3", "小程序"),
    ;

    private String code;
    private String cnValue;

    ContactSourceEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static ContactSourceEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ContactSourceEnum enums : ContactSourceEnum.values()) {
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
