package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HelpSupprtoMenuEnum {
    PRODUCT_INTRODUCTION("1", "产品介绍"),//产品介绍
    OPERATION_GUIDE("2", "操作指南"),//操作指南
    QANDA("3", "常见问题"),//常见问题
    ;
    private String code;
    private String desc;

    public static HelpSupprtoMenuEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (HelpSupprtoMenuEnum enums : HelpSupprtoMenuEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
