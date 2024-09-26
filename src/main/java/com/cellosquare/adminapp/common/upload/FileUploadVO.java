package main.java.com.cellosquare.adminapp.common.upload;

import com.cellosquare.adminapp.common.vo.GeneralVO;

/**
 * <pre>
 * Class Name  : FileUploadVO.java
 * Description : 업로드 된 파일의 정보 VO
 * 
 * Modification Information
 *
 * ================================================================
 *  수정일　　　 　　                  수정자　　　 　　                  수정내용
 *  ─────────────  ─────────────  ───────────────────────────────
 *  2014. 4. 2.    sbkim           최초생성
 * ===============================================================
 * </pre>
 *
 * @author sbkim
 * @since 2014. 4. 2.
 * @version 1.0
 *
 * Copyright (C) 2014 by Bluewaves Lab All right reserved.
 */
public class FileUploadVO extends GeneralVO {

	private String mimeType;
	private String fileOriginName;
	private String fileTempName;
	private String filePath;
	private String canonicalPath;
	private String fileExtension;
	private long fileSize;
	private String inputName;
	private String fileSn;
	private String subFileSn;
	private String datePath;
	private String width;
	private String height;
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileOriginName() {
		return fileOriginName;
	}
	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}
	public String getFileTempName() {
		return fileTempName;
	}
	public void setFileTempName(String fileTempName) {
		this.fileTempName = fileTempName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getSubFileSn() {
		return subFileSn;
	}
	public void setSubFileSn(String subFileSn) {
		this.subFileSn = subFileSn;
	}
	public String getDatePath() {
		return datePath;
	}
	public void setDatePath(String datePath) {
		this.datePath = datePath;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getCanonicalPath() {
		return canonicalPath;
	}
	public void setCanonicalPath(String canonicalPath) {
		this.canonicalPath = canonicalPath;
	}
	

}
