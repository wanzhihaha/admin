package com.cellosquare.adminapp.admin.libsubmv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.libsubmv.mapper.AdminLibSubMvMapper;
import com.cellosquare.adminapp.admin.libsubmv.service.AdminLibSubMvService;
import com.cellosquare.adminapp.admin.libsubmv.vo.AdminLibSubMvVO;

@Service
public class AdminLibSubMvServiceImpl implements AdminLibSubMvService {

	@Autowired
	private AdminLibSubMvMapper adminLibSubMvMapper;
	@Override
	public int getTotalCount(AdminLibSubMvVO vo) {
		return adminLibSubMvMapper.getTotalCount(vo);
	}

	@Override
	public List<AdminLibSubMvVO> getList(AdminLibSubMvVO vo) {
		return adminLibSubMvMapper.getList(vo);
	}

	@Override
	public int regist(AdminLibSubMvVO vo) throws Exception {
		return adminLibSubMvMapper.regist(vo);
	}

	@Override
	public AdminLibSubMvVO getDetail(AdminLibSubMvVO vo) {
		return adminLibSubMvMapper.getDetail(vo);
	}

	@Override
	public int doUpdate(AdminLibSubMvVO vo) throws Exception {
		return adminLibSubMvMapper.doUpdate(vo);
	}

	@Override
	public int doDelete(AdminLibSubMvVO vo) throws Exception {
		return adminLibSubMvMapper.doDelete(vo);
	}

	@Override
	public int doSortOrder(AdminLibSubMvVO vo) throws Exception {
		return adminLibSubMvMapper.doSortOrder(vo);
	}

	@Override
	public List<AdminLibSubMvVO> getPopUpList(AdminLibSubMvVO vo) {
		return adminLibSubMvMapper.getPopUpList(vo);
	}

	@Override
	public int popupTotalCount(AdminLibSubMvVO vo) {
		return adminLibSubMvMapper.popupTotalCount(vo);
	}

}
