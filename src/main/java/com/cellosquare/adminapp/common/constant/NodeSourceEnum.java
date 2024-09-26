package com.cellosquare.adminapp.common.constant;

public enum NodeSourceEnum {

    A("A", "Api data"),
    E("E", "admin enter");

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

    NodeSourceEnum(String cd, String desc) {
        this.cd = cd;
        this.desc = desc;
    }
}
