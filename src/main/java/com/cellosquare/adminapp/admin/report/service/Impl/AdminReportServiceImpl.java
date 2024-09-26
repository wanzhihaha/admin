package com.cellosquare.adminapp.admin.report.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.attachfile.mapper.AdminAttachFileMapper;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.admin.report.mapper.AdminReportMapper;
import com.cellosquare.adminapp.admin.report.service.AdminReportService;
import com.cellosquare.adminapp.admin.report.vo.AdminReportVO;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

@Service
public class AdminReportServiceImpl implements AdminReportService{

	@Autowired
	private AdminReportMapper adminReportMapper;
	
	@Autowired
	private AdminAttachFileMapper adminAttachFileMapper;

	/**
	 * 리포트 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	@Override
	public int getTotalCount(AdminReportVO vo) {
		return adminReportMapper.getTotalCount(vo);
	}

	/**
	 * 리포트 리스트 조회
	 * @param vo
	 * @return
	 */
	@Override
	public List<AdminReportVO> getList(AdminReportVO vo) {
		return adminReportMapper.getList(vo);
	}

	/**
	 * 리포트 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public AdminReportVO getDetail(AdminReportVO vo) {
		return adminReportMapper.getDetail(vo);
	}

	/**
	 * 리포트 등록
	 * @param vo
	 * @return
	 */
	@Override
	public int doWrite(AdminReportVO vo)  {
		int result = 0;
		try {
			adminReportMapper.doWrite(vo);
			
			// 첨부파일 저장
			List<FileUploadVO> fileList = vo.getFileList();
			
			if(fileList != null && !fileList.isEmpty()) {
				AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
				adminAttachFileVO.setContentsSeqNo(vo.getRptSeqNo());
				adminAttachFileVO.setContentsCcd("RPT");   	// 리포트
				adminAttachFileVO.setInsPersonId(vo.getInsPersonId());
				
				for(int i = 0; i < fileList.size(); i++) {
					adminAttachFileVO.setOrdb(String.valueOf(i));
					adminAttachFileVO.setAtchFilePath(fileList.get(i).getFilePath());
					adminAttachFileVO.setAtchFileNm(fileList.get(i).getFileTempName());
					adminAttachFileVO.setAtchFileSize(String.valueOf(fileList.get(i).getFileSize()));					
					adminAttachFileVO.setAtchOrgFileNm(fileList.get(i).getFileOriginName());								

					adminAttachFileMapper.registAttachFile(adminAttachFileVO);
				}
			}
			result = 1;
		} catch(Exception e) {
			result = 0;
		}
		
		return result;
	}

	/**
	 * 리포트 수정
	 * @param vo
	 * @return
	 */
	@Override
	public int doUpdate(AdminReportVO vo) throws Exception {
		//
		int result = 0;
		try {
			adminReportMapper.doUpdate(vo);
			
			// 첨부파일 저장
			
			List<FileUploadVO> fileList = vo.getFileList();
			
			AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
			adminAttachFileVO.setContentsSeqNo(vo.getRptSeqNo());
			adminAttachFileVO.setContentsCcd("RPT");
			List<AdminAttachFileVO> attchFileList = adminAttachFileMapper.getAttachFileList(adminAttachFileVO);
						
			// 전체 삭제
			adminAttachFileMapper.doAttachFileDel(adminAttachFileVO);
			int selSeq = 0;	//새로운 파일 선택 시퀀스
			int sortOrder = 0;	// 정렬순서
			
			for(int j = 0; j < vo.getFileUploadSelect().length; j++) {
				if(StringUtil.nvl(vo.getFileUploadDel()[j],"N").equals("N") || vo.getFileUploadSelect()[j].equals("Y")) {	// 삭제 선택이 안되었 거나 신규파일을 선택 했을 경우
				
					adminAttachFileVO.setInsPersonId(vo.getInsPersonId());
					
					if(vo.getFileUploadSelect()[j].equals("Y")) {	// 새로운 파일을 선택했을시
						if(fileList != null && !fileList.isEmpty()) {	// 선택된 파일이 있을경우
							adminAttachFileVO.setOrdb(String.valueOf(sortOrder));
							adminAttachFileVO.setAtchFilePath(fileList.get(selSeq).getFilePath());
							adminAttachFileVO.setAtchFileNm(fileList.get(selSeq).getFileTempName());
							adminAttachFileVO.setAtchFileSize(String.valueOf(fileList.get(selSeq).getFileSize()));					
							adminAttachFileVO.setAtchOrgFileNm(fileList.get(selSeq).getFileOriginName());
							adminAttachFileMapper.registAttachFile(adminAttachFileVO);
							
							sortOrder++;
							selSeq++;
						}
					} else {
						if(attchFileList.size() > j) {	// 디비에 입력된 값이 있을경우
							
							adminAttachFileVO.setOrdb(String.valueOf(sortOrder));
							adminAttachFileVO.setAtchFilePath(attchFileList.get(j).getAtchFilePath());
							adminAttachFileVO.setAtchFileNm(attchFileList.get(j).getAtchFileNm());
							adminAttachFileVO.setAtchFileSize(String.valueOf(attchFileList.get(j).getAtchFileSize()));					
							adminAttachFileVO.setAtchOrgFileNm(attchFileList.get(j).getAtchOrgFileNm());
							adminAttachFileMapper.registAttachFile(adminAttachFileVO);
							
							sortOrder++;
						}
					}
				}						
			}
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/**
	 * 리포트 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int doDelete(AdminReportVO vo) throws Exception {
		return adminReportMapper.doDelete(vo);
	}

	/**
	 * ordb 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	@Override
	public int doSortOrder(AdminReportVO vo) throws Exception {
		return adminReportMapper.doSortOrder(vo);
	}
	
}
