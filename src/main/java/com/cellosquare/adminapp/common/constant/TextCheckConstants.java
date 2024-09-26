package com.cellosquare.adminapp.common.constant;

import java.util.Arrays;
import java.util.List;

public interface TextCheckConstants {
    String secretId = "31ba47e1fc1ae5797ad888afce75252f";
    String secretKey = "9b01d72072f7fc8d17cb06ddce98d896";
    String businessId = "8330eada37b3af79d954498417a69861";
    //成功状态码
    Integer SUCCESS_CODE = 200;
    //校验敏感词失败标识
    List<Integer> TEXT_CHECK_FAIL = Arrays.asList(1,2);
    /**
     * 加密密钥
     */
    String encoded_key = "aInHJXQMryYs4CCO";
}
