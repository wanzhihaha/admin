package com.cellosquare.adminapp.common.constant;

import java.util.HashMap;
import java.util.Map;

public class SystemConstant {

    public static final String QUOTE_CATEGORY_GRP = "QUOTE_CATEGORY_GRP";
    public static final String ROUTE_COUNTRY_CD = "ROUTE_COUNTRY_CD";

    public static Map<String, String> CHANGE_ITEM_MAP = new HashMap<>();
    static {
        CHANGE_ITEM_MAP.put("admAuth", "Sort");
        CHANGE_ITEM_MAP.put("admUserNm", "Name");
        CHANGE_ITEM_MAP.put("admPw", "Password");
        CHANGE_ITEM_MAP.put("mngLocation", "Management Location");
        CHANGE_ITEM_MAP.put("useYn", "Status");
    }



    /** status Yes Or No **/
    public static String Y = "Y";
    public static String N = "N";

    public final static String SESSION_TASK = "TASK_FLAG";

    public static final String SEND_PRODUCT_EXCEPTION = "sendPostException";

    public static final String APPLICATION_JSON = "application/json";

    /**
     * open API request key name
     */
    public static final String X_API_KEY= "x-api-key";

    /**
     * system-config.xml key of open API quote request header x-api-key
     */
    public static final String OPENAPI_QUOTE_KEY = "openapi.quote.key";


    public static final String OPENAPI_PRODUCT_URL  = "openapi.route.url";


    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    /**
     * AES密钥
     */
    public static final String AES_SECRET_KEY = "punf4eQmG91d4h7n";

}
