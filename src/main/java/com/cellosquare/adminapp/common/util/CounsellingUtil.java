package main.java.com.cellosquare.adminapp.common.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounsellingUtil {
    /**
     * 过滤特殊字符防止dds注入
     * @param str
     * @return
     */
    @Named("encodedAndDcc")
    public static String encodedAndDcc(String str){
        AES aes = SecureUtil.aes(com.cellosquare.adminapp.common.constant.TextCheckConstants.encoded_key.getBytes());
        if (!StringUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("=|-|@");
            Matcher m = p.matcher(str);
            str = m.replaceAll("").replace("+","");
            return aes.encryptHex(str);
        }
        return str;
    }

    @Named("decoded")
    public static String decoded(String str){
        AES aes = SecureUtil.aes(com.cellosquare.adminapp.common.constant.TextCheckConstants.encoded_key.getBytes());
        if (!StringUtils.isEmpty(str)) {
            try {
                return aes.decryptStr(str);
            } catch (Exception e) {
                return str;
            }
        }
        return str;
    }
}
