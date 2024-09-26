package com.cellosquare.adminapp.admin.mvsqprd.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminMvSqprdVO extends GeneralVO {

	// 메인 뷰 프로덕트 seq
	private String mvSqprdSeqNo;
	// 언어코드
	private String langCd;
	// 메인 상품 구분 코드
	private String mvSqprdCcd;
	// 메인 상품 구분 코드명 
	private String mvCcdNm;
	// 관련 상품
	private String reltdSqprd;
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
	private String[] listMvSqprdSeq;
	// 이미지종류
	private String imgKinds;

	// 상품 검색 값
	private String goodsSearchValue;
	// 연관 상품 시퀀스
	private String relsqprdSeqNo;
	// 상품 시퀀스
	private String sqprdSeqNo;
	// 상품 카테고리
	private String sqprdCtgry;
	private String sqprdCtgryNm;
	// 상품 이름
	private String sqprdNm;
	
	
	public String getSqprdNm() {
		return sqprdNm;
	}
	public void setSqprdNm(String sqprdNm) {
		this.sqprdNm = sqprdNm;
	}
	public String getSqprdCtgryNm() {
		return sqprdCtgryNm;
	}
	public void setSqprdCtgryNm(String sqprdCtgryNm) {
		this.sqprdCtgryNm = sqprdCtgryNm;
	}
	public String getSqprdCtgry() {
		return sqprdCtgry;
	}
	public void setSqprdCtgry(String sqprdCtgry) {
		this.sqprdCtgry = sqprdCtgry;
	}
	public String getSqprdSeqNo() {
		return sqprdSeqNo;
	}
	public void setSqprdSeqNo(String sqprdSeqNo) {
		this.sqprdSeqNo = sqprdSeqNo;
	}
	public String getGoodsSearchValue() {
		return goodsSearchValue;
	}
	public void setGoodsSearchValue(String goodsSearchValue) {
		this.goodsSearchValue = goodsSearchValue;
	}
	public String getRelsqprdSeqNo() {
		return relsqprdSeqNo;
	}
	public void setRelsqprdSeqNo(String relsqprdSeqNo) {
		this.relsqprdSeqNo = relsqprdSeqNo;
	}
	public String getMvSqprdSeqNo() {
		return mvSqprdSeqNo;
	}
	public void setMvSqprdSeqNo(String mvSqprdSeqNo) {
		this.mvSqprdSeqNo = mvSqprdSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getMvSqprdCcd() {
		return mvSqprdCcd;
	}
	public void setMvSqprdCcd(String mvSqprdCcd) {
		this.mvSqprdCcd = mvSqprdCcd;
	}
	public String getMvCcdNm() {
		return mvCcdNm;
	}
	public void setMvCcdNm(String mvCcdNm) {
		this.mvCcdNm = mvCcdNm;
	}
	public String getReltdSqprd() {
		return reltdSqprd;
	}
	public void setReltdSqprd(String reltdSqprd) {
		this.reltdSqprd = reltdSqprd;
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
	
	
	
}
