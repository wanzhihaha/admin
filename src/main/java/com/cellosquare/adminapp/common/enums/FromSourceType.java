package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FromSourceType {
    baidu("1", "baidu"),
    growing_io("2", "growing_io"),
    guawang("3", "官网注册"),
    ;
    private String code;
    private String desc;

    public static FromSourceType getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (FromSourceType enums : FromSourceType.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }

    public static FromSourceType getEnumByVal(String val) {
        if (StrUtil.isEmpty(val)) {
            return null;
        }
        for (FromSourceType enums : FromSourceType.values()) {
            if (val.equals(enums.getDesc())) {
                return enums;
            }
        }
        return null;
    }
}
