package com.cellosquare.adminapp.admin.counselling.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ColumnWidth(15)
public class CounsellingInfoExportVO implements Serializable {
    @ColumnWidth(20)
    @ExcelProperty("日期")
    private String createDateStr;
    @ExcelProperty("来源")
    private String source;
    @ExcelProperty("类型")
    private String category;
    @ExcelProperty("称呼")
    private String name;
    @ExcelProperty("公司邮箱")
    private String emailAddress;
    @ExcelProperty("联系电话")
    private String mobilePhone;
    @ExcelProperty("公司名称")
    private String company;
    @ExcelProperty("预计月物流费")
    private String estimatedMonthlyLogisticsCost;
    @ColumnWidth(25)
    @ExcelProperty("咨询内容")
    private String comment;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("运输方式")
    private String transMode;
    @ExcelProperty("起始地")
    private String transDeptPoint;
    @ExcelProperty("目的地")
    private String transDest;
    @ExcelProperty("预计装运时间")
    private String estimatedShippingDate;
    @ExcelProperty("预计装运时间")
    private String volAndQty;
    @ExcelProperty("商品URL")
    private String productUrl;
    @ExcelProperty("向第三方提供个人信息")
    private String hq3rdOptIn;
    @ExcelProperty("营销信息")
    private String hqEmailOptIn;
}
