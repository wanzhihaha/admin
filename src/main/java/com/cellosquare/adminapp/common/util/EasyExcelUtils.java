package main.java.com.cellosquare.adminapp.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel操作工具类
 */
public class EasyExcelUtils {

    /**
     * excel文件流xlsx格式导出 注解表头 @ExcelProperty(value = "主叫姓名",index = 0)
     *
     * @return void
     * @params [response, list, fileName, sheetName, clazz]
     */
    public static void writeExcel(HttpServletResponse response, List<?> list, String fileName, String sheetName, Class<?> clazz) throws Exception {
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        writeExcelImp(response,list,fileName,sheetName,clazz, new HorizontalCellStyleStrategy(null, contentWriteCellStyle));
    }

    /**
     * excel文件流xlsx格式导出 注解表头 @ExcelProperty(value = "主叫姓名",index = 0)
     *
     * @return void
     * @params [response, list, fileName, sheetName, clazz]
     */
    public static void writeExcelImp(HttpServletResponse response, List<?> list, String fileName, String sheetName, Class<?> clazz,WriteHandler writeHandler) throws Exception {
        handleResponse(response, fileName);
        //sheet名
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(getDefaultWriteHandler())
                .registerWriteHandler(writeHandler)
                .sheet(sheetName)
                .doWrite(list);
    }

    private static void handleResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "max-age=0");
    }

    public static WriteHandler getDefaultWriteHandler() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setBold(true);// 加粗
        headWriteFont.setFontHeightInPoints((short) 11);//字体大小
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setWrapped(false);//自动换行
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);//CENTER
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderBottom(BorderStyle.NONE);//THIN
        contentWriteCellStyle.setBorderLeft(BorderStyle.NONE);
        contentWriteCellStyle.setBorderRight(BorderStyle.NONE);
        contentWriteCellStyle.setBorderTop(BorderStyle.NONE);
        contentWriteCellStyle.setWrapped(false);//自动换行

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }
}
