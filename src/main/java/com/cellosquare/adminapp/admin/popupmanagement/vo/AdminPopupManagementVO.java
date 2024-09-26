package com.cellosquare.adminapp.admin.popupmanagement.vo;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminPopupManagementVO extends GeneralVO {

	// pop 시퀀스
	private String popSeqNo;
	// Period 시작일
	private String peridStatDate;
	// Period 종료일
	private String peridEndDate;
	// link url 새창
	private String linkUrl;
	// 제목 
	private String titleNm;
	// Pc Pop ccd 구분 값
	private String pcPopCcd;
	// Pc Pop ccd 구분 값 명
	private String pcPopCcdNm;
	// 사용 유무
	private String useYn;
	// 사용유무 명
	private String useYnNm;
	// 언어 코드
	private String langCd;
	// 이미지 뷰
	private String imgKinds;
	
	// PC Popup Image 부분
	
	// PC Popup Size 넓이
	private String pcPopSizeWdt;
	// PC Popup Size 높이
	private String pcPopSizeHgt;
	// PC Popup Location Top
	private String pcPopLocTop;
	// PC Popup Location Left
	private String pcPopLocLeft;
	// PC Popup Image 경로
	private String pcPopImgPath;
	// PC Popup Image 원본파일 명
	private String pcPopImgOrgFileNm;
	// PC Popup Image 파일 명
	private String pcPopImgFileNm;
	// PC Popup Image 사이즈
	private String pcPopImgSize;
	// PC Popup Image alt
	private String pcPopImgAlt;
	// PC Popup Image Upload
	private MultipartFile pcPopImgUpload;
	// PC Popup Image 삭제여부
	private String pcPopFileDel;	
	
	// MOBILE Popup Image 부분
	
	// MOBILE Popup Size 넓이
	private String mobilePopSizeWdt;
	// MOBILE Popup Size 높이
	private String mobilePopSizeHgt;
	// MOBILE Popup Image 경로
	private String mobilePopImgPath;
	// MOBILE Popup Image 원본파일 명
	private String mobilePopImgOrgFileNm;
	// MOBILE Popup Image 파일 명
	private String mobilePopImgFileNm;
	// MOBILE Popup Image 사이즈
	private String mobilePopImgSize;
	// MOBILE Popup Image alt
	private String mobilePopImgAlt;
	// MOBILE Popup Image Upload
	private MultipartFile mobilePopImgUpload;
	// MOBILE Popup Image 삭제여부
	private String mobilePopFileDel;
	
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getPcPopCcdNm() {
		return pcPopCcdNm;
	}
	public void setPcPopCcdNm(String pcPopCcdNm) {
		this.pcPopCcdNm = pcPopCcdNm;
	}
	public String getPopSeqNo() {
		return popSeqNo;
	}
	public void setPopSeqNo(String popSeqNo) {
		this.popSeqNo = popSeqNo;
	}
	public String getPeridStatDate() {
		return peridStatDate;
	}
	public void setPeridStatDate(String peridStatDate) {
		this.peridStatDate = peridStatDate;
	}
	public String getPeridEndDate() {
		return peridEndDate;
	}
	public void setPeridEndDate(String peridEndDate) {
		this.peridEndDate = peridEndDate;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getPcPopCcd() {
		return pcPopCcd;
	}
	public void setPcPopCcd(String pcPopCcd) {
		this.pcPopCcd = pcPopCcd;
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
	public String getPcPopSizeWdt() {
		return pcPopSizeWdt;
	}
	public void setPcPopSizeWdt(String pcPopSizeWdt) {
		this.pcPopSizeWdt = pcPopSizeWdt;
	}
	public String getPcPopSizeHgt() {
		return pcPopSizeHgt;
	}
	public void setPcPopSizeHgt(String pcPopSizeHgt) {
		this.pcPopSizeHgt = pcPopSizeHgt;
	}
	public String getPcPopLocTop() {
		return pcPopLocTop;
	}
	public void setPcPopLocTop(String pcPopLocTop) {
		this.pcPopLocTop = pcPopLocTop;
	}
	public String getPcPopLocLeft() {
		return pcPopLocLeft;
	}
	public void setPcPopLocLeft(String pcPopLocLeft) {
		this.pcPopLocLeft = pcPopLocLeft;
	}
	public String getPcPopImgPath() {
		return pcPopImgPath;
	}
	public void setPcPopImgPath(String pcPopImgPath) {
		this.pcPopImgPath = pcPopImgPath;
	}
	public String getPcPopImgOrgFileNm() {
		return pcPopImgOrgFileNm;
	}
	public void setPcPopImgOrgFileNm(String pcPopImgOrgFileNm) {
		this.pcPopImgOrgFileNm = pcPopImgOrgFileNm;
	}
	public String getPcPopImgFileNm() {
		return pcPopImgFileNm;
	}
	public void setPcPopImgFileNm(String pcPopImgFileNm) {
		this.pcPopImgFileNm = pcPopImgFileNm;
	}
	public String getPcPopImgSize() {
		return pcPopImgSize;
	}
	public void setPcPopImgSize(String pcPopImgSize) {
		this.pcPopImgSize = pcPopImgSize;
	}
	public String getPcPopImgAlt() {
		return pcPopImgAlt;
	}
	public void setPcPopImgAlt(String pcPopImgAlt) {
		this.pcPopImgAlt = pcPopImgAlt;
	}
	public MultipartFile getPcPopImgUpload() {
		return pcPopImgUpload;
	}
	public void setPcPopImgUpload(MultipartFile pcPopImgUpload) {
		this.pcPopImgUpload = pcPopImgUpload;
	}
	public String getPcPopFileDel() {
		return pcPopFileDel;
	}
	public void setPcPopFileDel(String pcPopFileDel) {
		this.pcPopFileDel = pcPopFileDel;
	}
	public String getMobilePopSizeWdt() {
		return mobilePopSizeWdt;
	}
	public void setMobilePopSizeWdt(String mobilePopSizeWdt) {
		this.mobilePopSizeWdt = mobilePopSizeWdt;
	}
	public String getMobilePopSizeHgt() {
		return mobilePopSizeHgt;
	}
	public void setMobilePopSizeHgt(String mobilePopSizeHgt) {
		this.mobilePopSizeHgt = mobilePopSizeHgt;
	}
	public String getMobilePopImgPath() {
		return mobilePopImgPath;
	}
	public void setMobilePopImgPath(String mobilePopImgPath) {
		this.mobilePopImgPath = mobilePopImgPath;
	}
	public String getMobilePopImgOrgFileNm() {
		return mobilePopImgOrgFileNm;
	}
	public void setMobilePopImgOrgFileNm(String mobilePopImgOrgFileNm) {
		this.mobilePopImgOrgFileNm = mobilePopImgOrgFileNm;
	}
	public String getMobilePopImgFileNm() {
		return mobilePopImgFileNm;
	}
	public void setMobilePopImgFileNm(String mobilePopImgFileNm) {
		this.mobilePopImgFileNm = mobilePopImgFileNm;
	}
	public String getMobilePopImgSize() {
		return mobilePopImgSize;
	}
	public void setMobilePopImgSize(String mobilePopImgSize) {
		this.mobilePopImgSize = mobilePopImgSize;
	}
	public String getMobilePopImgAlt() {
		return mobilePopImgAlt;
	}
	public void setMobilePopImgAlt(String mobilePopImgAlt) {
		this.mobilePopImgAlt = mobilePopImgAlt;
	}
	public MultipartFile getMobilePopImgUpload() {
		return mobilePopImgUpload;
	}
	public void setMobilePopImgUpload(MultipartFile mobilePopImgUpload) {
		this.mobilePopImgUpload = mobilePopImgUpload;
	}
	public String getMobilePopFileDel() {
		return mobilePopFileDel;
	}
	public void setMobilePopFileDel(String mobilePopFileDel) {
		this.mobilePopFileDel = mobilePopFileDel;
	}	

}
