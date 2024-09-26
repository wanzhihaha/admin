package com.cellosquare.adminapp.admin.event.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;

/**
 * @author User
 *
 */
public interface AdminEventService {

	/**
	 * 이벤트 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminEventVO vo);

	/**
	 * 이벤트 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminEventVO> getList(AdminEventVO vo);

	/**
	 * 이벤트 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminEventVO getImg(AdminEventVO vo);

	/**
	 * 이벤트 등록
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 */
	public int regist(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminEventVO vo);

	/**
	 * 이벤트 수정
	 * @param muServletRequest 
	 * @param response 
	 * @param request 
	 * @param vo
	 */
	
	public int update(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminEventVO vo);

	
	/**
	 * 이벤트 삭제
	 * @param vo
	 * @return
	 */
	
	public int delete(AdminEventVO vo);

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminEventVO vo);
	
	
}
