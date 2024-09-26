package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 联系我们来源枚举
 */
public enum ClickStatisticsEnum {
    CHATBOT("1", "chatbot按钮点击"),
    ;

    private String code;
    private String cnValue;

    ClickStatisticsEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static ClickStatisticsEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ClickStatisticsEnum enums : ClickStatisticsEnum.values()) {
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
