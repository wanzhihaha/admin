package com.cellosquare.adminapp.admin.blog.vo;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminBlogVO extends GeneralVO {

	// blog seq
	private String blogSeqNo;
	// meta seq
	private String metaSeqNo;
	// 언어 코드
	private String langCd;
	// 구분 코드
	private String blogCcd1;
	// 구분 명
	private String blogCcd1Nm;
	// 구분 코드
	private String blogCcd2;
	// 구분 명
	private String blogCcd2Nm;
	// 제목
	private String titleNm;
	// 요약 정보
	private String summaryInfo;
	// blog 상세내용
	private String detlInfo;
	// 사용 유무
	private String useYn;
	// 사용유무 명
	private String useYnNm;
	// 조회수
	private String srchCnt;
	// 이미지 뷰
	private String imgKinds;
	// 문의하기 유무
	private String iqrYn;
	private String iqrYnNm;
	
	// 정렬순서 => sortOrder
	private String ordb;
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listblogSeq;
	
	
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
	// PC List image Upload
	private MultipartFile pcListImgUpload;
	// PC List Image 삭제여부
	private String pcListFileDel;	
	
	
	// PC Detail image 경로 => PC DETL
	private String pcDetlImgPath;
	// PC Detail image 원본파일 명
	private String pcDetlImgOrgFileNm;
	// PC Detail image 파일 명
	private String pcDetlImgFileNm;
	// PC Detail image 사이즈
	private String pcDetlImgSize;
	// PC Detail image alt
	private String pcDetlImgAlt;
	// PC Detail image Upload
	private MultipartFile pcDetlImgUpload;
	// PC Detail Image 삭제여부
	private String pcDetlFileDel;
	
	
	// Mobile List image 경로 => MOBILE LIST
	private String mobileListImgPath;
	// Mobile List image 원본파일 명
	private String mobileListImgOrgFileNm;
	// Mobile List image 파일 명
	private String mobileListImgFileNm;
	// Mobile List image 사이즈
	private String mobileListImgSize;
	// Mobile List image alt
	private String mobileListImgAlt;
	// Mobile List image Upload
	private MultipartFile mobileListImgUpload;
	// Mobile List Image 삭제여부
	private String mobileListFileDel;	
	
	
	// Mobile Detail image 경로 => MOBILE DETL
	private String mobileDetlImgPath;
	// Mobile Detail image 원본파일 명
	private String mobileDetlImgOrgFileNm;
	// Mobile Detail image 파일 명
	private String mobileDetlImgFileNm;
	// Mobile Detail image 사이즈
	private String mobileDetlImgSize;
	// Mobile Detail image alt
	private String mobileDetlImgAlt;
	// MOBILE Detail image Upload
	private MultipartFile mobileDetlImgUpload;
	// MOBILE Detail Image 삭제여부
	private String mobileDetlFileDel;
	
	
	public String getBlogSeqNo() {
		return blogSeqNo;
	}
	public void setBlogSeqNo(String blogSeqNo) {
		this.blogSeqNo = blogSeqNo;
	}
	public String getMetaSeqNo() {
		return metaSeqNo;
	}
	public void setMetaSeqNo(String metaSeqNo) {
		this.metaSeqNo = metaSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getBlogCcd1() {
		return blogCcd1;
	}
	public void setBlogCcd1(String blogCcd1) {
		this.blogCcd1 = blogCcd1;
	}
	public String getBlogCcd1Nm() {
		return blogCcd1Nm;
	}
	public void setBlogCcd1Nm(String blogCcd1Nm) {
		this.blogCcd1Nm = blogCcd1Nm;
	}
	public String getBlogCcd2() {
		return blogCcd2;
	}
	public void setBlogCcd2(String blogCcd2) {
		this.blogCcd2 = blogCcd2;
	}
	public String getBlogCcd2Nm() {
		return blogCcd2Nm;
	}
	public void setBlogCcd2Nm(String blogCcd2Nm) {
		this.blogCcd2Nm = blogCcd2Nm;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getSummaryInfo() {
		return summaryInfo;
	}
	public void setSummaryInfo(String summaryInfo) {
		this.summaryInfo = summaryInfo;
	}
	public String getDetlInfo() {
		return detlInfo;
	}
	public void setDetlInfo(String detlInfo) {
		this.detlInfo = detlInfo;
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
	public String getSrchCnt() {
		return srchCnt;
	}
	public void setSrchCnt(String srchCnt) {
		this.srchCnt = srchCnt;
	}
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
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
	public String[] getListblogSeq() {
		return listblogSeq;
	}
	public void setListblogSeq(String[] listblogSeq) {
		this.listblogSeq = listblogSeq;
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
	public MultipartFile getPcListImgUpload() {
		return pcListImgUpload;
	}
	public void setPcListImgUpload(MultipartFile pcListImgUpload) {
		this.pcListImgUpload = pcListImgUpload;
	}
	public String getPcListFileDel() {
		return pcListFileDel;
	}
	public void setPcListFileDel(String pcListFileDel) {
		this.pcListFileDel = pcListFileDel;
	}
	public String getPcDetlImgPath() {
		return pcDetlImgPath;
	}
	public void setPcDetlImgPath(String pcDetlImgPath) {
		this.pcDetlImgPath = pcDetlImgPath;
	}
	public String getPcDetlImgOrgFileNm() {
		return pcDetlImgOrgFileNm;
	}
	public void setPcDetlImgOrgFileNm(String pcDetlImgOrgFileNm) {
		this.pcDetlImgOrgFileNm = pcDetlImgOrgFileNm;
	}
	public String getPcDetlImgFileNm() {
		return pcDetlImgFileNm;
	}
	public void setPcDetlImgFileNm(String pcDetlImgFileNm) {
		this.pcDetlImgFileNm = pcDetlImgFileNm;
	}
	public String getPcDetlImgSize() {
		return pcDetlImgSize;
	}
	public void setPcDetlImgSize(String pcDetlImgSize) {
		this.pcDetlImgSize = pcDetlImgSize;
	}
	public String getPcDetlImgAlt() {
		return pcDetlImgAlt;
	}
	public void setPcDetlImgAlt(String pcDetlImgAlt) {
		this.pcDetlImgAlt = pcDetlImgAlt;
	}
	public MultipartFile getPcDetlImgUpload() {
		return pcDetlImgUpload;
	}
	public void setPcDetlImgUpload(MultipartFile pcDetlImgUpload) {
		this.pcDetlImgUpload = pcDetlImgUpload;
	}
	public String getPcDetlFileDel() {
		return pcDetlFileDel;
	}
	public void setPcDetlFileDel(String pcDetlFileDel) {
		this.pcDetlFileDel = pcDetlFileDel;
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
	public MultipartFile getMobileListImgUpload() {
		return mobileListImgUpload;
	}
	public void setMobileListImgUpload(MultipartFile mobileListImgUpload) {
		this.mobileListImgUpload = mobileListImgUpload;
	}
	public String getMobileListFileDel() {
		return mobileListFileDel;
	}
	public void setMobileListFileDel(String mobileListFileDel) {
		this.mobileListFileDel = mobileListFileDel;
	}
	public String getMobileDetlImgPath() {
		return mobileDetlImgPath;
	}
	public void setMobileDetlImgPath(String mobileDetlImgPath) {
		this.mobileDetlImgPath = mobileDetlImgPath;
	}
	public String getMobileDetlImgOrgFileNm() {
		return mobileDetlImgOrgFileNm;
	}
	public void setMobileDetlImgOrgFileNm(String mobileDetlImgOrgFileNm) {
		this.mobileDetlImgOrgFileNm = mobileDetlImgOrgFileNm;
	}
	public String getMobileDetlImgFileNm() {
		return mobileDetlImgFileNm;
	}
	public void setMobileDetlImgFileNm(String mobileDetlImgFileNm) {
		this.mobileDetlImgFileNm = mobileDetlImgFileNm;
	}
	public String getMobileDetlImgSize() {
		return mobileDetlImgSize;
	}
	public void setMobileDetlImgSize(String mobileDetlImgSize) {
		this.mobileDetlImgSize = mobileDetlImgSize;
	}
	public String getMobileDetlImgAlt() {
		return mobileDetlImgAlt;
	}
	public void setMobileDetlImgAlt(String mobileDetlImgAlt) {
		this.mobileDetlImgAlt = mobileDetlImgAlt;
	}
	public MultipartFile getMobileDetlImgUpload() {
		return mobileDetlImgUpload;
	}
	public void setMobileDetlImgUpload(MultipartFile mobileDetlImgUpload) {
		this.mobileDetlImgUpload = mobileDetlImgUpload;
	}
	public String getMobileDetlFileDel() {
		return mobileDetlFileDel;
	}
	public void setMobileDetlFileDel(String mobileDetlFileDel) {
		this.mobileDetlFileDel = mobileDetlFileDel;
	}
	public String getIqrYn() {
		return iqrYn;
	}
	public void setIqrYn(String iqrYn) {
		this.iqrYn = iqrYn;
	}
	public String getIqrYnNm() {
		return iqrYnNm;
	}
	public void setIqrYnNm(String iqrYnNm) {
		this.iqrYnNm = iqrYnNm;
	}
	
}
