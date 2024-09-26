package com.cellosquare.adminapp.admin.helpSupport.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class HelpSupportExportVo implements Serializable {
    @ExcelProperty(value = {"一级类型"})
    private String topName;
    @ExcelProperty(value = {"二级类型"})
    private String childMenuName;
    @ColumnWidth(30)
    @ExcelProperty(value = {"标题"})
    private String name;
    @ExcelProperty(value = {"浏览量"})
    private String srchCnt;
    @ExcelProperty(value = {"点赞量"})
    private Integer isNice;
    @ExcelProperty(value = {"点踩量"})
    private Integer unHelp;
    @ExcelProperty(value = {"内容复杂 看不懂量"})
    private Integer complexContent;
    @ExcelProperty(value = {"按操作指引未解决问题量"})
    private Integer unresolvedIssues;
    @ExcelProperty(value = {"图片或链接失效量"})
    private Integer photoLinkInvalid;
    @ExcelProperty(value = {"页面不美观量"})
    private Integer uglyPage;
    @ExcelProperty(value = {"创建人"})
    private String insPersonNm;
    @ExcelProperty(value = {"创建时间"})
    private String insDtm;
    @ExcelProperty(value = {"更新人"})
    private String updPersonNm;
    @ExcelProperty(value = {"更新时间"})
    private String updDtm;
    @ExcelProperty(value = {"状态"})
    private String useYnNm;
}
