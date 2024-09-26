package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@ColumnWidth(15)
public class FromSourceExportVo implements Serializable {
    @ExcelProperty(value = {"渠道"})
    private String remark;
    @ExcelProperty(value = {"名称"})
    private String name;
    @ExcelProperty(value = {"类型"})
    private String typeName;
    @ColumnWidth(55)
    @ExcelProperty(value = {"官网URL"})
    private String shareLink;
    @ColumnWidth(20)
    @ExcelProperty(value = {"来源值"})
    private String sourceVal;
    @ColumnWidth(20)
    @ExcelProperty(value = {"创建时间"})
    private String insDtm;
    @ColumnWidth(20)
    @ExcelProperty(value = {"修改时间"})
    private String updDtm;
    @ExcelProperty(value = {"创建人"})
    private String insPersonNm;
    @ExcelProperty(value = {"修改人"})
    private String updPersonNm;
}
