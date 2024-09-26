package main.java.com.cellosquare.adminapp.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ExcetionUtil {
    /**
     * 将exception转换为String输出
     *
     * @param e
     * @return
     */
    public static String geterrorinfofromexception(Exception e) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(buf, true));
        return buf.toString();
    }
}
