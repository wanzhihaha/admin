package com.cellosquare.adminapp.admin.mvsqprd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.mvsqprd.mapper.AdminMvSqprdMapper;
import com.cellosquare.adminapp.admin.mvsqprd.service.AdminMvSqprdService;
import com.cellosquare.adminapp.admin.mvsqprd.vo.AdminMvSqprdVO;

@Service
public class AdminMvSqprdServiceImpl implements AdminMvSqprdService {

	@Autowired
	private AdminMvSqprdMapper adminMvSqprdMapper;
	
	@Override
	public int getTotalCount(AdminMvSqprdVO vo) {
		return adminMvSqprdMapper.getTotalCount(vo);
	}

	@Override
	public List<AdminMvSqprdVO> getList(AdminMvSqprdVO vo) {
		return adminMvSqprdMapper.getList(vo);
	}

	@Override
	public int doWrite(AdminMvSqprdVO vo) throws Exception {
		return adminMvSqprdMapper.doWrite(vo);
	}

	@Override
	public AdminMvSqprdVO getDetail(AdminMvSqprdVO vo) {
		return adminMvSqprdMapper.getDetail(vo);
	}

	@Override
	public int doUpdate(AdminMvSqprdVO vo) throws Exception {
		return adminMvSqprdMapper.doUpdate(vo);
	}

	@Override
	public int doDelete(AdminMvSqprdVO vo) throws Exception {
		return adminMvSqprdMapper.doDelete(vo);
	}

	@Override
	public int doSortOrder(AdminMvSqprdVO vo) throws Exception {
		return adminMvSqprdMapper.doSortOrder(vo);
	}

	@Override
	public List<AdminGoodsVO> getPopUpList(AdminMvSqprdVO vo) {
		return adminMvSqprdMapper.getPopUpList(vo);
	}

	@Override
	public int popupTotalCount(AdminMvSqprdVO vo) {
		return adminMvSqprdMapper.popupTotalCount(vo);
	}

}
