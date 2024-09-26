package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StickTypeEnum {
    DYNAMICS_LOGISTICS("10", "置顶1"),
    LOGISTICS_POLICY_2("20", "置顶2"),
    LOGISTICS_POLICY_3("30", "置顶3"),
    LOGISTICS_POLICY_4("40", "空"),
    ;
    private String code;
    private String desc;
    public static StickTypeEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (StickTypeEnum enums : StickTypeEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
