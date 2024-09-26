package com.cellosquare.adminapp.admin.libsubmvevent.vo;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminLibSubMvEventVO extends GeneralVO{

	// seq
	private String libSubMevSeqNo;
	// 언어코드
	private String langCd;
	// 연관 이벤트
	private String reltdEvt;
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
	// 사용 유무명
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
	private String[] listMvEvtSeq;
	// 이미지종류
	private String imgKinds;
	// 상품 검색 값
	private String eventSearchValue;
	// 연관 이벤트 시퀀스
	private String relEvtSeqNo;
	// 이벤트 시퀀스
	private String evtSeqNo;
	// 이벤트 카테고리
	private String evtCcd;
	private String evtCcdNm;
	// 이벤트 이름
	private String titleNm;
	
	// input File
	private MultipartFile pcListOrginFile;
	private MultipartFile mobileListOrginFile;
	// 파일 삭제 여부
	private String pcListFileDel;
	private String mobileListFileDel;
	
	public String getLibSubMevSeqNo() {
		return libSubMevSeqNo;
	}
	public void setLibSubMevSeqNo(String libSubMevSeqNo) {
		this.libSubMevSeqNo = libSubMevSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getReltdEvt() {
		return reltdEvt;
	}
	public void setReltdEvt(String reltdEvt) {
		this.reltdEvt = reltdEvt;
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
	public String getInsDtm() {
		return insDtm;
	}
	public void setInsDtm(String insDtm) {
		this.insDtm = insDtm;
	}
	public String getInsPersonId() {
		return insPersonId;
	}
	public void setInsPersonId(String insPersonId) {
		this.insPersonId = insPersonId;
	}
	public String getUpdDtm() {
		return updDtm;
	}
	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}
	public String getUpdPersonId() {
		return updPersonId;
	}
	public void setUpdPersonId(String updPersonId) {
		this.updPersonId = updPersonId;
	}
	public String[] getListSortOrder() {
		return listSortOrder;
	}
	public void setListSortOrder(String[] listSortOrder) {
		this.listSortOrder = listSortOrder;
	}
	public String[] getListMvEvtSeq() {
		return listMvEvtSeq;
	}
	public void setListMvEvtSeq(String[] listMvEvtSeq) {
		this.listMvEvtSeq = listMvEvtSeq;
	}
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public String getEventSearchValue() {
		return eventSearchValue;
	}
	public void setEventSearchValue(String eventSearchValue) {
		this.eventSearchValue = eventSearchValue;
	}
	public String getRelEvtSeqNo() {
		return relEvtSeqNo;
	}
	public void setRelEvtSeqNo(String relEvtSeqNo) {
		this.relEvtSeqNo = relEvtSeqNo;
	}
	public String getEvtSeqNo() {
		return evtSeqNo;
	}
	public void setEvtSeqNo(String evtSeqNo) {
		this.evtSeqNo = evtSeqNo;
	}
	public String getEvtCcd() {
		return evtCcd;
	}
	public void setEvtCcd(String evtCcd) {
		this.evtCcd = evtCcd;
	}
	public String getEvtCcdNm() {
		return evtCcdNm;
	}
	public void setEvtCcdNm(String evtCcdNm) {
		this.evtCcdNm = evtCcdNm;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public MultipartFile getPcListOrginFile() {
		return pcListOrginFile;
	}
	public void setPcListOrginFile(MultipartFile pcListOrginFile) {
		this.pcListOrginFile = pcListOrginFile;
	}
	public MultipartFile getMobileListOrginFile() {
		return mobileListOrginFile;
	}
	public void setMobileListOrginFile(MultipartFile mobileListOrginFile) {
		this.mobileListOrginFile = mobileListOrginFile;
	}
	public String getPcListFileDel() {
		return pcListFileDel;
	}
	public void setPcListFileDel(String pcListFileDel) {
		this.pcListFileDel = pcListFileDel;
	}
	public String getMobileListFileDel() {
		return mobileListFileDel;
	}
	public void setMobileListFileDel(String mobileListFileDel) {
		this.mobileListFileDel = mobileListFileDel;
	}
	
	
}
