package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarouselTypeEnum {
    H_F_G_G("0", "横幅广告"),
    PAGE_BANNER("1", "首页轮播图"),
    TEJIA_BANNER("2", "特价舱轮播图"),

    ;
    private String code;
    private String desc;

    public static CarouselTypeEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (CarouselTypeEnum enums : CarouselTypeEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
