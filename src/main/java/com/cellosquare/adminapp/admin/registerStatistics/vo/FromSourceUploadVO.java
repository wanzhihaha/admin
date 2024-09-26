package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class FromSourceUploadVO {
    @ColumnWidth(10)
    @ExcelProperty("名称")
    private String name;
    @ColumnWidth(10)
    @ExcelProperty("渠道")
    private String remark;
    @ColumnWidth(10)
    @ExcelProperty("类型")
    private String type;
//    @ExcelIgnore
//    private String identifier;
//    @ExcelIgnore
//    private String appSuccessIdentifier;

    @ColumnWidth(50)
    @ExcelProperty("官网URL")
    private String shareLink;
    @ExcelIgnore
    private String sourceVal;
}
