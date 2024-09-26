package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 产品枚举
 */
@Getter
public enum SeoModuleEnum {

    ARTICLE("1", "文章"),
    CORPORATEACTIVITIES("2", "活动"),
    HELPSUPPORT("3", "帮助支持"),
    LOGISTICSQA("4", "物流问答"),
    TERMINOLOGY("5", "术语"),
    ANTISTOP("6", "关键词"),
    PRODUCT("7", "产品"),
    VIDEO("8", "视频"),
    ;

    private String code;
    private String cnValue;

    SeoModuleEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static SeoModuleEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (SeoModuleEnum enums : SeoModuleEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }

}
