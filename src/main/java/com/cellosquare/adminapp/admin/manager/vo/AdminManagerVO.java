package com.cellosquare.adminapp.admin.manager.vo;

import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;

public class AdminManagerVO extends AdminLoginVO {
	// 시퀀스
	private String admMngSeqNo;
	// 아이디
	private String admUserId;
	// 이름
	private String admUserNm;
	// 이메일
	private String admEmailAddr;
	// 권한
	private String admAuth;
	private String admAuthNm;
	// 최근 접속 시간
	private String finalLogDtm;
	// 패스워드
	private String admPw;
	// salt
	private String saltVal;
	// 임시비밀번호 상태
	private String tempPwSts;
	private String tempPwStsNm;
	// 사용유무
	private String useYn;
	private String useYnNm;
	private int loginCnt;
	
	public String getAdmMngSeqNo() {
		return admMngSeqNo;
	}
	public void setAdmMngSeqNo(String admMngSeqNo) {
		this.admMngSeqNo = admMngSeqNo;
	}
	public String getAdmUserId() {
		return admUserId;
	}
	public void setAdmUserId(String admUserId) {
		this.admUserId = admUserId;
	}
	public String getAdmUserNm() {
		return admUserNm;
	}
	public void setAdmUserNm(String admUserNm) {
		this.admUserNm = admUserNm;
	}
	public String getAdmEmailAddr() {
		return admEmailAddr;
	}
	public void setAdmEmailAddr(String admEmailAddr) {
		this.admEmailAddr = admEmailAddr;
	}
	public String getAdmAuth() {
		return admAuth;
	}
	public void setAdmAuth(String admAuth) {
		this.admAuth = admAuth;
	}
	public String getAdmAuthNm() {
		return admAuthNm;
	}
	public void setAdmAuthNm(String admAuthNm) {
		this.admAuthNm = admAuthNm;
	}
	public String getAdmPw() {
		return admPw;
	}
	public void setAdmPw(String admPw) {
		this.admPw = admPw;
	}
	public String getSaltVal() {
		return saltVal;
	}
	public void setSaltVal(String saltVal) {
		this.saltVal = saltVal;
	}
	public String getTempPwSts() {
		return tempPwSts;
	}
	public void setTempPwSts(String tempPwSts) {
		this.tempPwSts = tempPwSts;
	}
	public String getTempPwStsNm() {
		return tempPwStsNm;
	}
	public void setTempPwStsNm(String tempPwStsNm) {
		this.tempPwStsNm = tempPwStsNm;
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
	public String getFinalLogDtm() {
		return finalLogDtm;
	}
	public void setFinalLogDtm(String finalLogDtm) {
		this.finalLogDtm = finalLogDtm;
	}
	public int getLoginCnt() {
		return loginCnt;
	}
	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}
	
}
