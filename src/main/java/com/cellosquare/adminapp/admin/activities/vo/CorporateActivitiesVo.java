package com.cellosquare.adminapp.admin.activities.vo;


import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 14:44:39
 */
@Data
public class CorporateActivitiesVo extends AdminSeoVO {


    private String id;


    private String type;


    private String name;


    private String summaryInfo;


    private String contents;


    private Integer srchCnt;


    private String useYn;


    private String listImgPath;


    private String listImgOrgFileNm;


    private String listImgFileNm;


    /**
     * 生效时间
     */
    private String effectiveTime;

    /**
     * 失效时间
     */
    private String deadTime;


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
    private String typeName;
}
