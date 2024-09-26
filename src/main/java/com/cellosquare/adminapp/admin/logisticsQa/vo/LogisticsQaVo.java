package com.cellosquare.adminapp.admin.logisticsQa.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 09:03:09
 */
@Data
public class LogisticsQaVo extends AdminSeoVO {

    private String id;


    private String name;

    private String summaryInfo;


    private String contents;


    private Integer isNice;


    private String useYn;


    private Integer srchCnt;


    private String listImgPath;


    private String listImgOrgFileNm;


    private String listImgFileNm;


    private Integer isSetLink;


    private String ordb;


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
    //图片媒体
    private MultipartFile listOrginFile;
    //状态
    private String useYnNm;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
}
