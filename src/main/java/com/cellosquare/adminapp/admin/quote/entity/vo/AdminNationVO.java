package com.cellosquare.adminapp.admin.quote.entity.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AdminNationVO extends GeneralVO {

    private String nationSeqNo;
    private String nationCd;
    private String nationNm;
    private String nationCnNm;
    private String continentCd;
    private String continentNm;
    private Integer isHot;
    private String isHotNm;
    private String searchKeyWord;
    private String[] listSearchKeyWord;
    private String expressUseYn;
    private String expressUseValue;
    private String imgFilePath;
    private String imgOrgFileNm;
    private String imgFileNm;
    private String imgSize;
    private String ordb;
    private String useYnNm;
    private MultipartFile imgFileUpload;
    private String[] listNationSeqNo;
    private String[] listSortOrder;
    private List<String> admUserIdList;

}
