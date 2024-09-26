package com.cellosquare.adminapp.admin.cellonews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.cellonews.mapper.AdminCelloNewsMapper;
import com.cellosquare.adminapp.admin.cellonews.service.AdminCelloNewsService;
import com.cellosquare.adminapp.admin.cellonews.vo.AdminCelloNewsVO;

@Service
public class AdminCelloNewsServiceImpl implements AdminCelloNewsService {

	@Autowired
	private AdminCelloNewsMapper adminCelloNewsMapper;

	@Override
	public int getTotalCount(AdminCelloNewsVO vo) {
		return adminCelloNewsMapper.getTotalCount(vo);
	}

	@Override
	public List<AdminCelloNewsVO> getList(AdminCelloNewsVO vo) {
		return adminCelloNewsMapper.getList(vo);
	}

	@Override
	public AdminCelloNewsVO getDetail(AdminCelloNewsVO vo) {
		return adminCelloNewsMapper.getDetail(vo);
	}
	
	@Override
	public int doWrite(AdminCelloNewsVO vo) throws Exception {
		int result = adminCelloNewsMapper.doWrite(vo);
		return result;
	}
		

	@Override
	public int doUpdate(AdminCelloNewsVO vo) throws Exception {
			return adminCelloNewsMapper.doUpdate(vo);
		}

	
	@Override
	public int doDelete(AdminCelloNewsVO vo) throws Exception {
		return adminCelloNewsMapper.doDelete(vo);
	}

	@Override
	public int doSortOrder(AdminCelloNewsVO vo) throws Exception {
		return adminCelloNewsMapper.doSortOrder(vo);
	}
	
	


}
