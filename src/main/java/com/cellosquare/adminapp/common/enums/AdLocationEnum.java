package com.cellosquare.adminapp.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdLocationEnum {
    COMPANY_NEWS("10", "菜单栏广告"),
    CROSS_BORDER("20", "产品页广告"),
    DYNAMICS_LOGISTICS_LIST("30", "资源列表广告"),
    DYNAMICS_LOGISTICS("40", "资源详情广告"),
    LOGISTICSQA_LIST("50", "物流问答列表广告"),
    LOGISTICSQA_DETAIL("60", "物流问答详情广告"),
    ACTIVITIES_DETAIL("70", "公司活动详情广告"),
    TERMINOLOGY_DETAIL("80", "术语详情图片广告"),
    KEYWORD_DETAIL("90", "关键词详情图片广告"),
    KEYWORD_TEXT_DETAIL("100", "关键词详情文本广告"),
    TERMINOLOGY_TEXT_DETAIL("110", "术语详情文本广告"),
    PAGE_TEJIACANG_POP("120", "首页特价舱弹窗广告"),
    TEJIACANG_DETAIL("130", "特价舱广告"),
    VIDEO_DETAIL("140", "视频页广告"),
    ;
    private String code;
    private String desc;

    public static AdLocationEnum getEnumByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (AdLocationEnum enums : AdLocationEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
