package com.cellosquare.adminapp.admin.mainviewlibrary.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminMainViewLibraryVO extends GeneralVO {

	// MVL 시퀀스
	private String mvLibSeqNo;
	// 언어 코드
	private String langCd;
	// MVL 구분 1 => 이벤트, 소식, 블로그, 리포트 4개
	private String mvLibCcd;
	// MVL 구분 1 명
	private String libCcd1Nm;
	// MVL SEQ 
	private String reltdLib;
	// 사용유무
	private String useYn;
	// 사용유무 명
	private String useYnNm;
	// 정렬순서 => sortOrder
	private String ordb;
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listMvLibSeq;
	
	// VO 전용 ----------
	
	// SEARCH_TITLE 
	private String searchTitle;
	// 최상위 구분자 검색
	private String searchLibCcd1;
	// LIB_CCD2
	private String libCcd2;
	private String libCcd2Nm;
	
	
	// TITLE_NM
	private String titleNm;
	// PC List image 경로 => PC LIST
	private String pcListImgPath;
	// PC List image 원본파일 명
	private String pcListImgOrgFileNm;
	// PC List image 파일 명
	private String pcListImgFileNm;
	// PC List image 사이즈
	private String pcListImgSize;
	// PC List image alt
	private String pcListImgAlt;
	// PC List Image 삭제여부
	private String pcListFileDel;	
	// 이미지 뷰
	private String imgKinds;
	
	
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
	public String getPcListFileDel() {
		return pcListFileDel;
	}
	public void setPcListFileDel(String pcListFileDel) {
		this.pcListFileDel = pcListFileDel;
	}
	public String getLibCcd2() {
		return libCcd2;
	}
	public void setLibCcd2(String libCcd2) {
		this.libCcd2 = libCcd2;
	}
	public String getLibCcd2Nm() {
		return libCcd2Nm;
	}
	public void setLibCcd2Nm(String libCcd2Nm) {
		this.libCcd2Nm = libCcd2Nm;
	}
	public String getMvLibSeqNo() {
		return mvLibSeqNo;
	}
	public void setMvLibSeqNo(String mvLibSeqNo) {
		this.mvLibSeqNo = mvLibSeqNo;
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
	public String getLibCcd1Nm() {
		return libCcd1Nm;
	}
	public void setLibCcd1Nm(String libCcd1Nm) {
		this.libCcd1Nm = libCcd1Nm;
	}
	public String getReltdLib() {
		return reltdLib;
	}
	public void setReltdLib(String reltdLib) {
		this.reltdLib = reltdLib;
	}
	public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	public String getSearchLibCcd1() {
		return searchLibCcd1;
	}
	public void setSearchLibCcd1(String searchLibCcd1) {
		this.searchLibCcd1 = searchLibCcd1;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getOrdb() {
		return ordb;
	}
	public void setOrdb(String ordb) {
		this.ordb = ordb;
	}
	public String[] getListSortOrder() {
		return listSortOrder;
	}
	public void setListSortOrder(String[] listSortOrder) {
		this.listSortOrder = listSortOrder;
	}
	public String[] getListMvLibSeq() {
		return listMvLibSeq;
	}
	public void setListMvLibSeq(String[] listMvLibSeq) {
		this.listMvLibSeq = listMvLibSeq;
	}
		
}
