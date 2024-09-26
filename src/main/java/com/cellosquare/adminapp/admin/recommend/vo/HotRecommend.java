package com.cellosquare.adminapp.admin.recommend.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("mk_hot_product")
public class HotRecommend implements Serializable {
    // 사용유무 명
    @TableField(exist = false)
    private String useYnNm;
    private String title;
    private String link;
    private String type;
    // 언어 코드
    private String langCd;
    private Integer ordb;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(exist = false)
    private Integer[] listOrdb;
    @TableField(exist = false)
    private Integer[] listRecommendSeqNo;
    private String srchCnt;
    private String useYn;
    /**
     * 최초 등록 일시
     */
    private Timestamp insDtm;
    /**
     * 최초 등록 아이디
     */
    private String insPersonId;
    /**
     * 최초 등록자 명
     */
    @TableField(exist = false)
    private String insPersonNm;
    /**
     * 최종 수정 일시
     */
    private Timestamp updDtm;
    /**
     * 최종 수정 아이디
     */
    private String updPersonId;
    /**
     * 최종 수정자 명
     */
    @TableField(exist = false)
    private String updPersonNm;

    /**
     * 검색 타입
     */
    @TableField(exist = false)
    private String searchType;
    /**
     * 검색 값
     */
    @TableField(exist = false)
    private String searchValue;
    /**
     * 페이지 번호
     */
    @TableField(exist = false)
    private String page = "1"; //초기값 설정

    /**
     * 한 페이지에 표시될 레코드 수
     */
    @TableField(exist = false)
    private String rowPerPage = "20"; //초기값 설정
}
