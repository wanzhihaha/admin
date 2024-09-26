package com.cellosquare.adminapp.admin.faq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.faq.mapper.AdminFaqMapper;
import com.cellosquare.adminapp.admin.faq.service.AdminFaqService;
import com.cellosquare.adminapp.admin.faq.vo.AdminFaqVO;

@Service
public class AdminFaqServiceImpl implements AdminFaqService {
	@Autowired
	private AdminFaqMapper adminFaqMapper;

	@Override
	public int getTotalCount(AdminFaqVO vo) {
		return adminFaqMapper.getTotalCount(vo);
	}

	@Override
	public List<AdminFaqVO> getList(AdminFaqVO vo) {
		return adminFaqMapper.getList(vo);
	}

	@Override
	public AdminFaqVO getDetail(AdminFaqVO vo) {
		return adminFaqMapper.getDetail(vo);
	}
	
	@Override
	public int doWrite(AdminFaqVO vo) throws Exception {
		return adminFaqMapper.doWrite(vo);
	}

	@Override
	public int doUpdate(AdminFaqVO vo) throws Exception {
		return adminFaqMapper.doUpdate(vo);
	}

	@Override
	public int doDelete(AdminFaqVO vo) throws Exception {
		return adminFaqMapper.doDelete(vo);
	}

	@Override
	public int doSortOrder(AdminFaqVO vo) throws Exception {
		return adminFaqMapper.doSortOrder(vo);
	}

}
