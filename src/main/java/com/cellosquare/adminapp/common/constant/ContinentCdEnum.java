package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

public enum ContinentCdEnum {

    AS("AS", "亚洲"),
    NA("NA", "北美洲"),
    SA("SA", "南美洲"),
    EU("EU", "欧洲"),
    AF("AF", "非洲"),
    OC("OC", "大洋洲"),
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

    ContinentCdEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }
    public static ContinentCdEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ContinentCdEnum enums : ContinentCdEnum.values()) {
            if (code.equals(enums.getCd())) {
                return enums;
            }
        }
        return null;
    }
}
