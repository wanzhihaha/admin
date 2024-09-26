package com.cellosquare.adminapp.admin.termsofservice.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminTermsOfServiceVO extends GeneralVO {

	// tos 시퀀스
	private String tosSeqNo;
	// 언어 코드
	private String langCd;
	// tos 구분
	private String tosCcd;
	// tos 구분 명
	private String tosCcdNm;
	// 제목 
	private String titleNm;
	// 내용
	private String detlInfo;
	// memo
	private String memo;
	// version
	private String tosVer;
	// 약관동의
	private String reqSts;
	// 약관동의 명
	private String reqStsNm;
	// 사용 유무
	private String useYn;
	// 사용유무 명
	private String useYnNm;
	
	public String getReqStsNm() {
		return reqStsNm;
	}
	public void setReqStsNm(String reqStsNm) {
		this.reqStsNm = reqStsNm;
	}
	public String getTosSeqNo() {
		return tosSeqNo;
	}
	public void setTosSeqNo(String tosSeqNo) {
		this.tosSeqNo = tosSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getTosCcd() {
		return tosCcd;
	}
	public void setTosCcd(String tosCcd) {
		this.tosCcd = tosCcd;
	}
	public String getTosCcdNm() {
		return tosCcdNm;
	}
	public void setTosCcdNm(String tosCcdNm) {
		this.tosCcdNm = tosCcdNm;
	}
	public String getTitleNm() {
		return titleNm;
	}
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	public String getDetlInfo() {
		return detlInfo;
	}
	public void setDetlInfo(String detlInfo) {
		this.detlInfo = detlInfo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTosVer() {
		return tosVer;
	}
	public void setTosVer(String tosVer) {
		this.tosVer = tosVer;
	}
	public String getReqSts() {
		return reqSts;
	}
	public void setReqSts(String reqSts) {
		this.reqSts = reqSts;
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
	
}
