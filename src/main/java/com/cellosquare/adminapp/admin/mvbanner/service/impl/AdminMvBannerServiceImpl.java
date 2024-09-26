package com.cellosquare.adminapp.admin.mvbanner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.mvbanner.mapper.AdminMvBannerMapper;
import com.cellosquare.adminapp.admin.mvbanner.service.AdminMvBannerService;
import com.cellosquare.adminapp.admin.mvbanner.vo.AdminMvBannerVO;

@Service
public class AdminMvBannerServiceImpl implements AdminMvBannerService {
	@Autowired
	private AdminMvBannerMapper adminMvBannerMapper;

	/**
	 * 배너 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminMvBannerVO vo) {
		return adminMvBannerMapper.getTotalCount(vo);
	}

	/**
	 * 배너 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminMvBannerVO> getList(AdminMvBannerVO vo) {
		return adminMvBannerMapper.getList(vo);
	}

	/**
	 * 배너 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminMvBannerVO getDetail(AdminMvBannerVO vo) {
		return adminMvBannerMapper.getDetail(vo);
	}

	/**
	 * 배너 등록
	 * @param vo
	 * @return
	 */
	public int regist(AdminMvBannerVO vo) {
		return adminMvBannerMapper.regist(vo);
	}

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminMvBannerVO vo) {
		return adminMvBannerMapper.doSortOrder(vo);
	}

	/**
	 * 배너 수정
	 * @param vo
	 * @return
	 */
	public int update(AdminMvBannerVO vo) {
		return adminMvBannerMapper.update(vo);
	}

	/**
	 * 배너 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminMvBannerVO vo) {
		return adminMvBannerMapper.delete(vo);
	}





}
