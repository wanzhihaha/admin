package com.cellosquare.adminapp.admin.video.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cellosquare.adminapp.common.util.S3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.video.mapper.AdminVideoMapper;
import com.cellosquare.adminapp.admin.video.service.AdminVideoService;
import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

@Service
public class AdminVideoServiceImpl implements AdminVideoService {
	@Autowired
	private AdminVideoMapper adminVideoMapper;

	/**
	 * 비디오 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminVideoVO vo) {
		return adminVideoMapper.getTotalCount(vo);
	}

	/**
	 * 비디오 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminVideoVO> getList(AdminVideoVO vo) {
		return adminVideoMapper.getList(vo);
	}

	/**
	 * 비디오 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminVideoVO getDetail(AdminVideoVO vo) {
		return adminVideoMapper.getDetail(vo);
	}

	/**
	 * 비디오 등록
	 * @param vo
	 * @return
	 */
	public int regist(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest muServletRequest, AdminVideoVO vo) {

		int result = 0;
		// 컨텐츠 저장
		if(!vo.getPcListOrginFile().isEmpty()) {	
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

				vo.setPcListImgPath(fileVO.getFilePath());
				vo.setPcListImgFileNm(fileVO.getFileTempName());
				vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} 
		if(!vo.getPcDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

				vo.setPcDetlImgPath(fileVO.getFilePath());
				vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} 
		if(!vo.getMobileListOrginFile().isEmpty()) {

			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

				vo.setMobileListImgPath(fileVO.getFilePath());
				vo.setMobileListImgFileNm(fileVO.getFileTempName());
				vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} 
		if(!vo.getMobileDetailOrginFile().isEmpty()) {

			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");

				vo.setMobileDetlImgPath(fileVO.getFilePath());
				vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} 
		result = adminVideoMapper.regist(vo);
		return result;
	}

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminVideoVO vo) {
		return adminVideoMapper.doSortOrder(vo);
	}

	/**
	 * 비디오 수정
	 * @param vo
	 * @return
	 */
	public int update(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest muServletRequest, AdminVideoVO vo) {

		int result = 0;

		AdminVideoVO adminVideoVO = getDetail(vo);
		String pcListFileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
		String pcDetailFileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
		String mobileListFileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
		String mobileDetailFileDel = StringUtil.nvl(vo.getMobileDetailFileDel(), "N");;

		// pcList 첨부파일을 선택 했을시 
		
		if(!vo.getPcListOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

				vo.setPcListImgPath(fileVO.getFilePath());
				vo.setPcListImgFileNm(fileVO.getFileTempName());
				vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} else {	// 파일을 선택안했을시
			if(pcListFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setPcListImgPath("");
				vo.setPcListImgFileNm("");
				vo.setPcListImgSize("0");
				vo.setPcListImgOrgFileNm("");
				vo.setPcListImgAlt("");
			} else {
				vo.setPcListImgPath(adminVideoVO.getPcListImgPath());
				vo.setPcListImgFileNm(adminVideoVO.getPcListImgFileNm());
				vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminVideoVO.getPcListImgSize(), "0")));
				vo.setPcListImgOrgFileNm(adminVideoVO.getPcListImgOrgFileNm());
			}
		}
		// pcDetail 첨부파일을 선택 했을시
		if(!vo.getPcDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

				vo.setPcDetlImgPath(fileVO.getFilePath());
				vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} else {	// 파일을 선택안했을시
			if(pcDetailFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setPcDetlImgPath("");
				vo.setPcDetlImgFileNm("");
				vo.setPcDetlImgSize("0");
				vo.setPcDetlImgOrgFileNm("");
				vo.setPcDetlImgAlt("");
			} else {
				vo.setPcDetlImgPath(adminVideoVO.getPcDetlImgPath());
				vo.setPcDetlImgFileNm(adminVideoVO.getPcDetlImgFileNm());
				vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(adminVideoVO.getPcDetlImgSize(), "0")));
				vo.setPcDetlImgOrgFileNm(adminVideoVO.getPcDetlImgOrgFileNm());
			}
		}
		// mobileList 첨부파일을 선택 했을시
		if(!vo.getMobileListOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

				vo.setMobileListImgPath(fileVO.getFilePath());
				vo.setMobileListImgFileNm(fileVO.getFileTempName());
				vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} else {	// 파일을 선택안했을시
			if(mobileListFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setMobileListImgPath("");
				vo.setMobileListImgFileNm("");
				vo.setMobileListImgSize("0");
				vo.setMobileListImgOrgFileNm("");
				vo.setMobileListImgAlt("");
			} else {
				vo.setMobileListImgPath(adminVideoVO.getMobileListImgPath());
				vo.setMobileListImgFileNm(adminVideoVO.getMobileListImgFileNm());
				vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminVideoVO.getMobileListImgSize(), "0")));
				vo.setMobileListImgOrgFileNm(adminVideoVO.getMobileListImgOrgFileNm());
			}
		}
		// mobileDetail 첨부파일을 선택 했을시
		if(!vo.getMobileDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMovie")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");

				vo.setMobileDetlImgPath(fileVO.getFilePath());
				vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
			} else {
				return 0;
			}
		} else {	// 파일을 선택안했을시
			if(mobileDetailFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setMobileDetlImgPath("");
				vo.setMobileDetlImgFileNm("");
				vo.setMobileDetlImgSize("0");
				vo.setMobileDetlImgOrgFileNm("");
				vo.setMobileDetlImgAlt("");
			} else {
				vo.setMobileDetlImgPath(adminVideoVO.getMobileDetlImgPath());
				vo.setMobileDetlImgFileNm(adminVideoVO.getMobileDetlImgFileNm());
				vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(adminVideoVO.getMobileDetlImgSize(), "0")));
				vo.setMobileDetlImgOrgFileNm(adminVideoVO.getMobileDetlImgOrgFileNm());
			}
		}

		result = adminVideoMapper.update(vo);
		return result;
	}

	/**
	 * 비디오 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int delete(AdminVideoVO vo) {
		return adminVideoMapper.delete(vo);
	}




}
