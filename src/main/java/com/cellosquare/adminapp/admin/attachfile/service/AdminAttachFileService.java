package com.cellosquare.adminapp.admin.attachfile.service;

import java.util.List;

import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;

public interface AdminAttachFileService {
	// 파일목록 구하기
	public List<AdminAttachFileVO> getAttachFileList(AdminAttachFileVO vo);
	
	public AdminAttachFileVO getDetail(AdminAttachFileVO vo);
}
