package com.cellosquare.adminapp.admin.mvbanner.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

@Data
public class AdminMvBannerVO extends GeneralVO {

    // 메인배너 시퀀스
    private String mvBannerSeqNo;
    // 언어코드
    private String langCd;
    // 제목
    private String titleNm;
    private String content;
    // 배너 노출 시작일
    private String bannerOpenStatDate;
    // 배너 노출 종료일
    private String bannerOpenEndDate;
    // 배너 URL
    private String bannerUrl;
    // PC LIST Image 경로
    private String pcListImgPath;
    // PC LIST Image 원본파일 명
    private String pcListImgOrgFileNm;
    // PC LIST Image 파일명
    private String pcListImgFileNm;
    // PC LIST Image 사이즈
    private String pcListImgSize;
    // PC LIST Image alt
    private String pcListImgAlt;
    // Mobile List Image 경로
    private String mobileListImgPath;
    // Mobile List Image 원본파일 명
    private String mobileListImgOrgFileNm;
    // Mobile List Image 파일 명
    private String mobileListImgFileNm;
    // Mobile List Image 사이즈
    private String mobileListImgSize;
    // Mobile List Image alt
    private String mobileListImgAlt;
    // 사용유무
    private String useYn;
    // 정렬순서
    private String ordb;
    // 백그라운드 칼러
    private String bkgrColor;

    // input file
    private MultipartFile pcListOrginFile;
    private MultipartFile mobileListOrginFile;

    // 사용 유무 명
    private String useYnNm;
    // 파일 삭제여부
    private String pcListFileDel;
    private String mobileListFileDel;
    // 리스트 정렬 저장
    private String[] listOrdb;
    private String[] listBannerSeqNo;
    // imgView조건 구분자
    private String imgKinds;
    private String picType;
    private String carouselType;
    private String carouselTypeName;
}
