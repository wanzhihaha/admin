package com.cellosquare.adminapp.common.constant;

public enum RoutStatusEnum {

    Y("Y", "Y"),
    N("N", "N");

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

    RoutStatusEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }
}
