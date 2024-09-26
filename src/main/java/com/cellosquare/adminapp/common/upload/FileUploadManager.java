package main.java.com.cellosquare.adminapp.common.upload;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.DateUtil;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.common.util.FileTypeUtil;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * <pre>
 * Class Name  : FileUploadManager.java
 * Description : 파일을 업로드 할수 있게 도와주는 유틸리티 클래스
 * 
 * Modification Information
 *
 * ================================================================
 *  수정일　　　 　　                  수정자　　　 　　                  수정내용
 *  ─────────────  ─────────────  ───────────────────────────────
 *  2014. 4. 2.    김성복            최초생성
 * ===============================================================
 * </pre>
 *
 * @author 김성복
 * @since 2014. 4. 2.
 * @version 1.0
 *
 * Copyright (C) 2014 by Bluewaves Lab All right reserved.
 */
public class FileUploadManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadManager.class);
	
	private final static String allowImageExtensions = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("allowImageExtensions")));
	private final static String allowImageMimeTypes = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("allowImageMimeTypes")));
	private final static long uploadImageMaxSize = Long.parseLong(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadImageMaxSize"), "-1"));
	private final static String uploadImagePath = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadImagePath")));
	
	private final static String allowFileExtensions = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("allowFileExtensions")));
	private final static String allowFileMimeTypes = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("allowFileMimeTypes")));
	private final static long uploadFileMaxSize = Long.parseLong(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFileMaxSize"), "-1"));
	private final static String uploadFilePath = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePath")));
	
	private final static String todayMakePathYN = StringUtil.nvl(XmlPropertyManager.getPropertyValue("todayMakePathYN"), "N");
	
	public static final String DEFAULT_PATH = "DEFAULT_PATH";
	
	/**
	 * <pre>
	 * 허용 이미지 확장자를 반환한다.
	 * </pre>
	 *
	 * @return 허용 확장자 String[] 배열
	 */
	private static String[] getAllowImageExtensions() {
		
		if(allowImageExtensions != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(allowImageExtensions, ",");
			
			String[] allowExtensions = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowExtensions[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowExtensions;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 허용 파일 확장자를 반환한다.
	 * </pre>
	 *
	 * @return 허용 확장자 String[] 배열
	 */
	private static String[] getAllowFileExtensions() {
		
		if(allowFileExtensions != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(allowFileExtensions, ",");
			
			String[] allowExtensions = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowExtensions[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowExtensions;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 허용 파일 확장자를 반환한다.
	 * </pre>
	 *
	 * @param allowFileExtensions
	 * @return 허용 확장자 String[] 배열
	 */
	private static String[] getAllowFileExtensions(String allowFileExtensions) {
		
		String _allowFileExtensions = StringUtil.carriageReturnDelete(StringUtil.nvl(allowFileExtensions));
		
		if(_allowFileExtensions != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(_allowFileExtensions, ",");
			
			String[] allowExtensions = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowExtensions[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowExtensions;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 허용 이미지 MIME TYPE 을 반환한다.
	 * </pre>
	 *
	 * @return 허용 MIME TYPE String[] 배열
	 */
	private static String[] getAllowImageMimeTypes() {
		
		if(allowImageMimeTypes != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(allowImageMimeTypes, ",");
			
			String[] allowMimeTypes = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowMimeTypes[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowMimeTypes;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 허용 파일 MIME TYPE 을 반환한다.
	 * </pre>
	 *
	 * @return 허용 MIME TYPE String[] 배열
	 */
	private static String[] getAllowFileMimeTypes() {
		
		if(allowFileMimeTypes != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(allowFileMimeTypes, ",");
			
			String[] allowMimeTypes = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowMimeTypes[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowMimeTypes;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 허용 파일 MIME TYPE 을 반환한다.
	 * </pre>
	 *
	 * @param allowFileMimeTypes
	 * @return
	 */
	private static String[] getAllowFileMimeTypes(String allowFileMimeTypes) {
		
		String _allowFileMimeTypes = StringUtil.carriageReturnDelete(StringUtil.nvl(allowFileMimeTypes));
		
		if(_allowFileMimeTypes != null) {
			
			StringTokenizer stringTokenizer = new StringTokenizer(_allowFileMimeTypes, ",");
			
			String[] allowMimeTypes = new String[stringTokenizer.countTokens()];
			
			int i = 0;
			while(stringTokenizer.hasMoreTokens()) {
				
				allowMimeTypes[i++] = StringUtil.nvl(stringTokenizer.nextToken()).trim();
			}
			
			return allowMimeTypes;
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, true);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param fileInputName
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String fileInputName) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, true, fileInputName);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, true, null);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param isHistoryBack
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String fileInputName) {
		
		if(multipartHttpServletRequest != null) {
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile file = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
					
					boolean isPass = false;
					
					//파일명 가저오기
					String fileName = StringUtil.nvl(file.getOriginalFilename());
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String fileMimeType = file.getContentType();
					long fileSize = file.getSize();
					LOGGER.info("####################### [isUploadImageValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");
					
					if(getAllowImageExtensions() != null) {
						
						//파일 확장자 검사
						for(String allowExtension : getAllowImageExtensions()) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileExtension.equalsIgnoreCase(allowExtension)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//확장자 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
						}
						return false;
					}
					
					if(getAllowImageMimeTypes() != null) {
						
						//파일 마인 타입 검사
						for(String allowMimeType : getAllowImageMimeTypes()) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//파일 마인 타입 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
					try {
					InputStream is = file.getInputStream();
						if (!FileTypeUtil.getImage(is)){
							isPass = false;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}

					//용량 체크
					if(uploadImageMaxSize <= -1 || fileSize <= uploadImageMaxSize) {
						isPass = true;
					}
					
					//용량 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
				}
				
			}
		}
		
		return true;
	}

	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param allowImageExtensions
	 * @param allowImageMimeTypes
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String allowImageExtensions, String allowImageMimeTypes) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, true, null, allowImageExtensions, allowImageMimeTypes);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param isHistoryBack
	 * @param allowImageExtensions
	 * @param allowImageMimeTypes
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String allowImageExtensions, String allowImageMimeTypes) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, isHistoryBack, null, allowImageExtensions, allowImageMimeTypes);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param fileInputName
	 * @param allowImageExtensions
	 * @param allowImageMimeTypes
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String fileInputName, String allowImageExtensions, String allowImageMimeTypes) {
		
		return isUploadImageValid(multipartHttpServletRequest, response, true, fileInputName, allowImageExtensions, allowImageMimeTypes);
	}
	
	/**
	 * <pre>
	 * 업로드 한 이미지를 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param isHistoryBack
	 * @param fileInputName
	 * @param allowImageExtensions
	 * @param allowImageMimeTypes
	 * @return 이미지 파일 검증 여부
	 */
	public static boolean isUploadImageValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String fileInputName, String allowImageExtensions, String allowImageMimeTypes) {
		
		if(multipartHttpServletRequest != null) {
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile file = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
					
					boolean isPass = false;
					
					//파일명 가저오기
					String fileName = StringUtil.nvl(file.getOriginalFilename());
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String fileMimeType = file.getContentType();
					long fileSize = file.getSize();
					
					LOGGER.info("####################### [isUploadImageValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");
					
					if(allowImageExtensions != null) {
						
						String[] _allowImageExtensions = StringUtil.divideStringArray(StringUtil.carriageReturnDelete(allowImageExtensions), ",");
						
						//파일 확장자 검사
						for(String allowExtension : _allowImageExtensions) {
							
							allowExtension = StringUtil.nvl(allowExtension).trim();
							
							//허용할 확장자에 포함되어 있으면
							if(fileExtension.equalsIgnoreCase(allowExtension)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//확장자 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
						}
						return false;
					}
					
					if(allowImageMimeTypes != null) {
						
						String[] _allowImageMimeTypes = StringUtil.divideStringArray(StringUtil.carriageReturnDelete(allowImageMimeTypes), ",");
						
						//파일 마인 타입 검사
						for(String allowMimeType : _allowImageMimeTypes) {
							
							allowMimeType = StringUtil.nvl(allowMimeType).trim();
							
							//허용할 확장자에 포함되어 있으면
							if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//파일 마인 타입 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
					
					//용량 체크
					if(uploadImageMaxSize <= -1 || fileSize <= uploadImageMaxSize) {
						isPass = true;
					}
					
					//용량 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response) {
		
		return isUploadFileValid(multipartHttpServletRequest, response, true);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param fileInputName
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String fileInputName) {
		
		return isUploadFileValid(multipartHttpServletRequest, response, true, fileInputName);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack) {
		
		return isUploadFileValid(multipartHttpServletRequest, response, true, null);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param isHistoryBack
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String fileInputName) {
		return isUploadFileValid(multipartHttpServletRequest, response, isHistoryBack, fileInputName, uploadFileMaxSize);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param HttpServletResponse
	 * @param isHistoryBack
	 * @param uploadFileMaxSize
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String fileInputName, long uploadFileMaxSize) {
		
		if(multipartHttpServletRequest != null) {
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile file = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
					
					boolean isPass = false;
					
					//파일명 가저오기
					String fileName = file.getOriginalFilename();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String fileMimeType = file.getContentType();
					long fileSize = file.getSize();
					
					LOGGER.info("####################### [isUploadFileValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");
					
					if(getAllowFileExtensions() != null) {
						
						//파일 확장자 검사
						for(String allowExtension : getAllowFileExtensions()) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileExtension.equalsIgnoreCase(allowExtension)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//확장자 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
						}
						return false;
					}
					
					if(getAllowFileMimeTypes() != null) {
						
						//파일 마인 타입 검사
						for(String allowMimeType : getAllowFileMimeTypes()) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//파일 마인 타입 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
					
					//용량 체크
					if(uploadFileMaxSize <= -1 || fileSize <= uploadFileMaxSize) {
						isPass = true;
					}
					
					//용량 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String allowFileExtensions, String allowFileMimeTypes) {
		return isUploadFileValid(multipartHttpServletRequest, response, true, allowFileExtensions, allowFileMimeTypes);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @param uploadFileMaxSize
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String allowFileExtensions, String allowFileMimeTypes, long uploadFileMaxSize) {
		return isUploadFileValid(multipartHttpServletRequest, response, true, allowFileExtensions, allowFileMimeTypes, uploadFileMaxSize);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @param fileInputName
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, String allowFileExtensions, String allowFileMimeTypes, String fileInputName) {
		
		return isUploadFileValid(multipartHttpServletRequest, response, true, allowFileExtensions, allowFileMimeTypes, fileInputName);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String allowFileExtensions, String allowFileMimeTypes) {
		return isUploadFileValid(multipartHttpServletRequest, response, true, allowFileExtensions, allowFileMimeTypes, null);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @param uploadFileMaxSize
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String allowFileExtensions, String allowFileMimeTypes, long uploadFileMaxSize) {
		return isUploadFileValid(multipartHttpServletRequest, response, true, allowFileExtensions, allowFileMimeTypes, null, uploadFileMaxSize);
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String allowFileExtensions, String allowFileMimeTypes, String fileInputName) {
		
		if(multipartHttpServletRequest != null) {
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile file = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
					
					boolean isPass = false;
					
					//파일명 가저오기
					String fileName = file.getOriginalFilename();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String fileMimeType = file.getContentType();
					long fileSize = file.getSize();
					
					LOGGER.info("####################### [isUploadFileValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");
					
					if(getAllowFileExtensions(allowFileExtensions) != null) {
						
						//파일 확장자 검사
						for(String allowExtension : getAllowFileExtensions(allowFileExtensions)) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileExtension.equalsIgnoreCase(allowExtension)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//확장자 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
						}
						return false;
					}
					
					if(getAllowFileMimeTypes(allowFileMimeTypes) != null) {
						
						//파일 마인 타입 검사
						for(String allowMimeType : getAllowFileMimeTypes(allowFileMimeTypes)) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//파일 마인 타입 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
					
					//용량 체크
					if(uploadFileMaxSize <= -1 || fileSize <= uploadFileMaxSize) {
						isPass = true;
					}
					
					//용량 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * <pre>
	 * 업로드 한 파일을 검증한다.
	 * 파일 확장자, 파일 MIME TYPE, 파일 사이즈
	 * 업로드 된 파일이 존재 하지 않는다면 true 를 리턴한다.
	 * 검증에 성공하면 true 를 리턴, 실패시 false 를 리턴한다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param response
	 * @param isHistoryBack
	 * @param allowFileExtensions
	 * @param allowFileMimeTypes
	 * @return 파일 검증 여부
	 */
	public static boolean isUploadFileValid(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String allowFileExtensions, String allowFileMimeTypes, String fileInputName, long uploadFileMaxSize) {
		
		if(multipartHttpServletRequest != null) {
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile file = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
					
					boolean isPass = false;
					
					//파일명 가저오기
					String fileName = file.getOriginalFilename();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String fileMimeType = file.getContentType();
					long fileSize = file.getSize();
					
					LOGGER.info("####################### [isUploadFileValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");
					
					if(getAllowFileExtensions(allowFileExtensions) != null) {
						
						//파일 확장자 검사
						for(String allowExtension : getAllowFileExtensions(allowFileExtensions)) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileExtension.equalsIgnoreCase(allowExtension)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//확장자 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
						}
						return false;
					}
					
					if(getAllowFileMimeTypes(allowFileMimeTypes) != null) {
						
						//파일 마인 타입 검사
						for(String allowMimeType : getAllowFileMimeTypes(allowFileMimeTypes)) {
							
							//허용할 확장자에 포함되어 있으면
							if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
								isPass = true;
								break;
							}
						}
					} else {
						isPass = true;
					}
					
					//파일 마인 타입 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
					
					//용량 체크
					if(uploadFileMaxSize <= -1 || fileSize <= uploadFileMaxSize) {
						isPass = true;
					}
					
					//용량 검증 실패
					if(!isPass) {
						if(isHistoryBack) {
							XmlMessageManager.historyBack(response, "comm.file.upload.denied");
						}
						return false;
					}
				}
				
			}
		}
		
		return true;
	}
	
	public static boolean isUploadFileValids(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response, boolean isHistoryBack, String fileInputName) {

		if(multipartHttpServletRequest != null) {

			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

			while(fileNames.hasNext()) {

				String _fileName = fileNames.next();

				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}

				List<MultipartFile> files = multipartHttpServletRequest.getFiles(_fileName);
				for(MultipartFile file : files) {

					//업로드 된 파일이 존재하면
					if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {

						boolean isPass = false;

						//파일명 가저오기
						String fileName = StringUtil.nvl(file.getOriginalFilename());
						String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
						String fileMimeType = file.getContentType();
						long fileSize = file.getSize();

						LOGGER.debug("####################### [isUploadImageValid] fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");

						
						if(getAllowFileExtensions() != null) {

							//파일 확장자 검사
							for(String allowExtension : getAllowFileExtensions()) {

								//허용할 확장자에 포함되어 있으면
								if(fileExtension.equalsIgnoreCase(allowExtension)) {
									isPass = true;
									break;
								}
							}
						} else {
							isPass = true;
						}

						//확장자 검증 실패
						if(!isPass) {

							LOGGER.debug("####################### [isUploadImageValid] allowExtension Fail :: fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");

							if(isHistoryBack) {
								XmlMessageManager.historyBack(response, "comm.file.upload.denied.extension");
							}
							return false;
						}

						if(getAllowFileMimeTypes() != null) {

							//파일 마인 타입 검사
							for(String allowMimeType : getAllowFileMimeTypes()) {

								//허용할 확장자에 포함되어 있으면
								if(fileMimeType.equalsIgnoreCase(allowMimeType)) {
									isPass = true;
									break;
								}
							}
						} else {
							isPass = true;
						}

						//파일 마인 타입 검증 실패
						if(!isPass) {

							LOGGER.debug("####################### [isUploadImageValid] allowMimeType Fail :: fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");

							if(isHistoryBack) {
								XmlMessageManager.historyBack(response, "comm.file.upload.denied");
							}
							return false;
						}

						//용량 체크
						if(uploadImageMaxSize <= -1 || fileSize <= uploadImageMaxSize) {
							isPass = true;
						}

						//용량 검증 실패
						if(!isPass) {

							LOGGER.debug("####################### [isUploadImageValid] allowMaxSize Fail :: fileName : " + fileName + " / contentType : " + fileMimeType + " ######################");

							if(isHistoryBack) {
								XmlMessageManager.historyBack(response, "comm.file.upload.denied");
							}
							return false;
						}
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadImageFile(MultipartHttpServletRequest multipartHttpServletRequest) {
		
		return uploadImageFile(multipartHttpServletRequest, uploadImagePath);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadImageFile(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath) {
		
		return uploadImageFile(multipartHttpServletRequest, uploadPath, null);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @param fileInputName
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadImageFile(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath, String fileInputName) {
		
		String _uploadPath = uploadPath;
		
		if(StringUtil.nvl(uploadPath).equals("") || uploadPath.equals(FileUploadManager.DEFAULT_PATH)) {
			_uploadPath = uploadImagePath;
		}
		
		if(multipartHttpServletRequest != null) {
			
			String createPath = _uploadPath;
			String datePath = DateUtil.getDateFormat("yyyyMMdd");
			
			try {
				createPath = makeDirectory(_uploadPath, datePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile multipartFile = multipartHttpServletRequest.getFile(_fileName);
				//업로드 된 파일이 존재하면
				if(multipartFile != null && !StringUtil.isEmpty(multipartFile.getOriginalFilename())) {
					
					String fileNm = multipartFile.getOriginalFilename();
					//String fileExt = fileNm.toUpperCase().substring(fileNm.lastIndexOf(".") + 1);
					//String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "_" + fileExt;
					
					String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
					String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;
					
					try {
						
						SafeFile file = new SafeFile(createPath, fileTmprNm);
						
						multipartFile.transferTo(file);
						
						String mimeType = multipartFile.getContentType();
						long fileSize = multipartFile.getSize();
						String filePath = file.getCanonicalPath();
						String inputName = multipartFile.getName();
						
						LOGGER.info("###### upload image Extension : " + fileExt);
						LOGGER.info("###### upload image FileName : " +  fileNm);
						LOGGER.info("###### upload image FilePath : " + filePath);
						LOGGER.info("###### upload image FileTempName : " + fileTmprNm);
						LOGGER.info("###### upload image FileName : " +  fileNm);
						LOGGER.info("###### upload image FileSize : " + fileSize);
						LOGGER.info("###### upload image MimeType : " + mimeType);
						LOGGER.info("###### upload image InputName : " + inputName);
						LOGGER.info("###### upload image DatePath : " + (todayMakePathYN.equalsIgnoreCase("Y") ? datePath : ""));
						
						FileUploadVO fileUploadVO = new FileUploadVO();
						fileUploadVO.setFileExtension(fileExt);
						fileUploadVO.setFileOriginName(fileNm);
						fileUploadVO.setFilePath(createPath);
						fileUploadVO.setCanonicalPath(filePath);
						fileUploadVO.setFileTempName(fileTmprNm);
						fileUploadVO.setFileSize(fileSize);
						fileUploadVO.setMimeType(mimeType);
						fileUploadVO.setInputName(inputName);
						fileUploadVO.setDatePath(todayMakePathYN.equalsIgnoreCase("Y") ? datePath : "");
						
						//setUserId(multipartHttpServletRequest, fileUploadVO);
						
						return fileUploadVO;
					
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ValidationException e) {
						e.printStackTrace();
					}
					
					//PMD 지적사항 : Avoid using a branching statement as the last in a loop.
					//단일의 파일을 처리함으로 1회 loop 후 break 함.
					break;
				}
				
			}
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadImageFiles(MultipartHttpServletRequest multipartHttpServletRequest) {
		
		return uploadImageFiles(multipartHttpServletRequest, uploadImagePath);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadImageFiles(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath) {
		
		return uploadImageFiles(multipartHttpServletRequest, uploadPath, null);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @param fileInputName
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadImageFiles(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath, String fileInputName) {
		
		String _uploadPath = uploadPath;
		
		if(StringUtil.nvl(uploadPath).equals("") || uploadPath.equals(FileUploadManager.DEFAULT_PATH)) {
			_uploadPath = uploadImagePath;
		}
		
		if(multipartHttpServletRequest != null) {
			
			String createPath = _uploadPath;
			String datePath = DateUtil.getDateFormat("yyyyMMdd");
			
			try {
				createPath = makeDirectory(_uploadPath, datePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			List<FileUploadVO> list = new ArrayList<FileUploadVO>();
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile multipartFile = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(multipartFile != null && !StringUtil.isEmpty(multipartFile.getOriginalFilename())) {
					
					String fileNm = multipartFile.getOriginalFilename();
					//String fileExt = fileNm.toUpperCase().substring(fileNm.lastIndexOf(".") + 1);
					//String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "_" + fileExt;
					
					String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
					String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;
					
					try {
						
						SafeFile file = new SafeFile(createPath, fileTmprNm);
						
						multipartFile.transferTo(file);
						
						String mimeType = multipartFile.getContentType();
						long fileSize = multipartFile.getSize();
						String filePath = file.getCanonicalPath();
						String inputName = multipartFile.getName();
						
						LOGGER.info("###### upload image Extension : " + fileExt);
						LOGGER.info("###### upload image FileName : " +  fileNm);
						LOGGER.info("###### upload image FilePath : " + filePath);
						LOGGER.info("###### upload image FileTempName : " + fileTmprNm);
						LOGGER.info("###### upload image FileName : " +  fileNm);
						LOGGER.info("###### upload image FileSize : " + fileSize);
						LOGGER.info("###### upload image MimeType : " + mimeType);
						LOGGER.info("###### upload image InputName : " + inputName);
						LOGGER.info("###### upload image DatePath : " + (todayMakePathYN.equalsIgnoreCase("Y") ? datePath : ""));
						
						FileUploadVO fileUploadVO = new FileUploadVO();
						fileUploadVO.setFileExtension(fileExt);
						fileUploadVO.setFileOriginName(fileNm);
						fileUploadVO.setFilePath(filePath);
						fileUploadVO.setFileTempName(fileTmprNm);
						fileUploadVO.setFileSize(fileSize);
						fileUploadVO.setMimeType(mimeType);
						fileUploadVO.setInputName(inputName);
						fileUploadVO.setDatePath(todayMakePathYN.equalsIgnoreCase("Y") ? datePath : "");
						
						//setUserId(multipartHttpServletRequest, fileUploadVO);
						
						list.add(fileUploadVO);
					
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ValidationException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			if(list.size() > 0) {
				
				return list;
			}
			
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadFile(MultipartHttpServletRequest multipartHttpServletRequest) {
		
		return uploadFile(multipartHttpServletRequest, uploadFilePath);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadFile(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath) {
		
		return uploadFile(multipartHttpServletRequest, uploadPath, null);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @param fileInputName
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadFile(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath, String fileInputName) {
		
		String _uploadPath = uploadPath;
		
		if(StringUtil.nvl(uploadPath).equals("") || uploadPath.equals(FileUploadManager.DEFAULT_PATH)) {
			_uploadPath = uploadFilePath;
		}
		
		if(multipartHttpServletRequest != null) {
			
			String createPath = _uploadPath;
			String datePath = DateUtil.getDateFormat("yyyyMMdd");
			
			try {
				createPath = makeDirectory(_uploadPath, datePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				MultipartFile multipartFile = multipartHttpServletRequest.getFile(_fileName);
				
				//업로드 된 파일이 존재하면
				if(multipartFile != null && !StringUtil.isEmpty(multipartFile.getOriginalFilename())) {
					
					String fileNm = multipartFile.getOriginalFilename();
					//String fileExt = fileNm.toUpperCase().substring(fileNm.lastIndexOf(".") + 1);
					//String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "_" + fileExt;
					
					String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
					String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;
					
					try {
						
						SafeFile file = new SafeFile(createPath, fileTmprNm);
						
						multipartFile.transferTo(file);
						
						String mimeType = multipartFile.getContentType();
						long fileSize = multipartFile.getSize();
						String filePath = file.getCanonicalPath();
						String inputName = multipartFile.getName();
						
						LOGGER.info("###### upload file Extension : " + fileExt);
						LOGGER.info("###### upload file FileName : " +  fileNm);
						LOGGER.info("###### upload file FilePath : " + filePath);
						LOGGER.info("###### upload file FileTempName : " + fileTmprNm);
						LOGGER.info("###### upload file FileName : " +  fileNm);
						LOGGER.info("###### upload file FileSize : " + fileSize);
						LOGGER.info("###### upload file MimeType : " + mimeType);
						LOGGER.info("###### upload image InputName : " + inputName);
						LOGGER.info("###### upload image DatePath : " + (todayMakePathYN.equalsIgnoreCase("Y") ? datePath : ""));
						
						FileUploadVO fileUploadVO = new FileUploadVO();
						fileUploadVO.setFileExtension(fileExt);
						fileUploadVO.setFileOriginName(fileNm);
						fileUploadVO.setFilePath(filePath);
						fileUploadVO.setFileTempName(fileTmprNm);
						fileUploadVO.setFileSize(fileSize);
						fileUploadVO.setMimeType(mimeType);
						fileUploadVO.setInputName(inputName);
						fileUploadVO.setDatePath(todayMakePathYN.equalsIgnoreCase("Y") ? datePath : "");
						
						//setUserId(multipartHttpServletRequest, fileUploadVO);
						
						return fileUploadVO;
					
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ValidationException e) {
						e.printStackTrace();
					}
					
					//PMD 지적사항 : Avoid using a branching statement as the last in a loop.
					//단일의 파일을 처리함으로 1회 loop 후 break 함.
					break;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadFiles(MultipartHttpServletRequest multipartHttpServletRequest) {
		
		return uploadFiles(multipartHttpServletRequest, uploadFilePath);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadFiles(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath) {
		
		return uploadFiles(multipartHttpServletRequest, uploadPath, null);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @param fileInputName
	 * @return 파일의 정보 List<FileUploadVO>
	 */
	public static List<FileUploadVO> uploadFiles(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath, String fileInputName) {
		
		return uploadFiles(multipartHttpServletRequest, uploadPath, fileInputName, false);
	}
	
	/**
	 * <pre>
	 * MultipartHttpServletRequest 의 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 List<FileUploadVO> 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param multipartHttpServletRequest
	 * @param uploadPath
	 * @param fileInputName
	 * @param isExtFlag
	 * @return
	 */
	public static List<FileUploadVO> uploadFiles(MultipartHttpServletRequest multipartHttpServletRequest, String uploadPath, String fileInputName, boolean isExtFlag) {
		
		String _uploadPath = uploadPath;
		
		if(StringUtil.nvl(uploadPath).equals("") || uploadPath.equals(FileUploadManager.DEFAULT_PATH)) {
			_uploadPath = uploadFilePath;
		}
		
		if(multipartHttpServletRequest != null) {
			
			String createPath = _uploadPath;
			String datePath = DateUtil.getDateFormat("yyyyMMdd");
			
			try {
				createPath = makeDirectory(_uploadPath, datePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			List<FileUploadVO> list = new ArrayList<FileUploadVO>();
			
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				
				String _fileName = fileNames.next();
				
				if(!StringUtil.nvl(fileInputName).equals("") && !_fileName.startsWith(fileInputName)) {
					continue;
				}
				
				List<MultipartFile> files = multipartHttpServletRequest.getFiles(_fileName);
				for(MultipartFile file : files) {
					//업로드 된 파일이 존재하면
					if(file != null && !StringUtil.isEmpty(file.getOriginalFilename())) {
						String fileNm = file.getOriginalFilename();
						//String fileExt = fileNm.toUpperCase().substring(fileNm.lastIndexOf(".") + 1);
						//String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + (isExtFlag ? "." + fileExt.toLowerCase() : "_" + fileExt);
						
						String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
						String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;
						
						try {
							SafeFile file_ = new SafeFile(createPath, fileTmprNm);
							
							file.transferTo(file_);
							
							String mimeType = file.getContentType();
							long fileSize = file.getSize();
							String filePath = file_.getCanonicalPath();
							String inputName = file.getName();
							
							LOGGER.info("###### upload file Extension : " + fileExt);
							LOGGER.info("###### upload file FileName : " +  fileNm);
							LOGGER.info("###### upload file FilePath : " + filePath);
							LOGGER.info("###### upload file FileTempName : " + fileTmprNm);
							LOGGER.info("###### upload file FileName : " +  fileNm);
							LOGGER.info("###### upload file FileSize : " + fileSize);
							LOGGER.info("###### upload file MimeType : " + mimeType);
							LOGGER.info("###### upload image InputName : " + inputName);
							LOGGER.info("###### upload image DatePath : " + (todayMakePathYN.equalsIgnoreCase("Y") ? datePath : ""));
							
							FileUploadVO fileUploadVO = new FileUploadVO();							
							fileUploadVO.setFileExtension(fileExt);
							fileUploadVO.setFileOriginName(fileNm);
							fileUploadVO.setFilePath(createPath);
							fileUploadVO.setCanonicalPath(filePath);
							fileUploadVO.setFileTempName(fileTmprNm);
							fileUploadVO.setFileSize(fileSize);
							fileUploadVO.setMimeType(mimeType);
							fileUploadVO.setInputName(inputName);
							fileUploadVO.setDatePath(todayMakePathYN.equalsIgnoreCase("Y") ? datePath : "");
							
							//setUserId(multipartHttpServletRequest, fileUploadVO);
							
							list.add(fileUploadVO);
						
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ValidationException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
			if(list.size() > 0) {
				
				return list;
			}
			
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 디렉토리를 만들고 만들어진 경로를 반환한다.
	 * </pre>
	 *
	 * @param uploadPath
	 * @return
	 * @throws IOException 
	 */
	private static String makeDirectory(String uploadPath, String datePath) throws IOException {
		try {
			SafeFile uploadPathFile = new SafeFile(uploadPath);
			
			if(!uploadPathFile.exists() || !uploadPathFile.isDirectory()) {
				uploadPathFile.mkdirs();
			}
			
			if(todayMakePathYN.equalsIgnoreCase("Y")) {
			
				String todayPath = datePath;
				
				SafeFile todayFile = new SafeFile(uploadPath, todayPath);
				
				if(!todayFile.exists() || !todayFile.isDirectory()) {
					todayFile.mkdir();
				}
				
				return todayFile.getCanonicalPath();
				
			} else {
				
				return uploadPathFile.getCanonicalPath();
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * <pre>
	 * 파일을 서버에 저장하고 파일의 정보를 반환한다.
	 * 업로드 된 파일이 존재하면 FileUploadVO 가 리턴되고, 존재하지 않으면 null 이 리턴된다.
	 * </pre>
	 *
	 * @param file
	 * @param uploadPath
	 * @param fileInputName
	 * @return 파일의 정보 FileUploadVO
	 */
	public static FileUploadVO uploadImageFile(File uploadFile, String uploadPath, String fileInputName) {
		
		String _uploadPath = uploadPath;
		
		if(StringUtil.nvl(uploadPath).equals("") || uploadPath.equals(FileUploadManager.DEFAULT_PATH)) {
			_uploadPath = uploadImagePath;
		}
			
		String createPath = _uploadPath;
		String datePath = DateUtil.getDateFormat("yyyyMMdd");
		
		try {
			createPath = makeDirectory(_uploadPath, datePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		String fileNm = uploadFile.getName();
		//String fileExt = fileNm.toUpperCase().substring(fileNm.lastIndexOf(".") + 1);
		//String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "_" + fileExt;
		
		String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
		String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;
		
		
		try {
			SafeFile createFile = new SafeFile(createPath, FilenameUtils.getName(fileTmprNm));
			uploadFile.renameTo(createFile);
			
			String mimeType = Files.probeContentType(Paths.get(createPath));
			long fileSize = createFile.length();
			String filePath = createFile.getCanonicalPath();
			String inputName = createFile.getName();
			
			LOGGER.info("###### upload image Extension : " + fileExt);
			LOGGER.info("###### upload image FileName : " +  fileNm);
			LOGGER.info("###### upload image FilePath : " + filePath);
			LOGGER.info("###### upload image FileTempName : " + fileTmprNm);
			LOGGER.info("###### upload image FileName : " +  fileNm);
			LOGGER.info("###### upload image FileSize : " + fileSize);
			LOGGER.info("###### upload image MimeType : " + mimeType);
			LOGGER.info("###### upload image InputName : " + inputName);
			LOGGER.info("###### upload image DatePath : " + (todayMakePathYN.equalsIgnoreCase("Y") ? datePath : ""));
			
			FileUploadVO fileUploadVO = new FileUploadVO();
			fileUploadVO.setFileExtension(fileExt);
			fileUploadVO.setFileOriginName(fileNm);
			fileUploadVO.setFilePath(filePath);
			fileUploadVO.setFileTempName(fileTmprNm);
			fileUploadVO.setFileSize(fileSize);
			fileUploadVO.setMimeType(mimeType);
			fileUploadVO.setInputName(inputName);
			fileUploadVO.setDatePath(todayMakePathYN.equalsIgnoreCase("Y") ? datePath : "");
			
			//setUserId(multipartHttpServletRequest, fileUploadVO);
			
			return fileUploadVO;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
