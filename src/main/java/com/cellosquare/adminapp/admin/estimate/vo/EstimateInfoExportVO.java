package com.cellosquare.adminapp.admin.estimate.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

@Data
@ColumnWidth(15)
public class EstimateInfoExportVO implements Serializable {
    @ColumnWidth(20)
    @ExcelProperty("查询日期")
    private String sendDate;
    @ColumnWidth(15)
    @ExcelProperty("报价编号")
    private String sqNo;
    @ExcelProperty("类型")
    private String svcMedCtgryCd;
    @ExcelProperty("货柜类型")
    private String svcClassCd;
    @ColumnWidth(30)
    @ExcelProperty("出发地")
    private String deppNm;
    @ColumnWidth(30)
    @ExcelProperty("到达地")
    private String arrpNm;
    @ExcelProperty("货物信息")
    private String itemInfo;
    @ExcelProperty("查询结果")
    private String searchRecordYn;
    @ExcelProperty("顺序编号")
    private String recordNo;
    @ExcelProperty("服务")
    private String carrierName;
    @ExcelProperty("运费")
    private String quotePrice;
    /* @ColumnWidth(35)
     @ExcelProperty("退出原因")
     private String returnReason;*/
    @ColumnWidth(35)
    @ExcelProperty("产品类型")
    private String productType;
    //    @ExcelProperty("是否发送邮件")
//    private String emailYn;
    @ExcelProperty("访问IP")
    private String accessIp;
}
