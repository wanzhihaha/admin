package com.cellosquare.adminapp.admin.libsubmvevent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;
import com.cellosquare.adminapp.admin.libsubmvevent.mapper.AdminLibSubMvEventMapper;
import com.cellosquare.adminapp.admin.libsubmvevent.service.AdminLibSubMvEventService;
import com.cellosquare.adminapp.admin.libsubmvevent.vo.AdminLibSubMvEventVO;

@Service
public class AdminLibSubMvEventServiceImpl implements AdminLibSubMvEventService{

	@Autowired
	private AdminLibSubMvEventMapper adminLibSubMvEventMapper;

	@Override
	public int getTotalCount(AdminLibSubMvEventVO vo) {
		return adminLibSubMvEventMapper.getTotalCount(vo);
	}

	@Override
	public List<AdminLibSubMvEventVO> getList(AdminLibSubMvEventVO vo) {
		return adminLibSubMvEventMapper.getList(vo);
	}

	@Override
	public int doWrite(AdminLibSubMvEventVO vo) throws Exception {
		return adminLibSubMvEventMapper.doWrite(vo);
	}

	@Override
	public AdminLibSubMvEventVO getDetail(AdminLibSubMvEventVO vo) {
		return adminLibSubMvEventMapper.getDetail(vo);
	}

	@Override
	public int doUpdate(AdminLibSubMvEventVO vo) throws Exception {
		return adminLibSubMvEventMapper.doUpdate(vo);
	}

	@Override
	public int doDelete(AdminLibSubMvEventVO vo) throws Exception {
		return adminLibSubMvEventMapper.doDelete(vo);
	}

	@Override
	public int doSortOrder(AdminLibSubMvEventVO vo) throws Exception {
		return adminLibSubMvEventMapper.doSortOrder(vo);
	}

	@Override
	public List<AdminEventVO> getPopUpList(AdminLibSubMvEventVO vo) {
		return adminLibSubMvEventMapper.getPopUpList(vo);
	}

	@Override
	public int popupTotalCount(AdminLibSubMvEventVO vo) {
		return adminLibSubMvEventMapper.popupTotalCount(vo);
	}
	
	
	
}
