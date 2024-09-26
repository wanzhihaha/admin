package com.cellosquare.adminapp.common.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

/**
 * <pre>
 * Class Name  : FileDownLoadManager.java
 * Description : 파일을 다룬로드 할수 있게 도와주는 유틸리티 클래스
 * 
 * Modification Information
 *
 * ================================================================
 *  수정일　　　 　　                  수정자　　　 　　                  수정내용
 *  ─────────────  ─────────────  ───────────────────────────────
 *  2014. 4. 2.    sbkim            최초생성
 * ===============================================================
 * </pre>
 *
 * @author sbkim
 * @since 2014. 4. 2.
 * @version 1.0
 *
 * Copyright (C) 2014 by Bluewaves Lab All right reserved.
 */
public class FileDownLoadManager {

	private static final String FILE_DOWNLOAD_TOKEN = "FILE_DOWNLOAD_TOKEN";
	
	/**
	 * HttpServletResponse
	 */
	private final HttpServletResponse response;
	/**
	 * HttpServletRequest
	 */
	private final HttpServletRequest request;
	
	/**
	 * HttpServletResponse 를 반환한다
	 * @return response(HttpServletResponse)
	 */
	public HttpServletResponse getResponse() {
		return response;
	}
	
	/**
	 * HttpServletRequest 를 반환한다
	 * @return request(HttpServletRequest)
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * FileDownLoadManager 생성자
	 * @param request
	 * @param response
	 */
	public FileDownLoadManager(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 파일 출력
	 * @param file
	 * @param fileName
	 * @throws Exception
	 */
	public void fileFlush(File file, String fileName) throws Exception {
		
		//파일출력 준비
		String userAgent = this.getRequest().getHeader("User-Agent");

		if(userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
			this.getResponse().setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8") + ";");
		} else if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) { // MS IE (보통은 6.x 이상 가정)
			this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
		} else { // 모질라나 오페라
			this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("euc-kr"), "latin1") + ";");
		}
		
		/* 파일사이즈가 정확하지 않으면 출력안함 */
		if(file.length() > 0) {
			this.getResponse().setHeader("Content-Length", String.valueOf(file.length()));
		}
		
		//파일출력
		ServletOutputStream servletoutputstream = this.getResponse().getOutputStream();
		this.dumpFile(file, servletoutputstream);
		servletoutputstream.flush();
		servletoutputstream.close();
		
		//파일삭제
		/*
		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	/**
	 * 파일 출력
	 * @param file
	 * @param fileName
	 * @param isAttachment
	 * @throws Exception
	 */
	public void fileFlush(File file, String fileName, String contentType, boolean isAttachment) throws Exception {
		
		this.getResponse().setContentType(contentType);
		
		//파일출력 준비
		String userAgent = this.getRequest().getHeader("User-Agent");

		if(userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
			this.getResponse().setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8") + ";");
		} else if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) { // MS IE (보통은 6.x 이상 가정)
			if(isAttachment) {
				this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
			} else {
				this.getResponse().setHeader("Content-Disposition", "filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
			}
		} else { // 모질라나 오페라
			if(isAttachment) {
				this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("euc-kr"), "latin1") + ";");
			} else {
				this.getResponse().setHeader("Content-Disposition", "filename=" + new String(fileName.getBytes("euc-kr"), "latin1") + ";");
			}
		}
		
		/* 파일사이즈가 정확하지 않으면 출력안함 */
		if(file.length() > 0) {
			this.getResponse().setHeader("Content-Length", String.valueOf(file.length()));
		}
		
		//파일출력
		ServletOutputStream servletoutputstream = this.getResponse().getOutputStream();
		this.dumpFile(file, servletoutputstream);
		servletoutputstream.flush();
		servletoutputstream.close();
		
		//파일삭제
		/*
		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	/**
	 * 파일덤프
	 * @param file
	 * @param outputstream
	 */
	private void dumpFile(File file, OutputStream outputstream) {
		
		byte readByte[] = new byte[16*1024];
		
		BufferedInputStream bufferedinputstream = null;
		
		try {
		
			bufferedinputstream = new BufferedInputStream(new FileInputStream(file));
			
			int i;
			
			//PMD 지적사항 : Avoid assignments in operands
			//outputstream 에 write 할 경우 i 값에 assign 하여  사용함으로 정상적인 코드임
			while((i = bufferedinputstream.read(readByte, 0, 16 * 1024)) != -1) {
				outputstream.write(readByte, 0, i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bufferedinputstream != null) { try { bufferedinputstream.close(); } catch(Exception e) { e.printStackTrace(); } }
		}
	}
	
	/**
	 * <pre>
	 * 다운로드 토큰을 생성한다.
	 * </pre>
	 *
	 * @param request
	 * @param list
	 */
	public static void createDownloadToken(HttpServletRequest request, List<FileUploadVO> list) {

		Map<String, String> map = new HashMap<String, String>();
		
		if(list != null && list.size() > 0) {
			for(FileUploadVO fileUploadVO : list) {
				map.put(fileUploadVO.getFileSn(), fileUploadVO.getFileSn());
			}
		}
		
		if(map.size() > 0) {
			SessionManager.setSession(FileDownLoadManager.FILE_DOWNLOAD_TOKEN, StringUtil.combineStringArray(map.values().toArray(new String[map.size()]), ","));
		}
	}
	
	/**
	 * <pre>
	 * 다운로드 하는 대상을 검증한다.
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param fileSn
	 * @return
	 */
	public static boolean isDownloadValid(HttpServletRequest request, HttpServletResponse response, String fileSn) {
		
		boolean isValid = false;
		
		String fileDownloadToken = StringUtil.nvl((String)SessionManager.getSession(FileDownLoadManager.FILE_DOWNLOAD_TOKEN));
		
		if(!fileDownloadToken.equals("")) {
			
			for(String tokenFileSn : StringUtil.divideStringArray(fileDownloadToken, ",")) {
				
				if(tokenFileSn.equals(fileSn)) {
					
					isValid = true;
				}
			}
		}
		
		if(!isValid) {
			XmlMessageManager.historyBack(response, "comm.file.download.denied");
		}
		
		return isValid;
	}
}
