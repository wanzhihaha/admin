package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {
    COMPANY_NEWS("10", "公司动态"),
    CROSS_BORDER("20", "行业资讯"),
    DYNAMICS_LOGISTICS("30", "海运资讯"),
    LOGISTICS_POLICY("40", "空运资讯"),
    ;
    private String code;
    private String desc;

    public static ArticleTypeEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ArticleTypeEnum enums : ArticleTypeEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }

    public static ArticleTypeEnum getEnumByDesc(String desc) {
        if (StrUtil.isEmpty(desc)) {
            return null;
        }
        for (ArticleTypeEnum enums : ArticleTypeEnum.values()) {
            if (desc.equals(enums.getDesc())) {
                return enums;
            }
        }
        return null;
    }
}
