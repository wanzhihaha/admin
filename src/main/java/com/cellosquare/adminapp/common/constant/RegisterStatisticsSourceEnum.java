package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 联系我们来源枚举
 */
public enum RegisterStatisticsSourceEnum {
    NONE("0", "无"),
    BAIDU("1", "百度"),
    GROWING_IO("2", "growing_io(旧数据)"),
    OTHER("3", "官网注册"),
    BAIDU_BRAND("4", "百度品牌广告"),
    ;

    private String code;
    private String cnValue;

    RegisterStatisticsSourceEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static RegisterStatisticsSourceEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (RegisterStatisticsSourceEnum enums : RegisterStatisticsSourceEnum.values()) {
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
