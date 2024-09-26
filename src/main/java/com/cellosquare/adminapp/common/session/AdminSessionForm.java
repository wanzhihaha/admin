package main.java.com.cellosquare.adminapp.common.session;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class AdminSessionForm implements Serializable {

	private static final long serialVersionUID = 6495918217336133042L;
	
	private String adminSn;
	
	private String adminSeCode;
	
	private String adminId;
		
	private String adminNm;
	
	private String sessionId;
	
	private String langCd;

	public String getAdminSn() {
		return adminSn;
	}

	public void setAdminSn(String adminSn) {
		this.adminSn = adminSn;
	}

	public String getAdminSeCode() {
		return adminSeCode;
	}

	public void setAdminSeCode(String adminSeCode) {
		this.adminSeCode = adminSeCode;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminNm() {
		return adminNm;
	}

	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLangCd() {
		return langCd;
	}

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
