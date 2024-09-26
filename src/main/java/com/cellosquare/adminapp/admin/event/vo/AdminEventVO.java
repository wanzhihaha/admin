package com.cellosquare.adminapp.admin.event.vo;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminEventVO extends GeneralVO {
	
	// 이벤트 시퀀스 
	private String evtSeqNo;
	// 메타 시퀀스
	private String metaSeqNo;
	// 언어 코드
	private String langCd;
	// 제목
	private String titleNm;
	// 요약정보
	private String summaryInfo;
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
	// PC Detail Image 경로
	private String pcDetlImgPath;
	// PC Detail Image 원본파일 명
	private String pcDetlImgOrgFileNm;
	// PC Detail Image 파일 명
	private String pcDetlImgFileNm;
	// PC Detail Image 사이즈
	private String pcDetlImgSize;
	// PC Detail Image alt
	private String pcDetlImgAlt;
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
	// Mobile Detail Image 경로
	private String mobileDetlImgPath;
	// Mobile Detail Image 경로
	private String mobileDetlImgOrgFileNm;
	// Mobile Detail Image 원본파일 명
	private String mobileDetlImgFileNm;
	// Mobile Detail Image 사이즈
	private String mobileDetlImgSize;
	// Mobile Detail Image alt
	private String mobileDetlImgAlt;
	// Event 상세 내용
	private String evtDetlContent;
	// Event 시작일
	private String evtStatDtm;
	// Event 종료일
	private String evtEndDtm;
	// 새창 링크 유무
	private String newWindowLinkYn;
	// 새창 링크
	private String newLink;
	// 사용유무
	private String useYn;
	// 조회수
	private String srchCnt;
	// 정렬순서
	private String ordb;
	// 이벤트 구분
	private String evtCcd;
	// 이벤트 구분 명
	private String evtCcdNm;
	
	// input file
	private MultipartFile pcListOrginFile;
	private MultipartFile pcDetailOrginFile;
	private MultipartFile mobileListOrginFile;
	private MultipartFile mobileDetailOrginFile;
	
	// 사용 유무 명
	private String useYnNm;
	// 링크 사용 유무 명
	private String newWindowLinkYnNm;
	// 파일 삭제여부
	private String pcListFileDel;
	private String pcDetailFileDel;
	private String mobileListFileDel;
	private String mobileDetailFileDel;
	// 리스트 정렬 저장
	private String[] listOrdb;
	private String[] listEvtSeqNo;
	// imgView조건 구분자
	private String imgKinds;
	
	
	public String getEvtCcdNm() {
		return evtCcdNm;
	}
	public void setEvtCcdNm(String evtCcdNm) {
		this.evtCcdNm = evtCcdNm;
	}
	public String getEvtSeqNo() {
		return evtSeqNo;
	}
	public void setEvtSeqNo(String evtSeqNo) {
		this.evtSeqNo = evtSeqNo;
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
	public String getEvtDetlContent() {
		return evtDetlContent;
	}
	public void setEvtDetlContent(String evtDetlContent) {
		this.evtDetlContent = evtDetlContent;
	}
	public String getEvtStatDtm() {
		return evtStatDtm;
	}
	public void setEvtStatDtm(String evtStatDtm) {
		this.evtStatDtm = evtStatDtm;
	}
	public String getEvtEndDtm() {
		return evtEndDtm;
	}
	public void setEvtEndDtm(String evtEndDtm) {
		this.evtEndDtm = evtEndDtm;
	}
	public String getNewWindowLinkYn() {
		return newWindowLinkYn;
	}
	public void setNewWindowLinkYn(String newWindowLinkYn) {
		this.newWindowLinkYn = newWindowLinkYn;
	}
	public String getNewLink() {
		return newLink;
	}
	public void setNewLink(String newLink) {
		this.newLink = newLink;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSrchCnt() {
		return srchCnt;
	}
	public void setSrchCnt(String srchCnt) {
		this.srchCnt = srchCnt;
	}
	public String getOrdb() {
		return ordb;
	}
	public void setOrdb(String ordb) {
		this.ordb = ordb;
	}
	public MultipartFile getPcListOrginFile() {
		return pcListOrginFile;
	}
	public void setPcListOrginFile(MultipartFile pcListOrginFile) {
		this.pcListOrginFile = pcListOrginFile;
	}
	public MultipartFile getPcDetailOrginFile() {
		return pcDetailOrginFile;
	}
	public void setPcDetailOrginFile(MultipartFile pcDetailOrginFile) {
		this.pcDetailOrginFile = pcDetailOrginFile;
	}
	public MultipartFile getMobileListOrginFile() {
		return mobileListOrginFile;
	}
	public void setMobileListOrginFile(MultipartFile mobileListOrginFile) {
		this.mobileListOrginFile = mobileListOrginFile;
	}
	public MultipartFile getMobileDetailOrginFile() {
		return mobileDetailOrginFile;
	}
	public void setMobileDetailOrginFile(MultipartFile mobileDetailOrginFile) {
		this.mobileDetailOrginFile = mobileDetailOrginFile;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getNewWindowLinkYnNm() {
		return newWindowLinkYnNm;
	}
	public void setNewWindowLinkYnNm(String newWindowLinkYnNm) {
		this.newWindowLinkYnNm = newWindowLinkYnNm;
	}
	public String getPcListFileDel() {
		return pcListFileDel;
	}
	public void setPcListFileDel(String pcListFileDel) {
		this.pcListFileDel = pcListFileDel;
	}
	public String getPcDetailFileDel() {
		return pcDetailFileDel;
	}
	public void setPcDetailFileDel(String pcDetailFileDel) {
		this.pcDetailFileDel = pcDetailFileDel;
	}
	public String getMobileListFileDel() {
		return mobileListFileDel;
	}
	public void setMobileListFileDel(String mobileListFileDel) {
		this.mobileListFileDel = mobileListFileDel;
	}
	public String getMobileDetailFileDel() {
		return mobileDetailFileDel;
	}
	public void setMobileDetailFileDel(String mobileDetailFileDel) {
		this.mobileDetailFileDel = mobileDetailFileDel;
	}
	public String[] getListOrdb() {
		return listOrdb;
	}
	public void setListOrdb(String[] listOrdb) {
		this.listOrdb = listOrdb;
	}
	public String[] getListEvtSeqNo() {
		return listEvtSeqNo;
	}
	public void setListEvtSeqNo(String[] listEvtSeqNo) {
		this.listEvtSeqNo = listEvtSeqNo;
	}
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public String getEvtCcd() {
		return evtCcd;
	}
	public void setEvtCcd(String evtCcd) {
		this.evtCcd = evtCcd;
	}
	
}
