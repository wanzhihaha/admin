package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;

/**
 * 活动类型枚举
 */
public enum SinotechProdutctTypeListEnum {
    ELEP("ELEP", "电子产品"),
    BUIB("BUIB", "内置电池"),
    MATB("MATB", "配套电池"),
    MOPS("MOPS", "移动电源"),
    PURB("PURB", "纯电池"),
    MPWE("MPWE", "手机(无电)"),
    TEXT("TEXT", "纺织品"),
    ECIG("ECIG", "电子烟"),
    CAPA("CAPA", "电容"),
    WOOB("WOOB", "木箱"),
    GGWB("GGWB", "普货(无任何电池)"),
    MPPD("MPPD", "手机(配电)"),
    MPIE("MPIE", "手机(内电)"),
    EPPM("EPPM", "防疫物资"),
    ACCE("ACCE", "饰品"),
    HOMA("HOMA", "家电"),
    KITS("KITS", "厨房用品"),
    ;

    private String code;
    private String cnValue;

    SinotechProdutctTypeListEnum(String code, String cnValue) {
        this.code = code;
        this.cnValue = cnValue;
    }

    public static SinotechProdutctTypeListEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (SinotechProdutctTypeListEnum enums : SinotechProdutctTypeListEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getCnValue() {
        return cnValue;
    }
}
