package com.cellosquare.adminapp.admin.clickStatistics.vo;

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
public class ClickStatisticsExportVo implements Serializable {
    //    @ExcelProperty(value = {"ip"})
//    private String ip;
/*    @ExcelProperty(value = {"名称"})
    private String name;*/
    @ExcelProperty(value = {"类型"})
    private String source;
    @ColumnWidth(50)
    @ExcelProperty(value = {"注册link"})
    private String url;
    @ColumnWidth(20)
    @ExcelProperty(value = {"点击时间"})
    private String createTime;
}
