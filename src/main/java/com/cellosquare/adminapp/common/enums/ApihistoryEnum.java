package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;

/**
 * 活动类型枚举
 */
public enum ApihistoryEnum {
    REGISTERAPPSUCCESS("1", "注册成功回调接口"),
    VIDEOSUCCESS("2", "视频上传"),
    ;

    private String code;
    private String cnValue;

    ApihistoryEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static ApihistoryEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ApihistoryEnum enums : ApihistoryEnum.values()) {
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
