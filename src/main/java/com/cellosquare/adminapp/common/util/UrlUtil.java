package main.java.com.cellosquare.adminapp.common.util;

public class UrlUtil {
    public static void main(String[] args) {
        String url = "https://dev-app.cellosquare.cn/business-registration-number";
        String newUrl = overrideUrlParams(url, "2");
        System.out.println(newUrl);

        url = "https://dev-app.cellosquare.cn/business-registration-number?id=3";
        newUrl = overrideUrlParams(url, "2");
        System.out.println(newUrl);

        url = "https://dev-app.cellosquare.cn/business-registration-number?id=3&from_source=1";
        newUrl = overrideUrlParams(url, "2");
        System.out.println(newUrl);
    }

    public static String overrideUrlParams(String url, String newFromSource) {
        int questionMarkIndex = url.indexOf('?');
        if (questionMarkIndex != -1) {
            String params = url.substring(questionMarkIndex + 1);
            String[] paramArray = params.split("&");
            StringBuilder newParams = new StringBuilder();
            boolean foundFromSource = false;
            for (String param : paramArray) {
                String[] keyValue = param.split("=");
                if (!keyValue[0].equals(com.cellosquare.adminapp.common.constant.Constants.FROM_SOURCE)) {
                    newParams.append(keyValue[0]).append("=").append(keyValue[1]).append("&");
                } else {
                    foundFromSource = true;
                }
            }
            newParams.append(com.cellosquare.adminapp.common.constant.Constants.FROM_SOURCE + "=").append(newFromSource);
            url = url.substring(0, questionMarkIndex) + "?" + newParams.toString();
        } else {
            url += "?" + com.cellosquare.adminapp.common.constant.Constants.FROM_SOURCE + "=" + newFromSource;
        }
        return url;
    }
}
