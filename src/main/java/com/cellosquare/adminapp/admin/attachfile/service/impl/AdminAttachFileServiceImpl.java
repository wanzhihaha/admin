package com.cellosquare.adminapp.admin.attachfile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.attachfile.mapper.AdminAttachFileMapper;
import com.cellosquare.adminapp.admin.attachfile.service.AdminAttachFileService;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;

@Service
public class AdminAttachFileServiceImpl implements AdminAttachFileService {
	@Autowired
	private AdminAttachFileMapper adminAttachFileMapper;
	
	@Override
	public List<AdminAttachFileVO> getAttachFileList(AdminAttachFileVO vo) {
		return adminAttachFileMapper.getAttachFileList(vo);
	}

	@Override
	public AdminAttachFileVO getDetail(AdminAttachFileVO vo) {
		return adminAttachFileMapper.getDetail(vo);
	}

}
