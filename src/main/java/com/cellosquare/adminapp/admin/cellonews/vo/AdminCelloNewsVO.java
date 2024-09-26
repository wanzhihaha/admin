package com.cellosquare.adminapp.admin.cellonews.vo;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminCelloNewsVO extends GeneralVO{
	// 시퀀스
	private String newsSeqNo;
	// meta 시퀀스
	private String metaSeqNo;
	// 언어코드
	private String langCd;
	// 구분1
	private String newsCcd1;
	// 구분2
	private String newsCcd2;
	// 제목
	private String titleNm;
	// 요약정보
	private String summaryInfo;
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
	// PC 이미지 경로
	private String pcDetlImgPath;
	// PC 이미지 원본 파일명
	private String pcDetlImgOrgFileNm;
	// PC 이미지 파일명
	private String pcDetlImgFileNm;
	// PC 이미지 사이즈
	private String pcDetlImgSize;
	// PC 이미지 설명
	private String pcDetlImgAlt;
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
	// Mobile 이미지 경로
	private String mobileDetlImgPath;
	// Mobile 이미지 원본 파일명
	private String mobileDetlImgOrgFileNm;
	// Mobile 이미지 파일명
	private String mobileDetlImgFileNm;
	// Mobile 이미지 사이즈
	private String mobileDetlImgSize;
	// Mobile 이미지 설명
	private String mobileDetlImgAlt;
	// 첼로소식 상세내용
	private String newsDetlInfo;
	// 사용 유무
	private String useYn;
	// 사용유무 명
	private String useYnNm;
	// 조회수
	private String srchCnt;
	// 소식 구분명
	private String newsCcdNm;
	
	private MultipartFile orginlFile;
	
	private String thumbFileDel;
	
	// 정렬
	private String ordb;
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listNewsSeq;
	
	// input file
	private MultipartFile pcListOrginFile;
	private MultipartFile pcDetailOrginFile;
	private MultipartFile mobileListOrginFile;
	private MultipartFile mobileDetailOrginFile;
	// 파일 삭제여부
	private String pcListFileDel;
	private String pcDetailFileDel;
	private String mobileListFileDel;
	private String mobileDetailFileDel;
	// 이미지종류
	private String imgKinds;
	
	public String getNewsSeqNo() {
		return newsSeqNo;
	}
	public void setNewsSeqNo(String newsSeqNo) {
		this.newsSeqNo = newsSeqNo;
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
	public String getNewsCcd1() {
		return newsCcd1;
	}
	public void setNewsCcd1(String newsCcd1) {
		this.newsCcd1 = newsCcd1;
	}
	public String getNewsCcd2() {
		return newsCcd2;
	}
	public void setNewsCcd2(String newsCcd2) {
		this.newsCcd2 = newsCcd2;
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
	public String getNewsDetlInfo() {
		return newsDetlInfo;
	}
	public void setNewsDetlInfo(String newsDetlInfo) {
		this.newsDetlInfo = newsDetlInfo;
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
	public String getNewsCcdNm() {
		return newsCcdNm;
	}
	public void setNewCcdNm(String newsCcdNm) {
		this.newsCcdNm = newsCcdNm;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public MultipartFile getOrginlFile() {
		return orginlFile;
	}
	public void setOrginlFile(MultipartFile orginlFile) {
		this.orginlFile = orginlFile;
	}
	public String getThumbFileDel() {
		return thumbFileDel;
	}
	public void setThumbFileDel(String thumbFileDel) {
		this.thumbFileDel = thumbFileDel;
	}
	public String[] getListSortOrder() {
		return listSortOrder;
	}
	public void setListSortOrder(String[] listSortOrder) {
		this.listSortOrder = listSortOrder;
	}
	public String[] getListNewsSeq() {
		return listNewsSeq;
	}
	public void setListNewsSeq(String[] listNewsSeq) {
		this.listNewsSeq = listNewsSeq;
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
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public void setNewsCcdNm(String newsCcdNm) {
		this.newsCcdNm = newsCcdNm;
	}
	
	
	
	
}
