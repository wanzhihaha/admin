package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Data
@ColumnWidth(15)
public class RegisterButtonExportVo implements Serializable {
    //    @ExcelProperty(value = {"ip"})
//    private String ip;
    @ExcelProperty(value = {"名称"})
    private String name;
    @ExcelProperty(value = {"渠道"})
    private String channel;
    @ExcelProperty(value = {"推送类型"})
    private String source;
    /*  @ExcelProperty(value = {"标识符"})
      private String identifier;
      @ExcelProperty(value = {"是否推送百度"})
      private String isPushBaidu;
      @ExcelProperty(value = {"是否推送GIO"})
      private String isPushGio;*/
    @ColumnWidth(50)
    @ExcelProperty(value = {"官网URL"})
    private String shareLink;
    @ColumnWidth(50)
    @ExcelProperty(value = {"注册link"})
    private String url;
    @ColumnWidth(50)
    @ExcelProperty(value = {"特价舱产品名"})
    private String bargainProductName;
    @ColumnWidth(50)
    @ExcelProperty(value = {"特价舱线路名"})
    private String bargainLineName;
    @ColumnWidth(20)
    @ExcelProperty(value = {"创建时间"})
    private String createTime;
}
