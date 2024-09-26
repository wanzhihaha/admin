package com.cellosquare.adminapp.admin.libsubmv.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminLibSubMvVO extends GeneralVO {

	// Main View Library 시퀀스
	private String libSubMvSeqNo;
	// 언어 코드
	private String langCd;
	// Main View Library 구분
	private String mvLibCcd;
	// Related Library
	private String reltdLib;
	// 사용유무
	private String useYn;
	// 정렬순서
	private String ordb;
	
	// Main View Library 구분명
	private String mvLibCcdNm;
	// 사용유무명
	private String UseYnNm;
	
	// 각 테이블 상품 시퀀스 번호
	private String reltdSeqNo;
	// 각 테이블 제목
	private String titleNm;
	// 각 테이블 구분자명
	private String ccdsNm;
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

	// 팝업 ccd1
	private String libSubCcd1;
	// 팝업 ccd1 명
	private String libSubCcd1Nm;
	// 팝업 ccd2
	private String libSubCcd2;
	// 팝업 ccd2 명
	private String libSubCcd2Nm;
	// 팝업 검색값
	private String libSearchValue;
	
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listMvSqprdSeq;
	// 이미지종류
	private String imgKinds;
	
	
	public String getLibSubMvSeqNo() {
		return libSubMvSeqNo;
	}
	public void setLibSubMvSeqNo(String libSubMvSeqNo) {
		this.libSubMvSeqNo = libSubMvSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getMvLibCcd() {
		return mvLibCcd;
	}
	public void setMvLibCcd(String mvLibCcd) {
		this.mvLibCcd = mvLibCcd;
	}
	public String getReltdLib() {
		return reltdLib;
	}
	public void setReltdLib(String reltdLib) {
		this.reltdLib = reltdLib;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getOrdb() {
		return ordb;
	}
	public void setOrdb(String ordb) {
		this.ordb = ordb;
	}
	public String getMvLibCcdNm() {
		return mvLibCcdNm;
	}
	public void setMvLibCcdNm(String mvLibCcdNm) {
		this.mvLibCcdNm = mvLibCcdNm;
	}
	public String getUseYnNm() {
		return UseYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		UseYnNm = useYnNm;
	}
	public String getReltdSeqNo() {
		return reltdSeqNo;
	}
	public void setReltdSeqNo(String reltdSeqNo) {
		this.reltdSeqNo = reltdSeqNo;
	}
	public String getCcdsNm() {
		return ccdsNm;
	}
	public void setCcdsNm(String ccdsNm) {
		this.ccdsNm = ccdsNm;
	}
	public String getPcListImgPath() {
		return pcListImgPath;
	}
	public void setPcListImgPath(String pcListImgPath) {
		this.pcListImgPath = pcListImgPath;
	}
	public String getPcListImgOrgFileNm() {
		return pcListImgOrgFileNm;
	}
	public void setPcListImgOrgFileNm(String pcListImgOrgFileNm) {
		this.pcListImgOrgFileNm = pcListImgOrgFileNm;
	}
	public String getPcListImgFileNm() {
		return pcListImgFileNm;
	}
	public void setPcListImgFileNm(String pcListImgFileNm) {
		this.pcListImgFileNm = pcListImgFileNm;
	}
	public String getPcListImgSize() {
		return pcListImgSize;
	}
	public void setPcListImgSize(String pcListImgSize) {
		this.pcListImgSize = pcListImgSize;
	}
	public String getPcListImgAlt() {
		return pcListImgAlt;
	}
	public void setPcListImgAlt(String pcListImgAlt) {
		this.pcListImgAlt = pcListImgAlt;
	}
	public String getMobileListImgPath() {
		return mobileListImgPath;
	}
	public void setMobileListImgPath(String mobileListImgPath) {
		this.mobileListImgPath = mobileListImgPath;
	}
	public String getMobileListImgOrgFileNm() {
		return mobileListImgOrgFileNm;
	}
	public void setMobileListImgOrgFileNm(String mobileListImgOrgFileNm) {
		this.mobileListImgOrgFileNm = mobileListImgOrgFileNm;
	}
	public String getMobileListImgFileNm() {
		return mobileListImgFileNm;
	}
	public void setMobileListImgFileNm(String mobileListImgFileNm) {
		this.mobileListImgFileNm = mobileListImgFileNm;
	}
	public String getMobileListImgSize() {
		return mobileListImgSize;
	}
	public void setMobileListImgSize(String mobileListImgSize) {
		this.mobileListImgSize = mobileListImgSize;
	}
	public String getMobileListImgAlt() {
		return mobileListImgAlt;
	}
	public void setMobileListImgAlt(String mobileListImgAlt) {
		this.mobileListImgAlt = mobileListImgAlt;
	}
	public String[] getListSortOrder() {
		return listSortOrder;
	}
	public void setListSortOrder(String[] listSortOrder) {
		this.listSortOrder = listSortOrder;
	}
	public String[] getListMvSqprdSeq() {
		return listMvSqprdSeq;
	}
	public void setListMvSqprdSeq(String[] listMvSqprdSeq) {
		this.listMvSqprdSeq = listMvSqprdSeq;
	}
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getLibSubCcd1() {
		return libSubCcd1;
	}
	public void setLibSubCcd1(String libSubCcd1) {
		this.libSubCcd1 = libSubCcd1;
	}
	public String getLibSubCcd1Nm() {
		return libSubCcd1Nm;
	}
	public void setLibSubCcd1Nm(String libSubCcd1Nm) {
		this.libSubCcd1Nm = libSubCcd1Nm;
	}
	public String getLibSubCcd2() {
		return libSubCcd2;
	}
	public void setLibSubCcd2(String libSubCcd2) {
		this.libSubCcd2 = libSubCcd2;
	}
	public String getLibSubCcd2Nm() {
		return libSubCcd2Nm;
	}
	public void setLibSubCcd2Nm(String libSubCcd2Nm) {
		this.libSubCcd2Nm = libSubCcd2Nm;
	}
	public String getLibSearchValue() {
		return libSearchValue;
	}
	public void setLibSearchValue(String libSearchValue) {
		this.libSearchValue = libSearchValue;
	}
	
	
}
