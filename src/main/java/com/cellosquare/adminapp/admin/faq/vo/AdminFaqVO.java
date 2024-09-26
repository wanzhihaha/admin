package com.cellosquare.adminapp.admin.faq.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminFaqVO extends GeneralVO {

	// 시퀀스
	private String  faqSeq;
	// 언어코드
	private String  langCd;
	// faq 구분
	private String  faqCcd;
	// faq 구분 명
	private String  faqCcdNm;
	// 제목
	private String  titleNm;
	// 내용
	private String  faqDetlContent;
	// 사용유무
	private String  useYn;
	// 사용유무 명
	private String  useYnNm;
	// 조회수
	private String  srchCnt;
	// 정렬
	private String  ordb;
	// 리스트 정렬 저장
	private String[] listSortOrder;
	private String[] listfaqSeq;
	
	public String getFaqSeq() {
		return faqSeq;
	}
	public void setFaqSeq(String faqSeq) {
		this.faqSeq = faqSeq;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getFaqCcd() {
		return faqCcd;
	}
	public void setFaqCcd(String faqCcd) {
		this.faqCcd = faqCcd;
	}
	public String getFaqCcdNm() {
		return faqCcdNm;
	}
	public void setFaqCcdNm(String faqCcdNm) {
		this.faqCcdNm = faqCcdNm;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getFaqDetlContent() {
		return faqDetlContent;
	}
	public void setFaqDetlContent(String faqDetlContent) {
		this.faqDetlContent = faqDetlContent;
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
	public String[] getListfaqSeq() {
		return listfaqSeq;
	}
	public void setListfaqSeq(String[] listfaqSeq) {
		this.listfaqSeq = listfaqSeq;
	}
	
}
