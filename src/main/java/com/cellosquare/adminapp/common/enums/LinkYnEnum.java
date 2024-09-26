package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkYnEnum {
    DYNAMICS_LOGISTICS("1", "存在内链"),
    LOGISTICS_POLICY("0", "不存在内链"),
    ;
    private String code;
    private String desc;
    public static LinkYnEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (LinkYnEnum enums : LinkYnEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
