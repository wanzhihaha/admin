package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;
import com.cellosquare.adminapp.common.enums.EnableFlagEnum;

/**
 * 产品枚举
 */
public enum HotRecommendEnum {
    HOT_LIST("HOT_LIST", ""),
    HOT_LIST_01("HOT_LIST_01", "热门产品"),
    HOT_LIST_02("HOT_LIST_02", "热门推荐"),
    ;

    private String code;
    private String cnValue;

    HotRecommendEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }
    public static HotRecommendEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (HotRecommendEnum enums : HotRecommendEnum.values()) {
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
