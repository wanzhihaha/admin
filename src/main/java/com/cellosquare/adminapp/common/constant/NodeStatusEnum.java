package com.cellosquare.adminapp.common.constant;

import cn.hutool.core.util.StrUtil;

public enum NodeStatusEnum {

    N("N", "New"),
    C("C", "Confirm");

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

    NodeStatusEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }
    public static NodeStatusEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (NodeStatusEnum enums : NodeStatusEnum.values()) {
            if (code.equals(enums.getCd())) {
                return enums;
            }
        }
        return null;
    }
}
