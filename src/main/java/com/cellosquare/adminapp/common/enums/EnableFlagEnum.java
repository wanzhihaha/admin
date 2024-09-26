package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EnableFlagEnum {
    DYNAMICS_LOGISTICS("Y", "Use"),
    LOGISTICS_POLICY("N", "Not use"),
    ;
    private String code;
    private String desc;
    public static EnableFlagEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (EnableFlagEnum enums : EnableFlagEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
