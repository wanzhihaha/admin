package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

public enum ExpressUseEnum {

    Y("Y", "支持"),
    N("N", "不支持"),
    ;

    private String cd;
    private String desc;

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ExpressUseEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }
    public static ExpressUseEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ExpressUseEnum enums : ExpressUseEnum.values()) {
            if (code.equals(enums.getCd())) {
                return enums;
            }
        }
        return null;
    }
}
