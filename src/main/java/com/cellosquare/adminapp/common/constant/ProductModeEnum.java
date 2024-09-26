package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

public enum ProductModeEnum {

    AR("AR", "国际空运"),
    VS("VS", "海运"),
    VSFCL("VS-FCL", "海运(FCL)"),
    VSLCL("VS-LCL", "海运(LCL)"),

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

    ProductModeEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }

    public static ProductModeEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ProductModeEnum enums : ProductModeEnum.values()) {
            if (code.equals(enums.getCd())) {
                return enums;
            }
        }
        return null;
    }
}
