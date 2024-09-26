package com.cellosquare.adminapp.admin.attachfile.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;

public interface AdminAttachFileMapper {
	// 파일 목록 구하기
	public List<AdminAttachFileVO> getAttachFileList(AdminAttachFileVO vo);
	
	// 첨부파일 저장
	public int registAttachFile(AdminAttachFileVO vo) throws Exception;
	
	// 삭제
	public int doAttachFileDel(AdminAttachFileVO vo) throws Exception;
	
	// 상세정보 구하기
	public AdminAttachFileVO getDetail(AdminAttachFileVO vo);
}
