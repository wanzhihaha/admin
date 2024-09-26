package com.cellosquare.adminapp.admin.video.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;

public interface AdminVideoService {

	/**
	 * 비디오 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminVideoVO vo);

	/**
	 * 비디오 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminVideoVO> getList(AdminVideoVO vo);
	
	
	/**
	 * 비디오 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminVideoVO getDetail(AdminVideoVO vo);

	/**
	 * 비디오 등록
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 * @return
	 */
	public int regist(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminVideoVO vo);

	/**
	 * 비디오 수정
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 * @return
	 */
	public int update(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminVideoVO vo);

	
	/**
	 * 비디오 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminVideoVO vo);

	/**
	 * 정렬 순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminVideoVO vo);
	
	
}
