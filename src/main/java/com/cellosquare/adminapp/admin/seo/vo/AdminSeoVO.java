package com.cellosquare.adminapp.admin.seo.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@SuppressWarnings({"findbugs:SE_BAD_FIELD"})
public class AdminSeoVO implements Serializable {
	private static final long serialVersionUID = 4090925527006450336L;
	// 시퀀스
	private String metaSeqNo;
	// 메타 타이틀
	private String metaTitleNm;
	// 메타 설명
	private String metaDesc;
	// og 이미지 경로
	private String ogImgPath;
	// og 원본 파일명
	private String ogImgOrgFileNm;
	// og 파일명
	private String ogImgFileNm;
	// og 파일 사이즈
	private String ogImgSize;
	// 사용여부
	private String useYn;
	private String keyWords;
	// upload Param
	private transient MultipartFile ogFile;
	// 파일 삭제여부
	private String ogFileDel;

	private String businessId;
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getMetaSeqNo() {
		return metaSeqNo;
	}
	public void setMetaSeqNo(String metaSeqNo) {
		this.metaSeqNo = metaSeqNo;
	}
	public String getMetaTitleNm() {
		return metaTitleNm;
	}
	public void setMetaTitleNm(String metaTitleNm) {
		this.metaTitleNm = metaTitleNm;
	}
	public String getMetaDesc() {
		return metaDesc;
	}
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	public String getOgImgPath() {
		return ogImgPath;
	}
	public void setOgImgPath(String ogImgPath) {
		this.ogImgPath = ogImgPath;
	}
	public String getOgImgOrgFileNm() {
		return ogImgOrgFileNm;
	}
	public void setOgImgOrgFileNm(String ogImgOrgFileNm) {
		this.ogImgOrgFileNm = ogImgOrgFileNm;
	}
	public String getOgImgFileNm() {
		return ogImgFileNm;
	}
	public void setOgImgFileNm(String ogImgFileNm) {
		this.ogImgFileNm = ogImgFileNm;
	}
	public String getOgImgSize() {
		return ogImgSize;
	}
	public void setOgImgSize(String ogImgSize) {
		this.ogImgSize = ogImgSize;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public MultipartFile getOgFile() {
		return ogFile;
	}
	public void setOgFile(MultipartFile ogFile) {
		this.ogFile = ogFile;
	}
	public String getOgFileDel() {
		return ogFileDel;
	}
	public void setOgFileDel(String ogFileDel) {
		this.ogFileDel = ogFileDel;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
