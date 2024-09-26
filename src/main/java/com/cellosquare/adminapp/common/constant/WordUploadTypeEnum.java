package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 产品枚举
 */
public enum WordUploadTypeEnum {
    ARTICLE("1", "文章"),
    QA("2", "问答"),
    HELP("3", "帮助与支持"),
    ;

    private String code;
    private String cnValue;

    WordUploadTypeEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static WordUploadTypeEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (WordUploadTypeEnum enums : WordUploadTypeEnum.values()) {
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
