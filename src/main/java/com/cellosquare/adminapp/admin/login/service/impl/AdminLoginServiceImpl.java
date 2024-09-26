package com.cellosquare.adminapp.admin.login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.login.mapper.AdminLoginMapper;
import com.cellosquare.adminapp.admin.login.service.AdminLoginService;
import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	@Override
	public AdminManagerVO getLogin(AdminLoginVO vo) {
		AdminManagerVO adminManagerVO = adminLoginMapper.getLogin(vo);
		
		return adminManagerVO;
	}

	@Override
	public int updateLastLoginDt(AdminManagerVO vo) {
		int res = 0;
		try {
			adminLoginMapper.updateLastLoginDt(vo);
			
			res = 1;
		} catch (Exception e) {
		}
		
		return res;
	}

	@Override
	public int updatePassword(AdminManagerVO vo) {
		int res = 0;
		try {
			adminLoginMapper.updatePassword(vo);
			
			res = 1;
		} catch (Exception e) {
		}
		
		return res;
	}
	
	@Override
	public int updateLoginFailCnt(AdminLoginVO vo) {
		int res = 0;
		try {
			adminLoginMapper.updateLoginFailCnt(vo);
			
			res = 1;
		} catch (Exception e) {
		}
		
		return res;
	}
}
