package com.cellosquare.adminapp.admin.attachfile.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminAttachFileVO extends GeneralVO {
	// 컨텐츠 시퀀스
	private String contentsSeqNo;
	// 컨텐츠 구분
	private String contentsCcd;
	// 정렬순서
	private String ordb;
	// 첨부파일 경로
	private String atchFilePath;
	// 첨부파일 원본 명
	private String atchOrgFileNm;
	// 첨부파일 명
	private String atchFileNm;
	// 첨부파일 사이즈
	private String atchFileSize;
	
	public String getContentsSeqNo() {
		return contentsSeqNo;
	}
	public void setContentsSeqNo(String contentsSeqNo) {
		this.contentsSeqNo = contentsSeqNo;
	}
	public String getContentsCcd() {
		return contentsCcd;
	}
	public void setContentsCcd(String contentsCcd) {
		this.contentsCcd = contentsCcd;
	}
	public String getOrdb() {
		return ordb;
	}
	public void setOrdb(String ordb) {
		this.ordb = ordb;
	}
	public String getAtchFilePath() {
		return atchFilePath;
	}
	public void setAtchFilePath(String atchFilePath) {
		this.atchFilePath = atchFilePath;
	}
	public String getAtchOrgFileNm() {
		return atchOrgFileNm;
	}
	public void setAtchOrgFileNm(String atchOrgFileNm) {
		this.atchOrgFileNm = atchOrgFileNm;
	}
	public String getAtchFileNm() {
		return atchFileNm;
	}
	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}
	public String getAtchFileSize() {
		return atchFileSize;
	}
	public void setAtchFileSize(String atchFileSize) {
		this.atchFileSize = atchFileSize;
	}
}
