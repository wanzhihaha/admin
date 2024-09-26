package com.cellosquare.adminapp.admin.helpSupport.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:44:59
 */
@Data
public class HelpSupportVo extends AdminSeoVO {

    private static final long serialVersionUID = 1L;

    private String id;

    private String topId;
    private String topName;

    private String childMenuId;

    private String childMenuName;

    private String name;

    private String summaryInfo;

    private String contents;

    private String useYn;

    private Integer srchCnt;

    private Integer isNice;

    private Integer unHelp;

    private Integer complexContent;

    private Integer unresolvedIssues;

    private Integer photoLinkInvalid;

    private Integer uglyPage;

    private Integer isSetLink;

    private Integer ordb;

    private Timestamp insDtm;

    private String insPersonId;

    private Timestamp updDtm;

    private String updPersonId;
    //新增人
    private String insPersonNm;
    //修改人
    private String updPersonNm;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    //术语标签
    private String terminologyTags;
    //术语集合
    private List<String> terminologyTagList;
    //关键词标签
    private String AntistopTags;
    //状态
    private String useYnNm;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정

    //是否选中
    private Integer isSelect = 0;
    private Integer[] listOrdb;
    private String[] listId;
    private String searchValue;
}
