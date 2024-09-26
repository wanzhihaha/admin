package com.cellosquare.adminapp.common.constant;

import java.util.Objects;

public enum IsHotEnum {

    IS(1, "热门"),
    NOT(0, "非"),
    ;

    private Integer cd;
    private String desc;

    public Integer getCd() {
        return cd;
    }

    public String getDesc() {
        return desc;
    }

    IsHotEnum(Integer cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }

    public static IsHotEnum getEnumByCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (IsHotEnum enums : IsHotEnum.values()) {
            if (code == enums.getCd()) {
                return enums;
            }
        }
        return null;
    }
}
