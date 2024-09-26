package com.cellosquare.adminapp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TermTypeEnum {
    COMPANY_NEWS("10", "新闻资讯"),
    CROSS_BORDER("20", "公司活动"),
    DYNAMICS_LOGISTICS_LIST("30", "物流问答"),
    DYNAMICS_LOGISTICS("40", "帮助与支持"),
    ;
    private String code;
    private String desc;
}
