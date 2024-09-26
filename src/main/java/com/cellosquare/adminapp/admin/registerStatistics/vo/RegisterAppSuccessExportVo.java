package com.cellosquare.adminapp.admin.registerStatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;


@Data
@ColumnWidth(15)
public class RegisterAppSuccessExportVo implements Serializable {
    //    @ExcelProperty(value = {"ip"})
//    private String ip;
    @ExcelProperty(value = {"名称"})
    private String name;
    @ExcelProperty(value = {"渠道"})
    private String channel;
    @ExcelProperty(value = {"推送类型"})
    private String fromSource;
    /*    @ExcelProperty(value = {"是否推送百度"})
        private String isPushBaidu;
        @ExcelProperty(value = {"是否推送GIO"})
        private String isPushGio;
        @ExcelProperty(value = {"来源值"})
        private String sourceVal;
        @ExcelProperty(value = {"标识符"})
        private String appSuccessIdentifier;*/
    @ColumnWidth(50)
    @ExcelProperty(value = {"官网URL"})
    private String shareLink;

    @ColumnWidth(50)
    @ExcelProperty(value = {"注册link"})
    private String url;

    @ExcelProperty(value = {"M1用户状态"})
    private String status;
    @ColumnWidth(25)
    @ExcelProperty(value = {"M1 Organization ID"})
    private String organizationId;
    @ColumnWidth(20)
    @ExcelProperty(value = {"M1用户创建时间"})
    private String member1ApprovalDate;
    @ExcelProperty(value = {"注册耗时(分钟)"})
    private String consumeTime;
    @ExcelProperty(value = {"特价舱产品名"})
    private String bargainProductName;
    @ColumnWidth(50)
    @ExcelProperty(value = {"特价舱线路名"})
    private String bargainLineName;
    @ColumnWidth(20)
    @ExcelProperty(value = {"记录时间"})
    private String createTime;
}
