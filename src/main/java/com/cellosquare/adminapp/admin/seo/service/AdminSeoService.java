package com.cellosquare.adminapp.admin.seo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;

public interface AdminSeoService {
	// SEO 저장
	public int doSeoWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo);

	public int doSeoWriteV2(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo);

	// seo 정보 가져오기
	public AdminSeoVO getSeoSelect(AdminSeoVO vo);
	
	// seo 정보 삭제
	public int doSeoDelete(AdminSeoVO vo);
	public int updateAllAddMesg();

	// seo 수정
	public int doSeoUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo);
	public int doSeoUpdatev2(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo);
}
