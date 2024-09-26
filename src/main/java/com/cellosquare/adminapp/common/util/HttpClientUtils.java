package main.java.com.cellosquare.adminapp.common.util;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 220503 수정
 * HttpClientUtils : Send external request
 * @author juru.jia
 * @Date : 2022/05/17
 */
public class HttpClientUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static OkHttpClient getClient(){

        return new OkHttpClient().newBuilder().build();
    }


    public static String doPost(JSONObject requestBody, Map<String, String> headerMap, String url){
        OkHttpClient okHttpClient = getClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(requestBody.toJSONString(), mediaType);
        Request.Builder builder = new Request.Builder();
        headerMap.forEach(builder::addHeader);
        Request request = builder.url(url)
                .method("POST", body)
                .build();
        Response execute;
        try {
            execute  = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = execute.body();
            if(null != responseBody ){
                return responseBody.string();
            }
        } catch (Exception e) {
            logger.error("doPost Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return com.cellosquare.adminapp.common.constant.SystemConstant.SEND_PRODUCT_EXCEPTION;
    }

    public static String doGet(Map<String, String> headerMap, String url){
        OkHttpClient okHttpClient = getClient();
        Request.Builder builder = new Request.Builder();
        headerMap.forEach(builder::addHeader);

        Request request = builder.url(url)
                .method("GET", null)
                .build();
        Response execute;
        try {
            execute  = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = execute.body();
            if(null != responseBody ){
                return responseBody.string();
            }
        } catch (Exception e) {
            logger.error("doGet Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return com.cellosquare.adminapp.common.constant.SystemConstant.SEND_PRODUCT_EXCEPTION;
    }
}
