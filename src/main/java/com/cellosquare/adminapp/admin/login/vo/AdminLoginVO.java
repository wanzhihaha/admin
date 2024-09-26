package com.cellosquare.adminapp.admin.login.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminLoginVO extends GeneralVO {
	private String adminId;
	
	private String passwordEncpt;
	
	private String langCd;
	
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPasswordEncpt() {
		return passwordEncpt;
	}

	public void setPasswordEncpt(String passwordEncpt) {
		this.passwordEncpt = passwordEncpt;
	}

	public String getLangCd() {
		return langCd;
	}

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	
}
