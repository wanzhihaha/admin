package com.cellosquare.adminapp.admin.event.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.event.mapper.AdminEventMapper;
import com.cellosquare.adminapp.admin.event.service.AdminEventService;
import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

@Service
public class AdminEventServiceImpl implements AdminEventService {
	@Autowired
	private AdminEventMapper adminEventMapper;

	/**
	 * 이벤트 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminEventVO vo) {
		return adminEventMapper.getTotalCount(vo);
	}

	/**
	 * 이벤트 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminEventVO> getList(AdminEventVO vo) {
		return adminEventMapper.getList(vo);
	}

	/**
	 * 이벤트 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminEventVO getImg(AdminEventVO vo) {
		return adminEventMapper.getImg(vo);
	}

	/**
	 * 이벤트 등록
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 */
	public int regist(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, AdminEventVO vo) {

		int result = 0;
		// 컨텐츠 저장
		if(!vo.getPcListOrginFile().isEmpty()) {	
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

				vo.setPcListImgPath(fileVO.getFilePath());
				vo.setPcListImgFileNm(fileVO.getFileTempName());
				vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
			}
		} 
		if(!vo.getPcDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

				vo.setPcDetlImgPath(fileVO.getFilePath());
				vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
			}
		} 
		if(!vo.getMobileListOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

				vo.setMobileListImgPath(fileVO.getFilePath());
				vo.setMobileListImgFileNm(fileVO.getFileTempName());
				vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
			}
		} 
		if(!vo.getMobileDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");

				vo.setMobileDetlImgPath(fileVO.getFilePath());
				vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
			}
		} 
		result = adminEventMapper.regist(vo);
		return result;
	}

	/**
	 * 이벤트 수정
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 */
	public int update(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, AdminEventVO vo) {

		int result = 0;

		String pcListFileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
		String pcDetailFileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
		String mobileListFileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
		String mobileDetailFileDel = StringUtil.nvl(vo.getMobileDetailFileDel(), "N");;
		AdminEventVO adminEventVO = getImg(vo);

		// pcList 첨부파일을 선택 했을시 
		if(!vo.getPcListOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

				vo.setPcListImgPath(fileVO.getFilePath());
				vo.setPcListImgFileNm(fileVO.getFileTempName());
				vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
				
			}
		} else {	// 파일을 선택안했을시
			if(pcListFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setPcListImgPath("");
				vo.setPcListImgFileNm("");
				vo.setPcListImgSize("0");
				vo.setPcListImgOrgFileNm("");
				vo.setPcListImgAlt("");
			} else {
				vo.setPcListImgPath(adminEventVO.getPcListImgPath());
				vo.setPcListImgFileNm(adminEventVO.getPcListImgFileNm());
				vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminEventVO.getPcListImgSize(), "0")));
				vo.setPcListImgOrgFileNm(adminEventVO.getPcListImgOrgFileNm());
			}
		}
		// pcDetail 첨부파일을 선택 했을시
		if(!vo.getPcDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

				vo.setPcDetlImgPath(fileVO.getFilePath());
				vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
			}
		} else {	// 파일을 선택안했을시
			if(pcDetailFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setPcDetlImgPath("");
				vo.setPcDetlImgFileNm("");
				vo.setPcDetlImgSize("0");
				vo.setPcDetlImgOrgFileNm("");
				vo.setPcDetlImgAlt("");
			} else {
				vo.setPcDetlImgPath(adminEventVO.getPcDetlImgPath());
				vo.setPcDetlImgFileNm(adminEventVO.getPcDetlImgFileNm());
				vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(adminEventVO.getPcDetlImgSize(), "0")));
				vo.setPcDetlImgOrgFileNm(adminEventVO.getPcDetlImgOrgFileNm());
			}
		}
		// mobileList 첨부파일을 선택 했을시
		if(!vo.getMobileListOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

				vo.setMobileListImgPath(fileVO.getFilePath());
				vo.setMobileListImgFileNm(fileVO.getFileTempName());
				vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
			}
		} else {	// 파일을 선택안했을시
			if(mobileListFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setMobileListImgPath("");
				vo.setMobileListImgFileNm("");
				vo.setMobileListImgSize("0");
				vo.setMobileListImgOrgFileNm("");
				vo.setMobileListImgAlt("");
			} else {
				vo.setMobileListImgPath(adminEventVO.getMobileListImgPath());
				vo.setMobileListImgFileNm(adminEventVO.getMobileListImgFileNm());
				vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminEventVO.getMobileListImgSize(), "0")));
				vo.setMobileListImgOrgFileNm(adminEventVO.getMobileListImgOrgFileNm());
			}
		}
		// mobileDetail 첨부파일을 선택 했을시
		if(!vo.getMobileDetailOrginFile().isEmpty()) {
			if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
				String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathEvent")));

				FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");

				vo.setMobileDetlImgPath(fileVO.getFilePath());
				vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
			}
		} else {	// 파일을 선택안했을시
			if(mobileDetailFileDel.equals("Y")) {// 삭제를 체크했을시
				vo.setMobileDetlImgPath("");
				vo.setMobileDetlImgFileNm("");
				vo.setMobileDetlImgSize("0");
				vo.setMobileDetlImgOrgFileNm("");
				vo.setMobileDetlImgAlt("");
			} else {
				vo.setMobileDetlImgPath(adminEventVO.getMobileDetlImgPath());
				vo.setMobileDetlImgFileNm(adminEventVO.getMobileDetlImgFileNm());
				vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(adminEventVO.getMobileDetlImgSize(), "0")));
				vo.setMobileDetlImgOrgFileNm(adminEventVO.getMobileDetlImgOrgFileNm());
			}
		}
		result = adminEventMapper.update(vo);
		return result;
	}

	/**
	 * 이벤트 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int delete(AdminEventVO vo) {
		return adminEventMapper.delete(vo);
	}

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	@Override
	public int doSortOrder(AdminEventVO vo) {
		return adminEventMapper.doSortOrder(vo);
	}




}
