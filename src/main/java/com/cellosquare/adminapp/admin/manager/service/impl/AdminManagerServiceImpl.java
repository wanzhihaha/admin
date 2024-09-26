package com.cellosquare.adminapp.admin.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.manager.service.AdminManagerService;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;

@Service
public class AdminManagerServiceImpl implements AdminManagerService {
	@Autowired
	private AdminManagerMapper adminManagerMapper;

	@Override
	public int registAdmin(AdminManagerVO vo) {
		return adminManagerMapper.registAdmin(vo);
	}

	@Override
	public int getChkId(AdminLoginVO vo) {
		return adminManagerMapper.getChkId(vo);
	}

	@Override
	public List<AdminManagerVO> getAdminList(AdminManagerVO vo) {
		List<AdminManagerVO> list = adminManagerMapper.getAdminList(vo);
		return list;
	}

	@Override
	public int getTotalCount(AdminManagerVO vo) {
		
		return adminManagerMapper.getTotalCount(vo);
	}

	@Override
	public AdminManagerVO getDetail(AdminManagerVO vo) {
		AdminManagerVO detailVO = adminManagerMapper.getDetail(vo);
		return detailVO;
	}

	@Override
	public int updateAdmin(AdminManagerVO vo) {
		return adminManagerMapper.updateAdmin(vo);
	}

	@Override
	public int doDelete(AdminManagerVO vo) {
		return adminManagerMapper.doDelete(vo);
	}

	@Override
	public int sendEmail(AdminManagerVO vo) {
		return adminManagerMapper.sendEmail(vo);
	}

	@Override
	public int updateLoginFailCntReset(AdminManagerVO vo) {
		return adminManagerMapper.updateLoginFailCntReset(vo);
	}
	
	

//	@Override
//	public void useUpdate(AdminManagerVO vo) {
//		adminManagerMapper.useUpdate(vo);
//	}
}
