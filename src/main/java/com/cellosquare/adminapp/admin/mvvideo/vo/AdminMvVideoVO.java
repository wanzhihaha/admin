package com.cellosquare.adminapp.admin.mvvideo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

@Data
public class AdminMvVideoVO extends GeneralVO {

	// 메인 뷰 비디오 seq
	private String mvVideoSeqNo;
	// 언어코드
	private String langCd;
	private String metaSeqNo;
	// 비디오 구분 코드
	private String mvVideoCcd;
	// 메인 비디오 구분 코드명 
	private String mvVideoCcdNm;
	// 관련 비디오
	private String reltdVideo;
	// 제목
	private String titleNm;
	private String mvTitleNm;
	// 요약정보
	private String summaryInfo;
	private String shortInfo;
	private String content;
	// PC 이미지 경로
	private String pcListImgPath;
	// PC 이미지 원본 파일명
	private String pcListImgOrgFileNm;
	// PC 이미지 파일명
	private String pcListImgFileNm;
	// PC 이미지 사이즈
	private String pcListImgSize;
	// PC 이미지 설명
	private String pcListImgAlt;
	// Mobile 이미지 경로
	private String mobileListImgPath;
	// Mobile 이미지 원본 파일명
	private String mobileListImgOrgFileNm;
	// Mobile 이미지 파일명
	private String mobileListImgFileNm;
	// Mobile 이미지 사이즈
	private String mobileListImgSize;
	// Mobile 이미지 설명
	private String mobileListImgAlt;
	// 사용 유무
	private String useYn;
	// 사용 유무 명
	private String useYnNm;
	// 정렬 순서
	private String ordb;
	// 등록 날짜
	private String insDtm;
	// 등록 아이디
	private String insPersonId;
	// 수정 날짜 
	private String updDtm;
	// 수정 아이디
	private String updPersonId;
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listMvVideoSeq;
	// input File
	private MultipartFile pcListOrginFile;
	private MultipartFile mobileListOrginFile;
	// 파일 삭제 여부
	private String pcListFileDel;
	private String mobileListFileDel;
	// 이미지종류
	private String imgKinds;

	// 비디오 검색 값
	private String videoSearchValue;
	// 연관 비디오 시퀀스
	private String relVideoSeqNo;
	// 비디오 시퀀스
	private String videoSeqNo;
	// 비디오 카테고리
	private String videoCcd;
	private String videoCcdNm;
	// Writer Info
	private String wrtInfo1;
	private String wrtInfo2;

	private MultipartFile video;
	private MultipartFile shortVideo;

}
