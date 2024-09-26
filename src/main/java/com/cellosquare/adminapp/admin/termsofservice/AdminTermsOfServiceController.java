package com.cellosquare.adminapp.admin.termsofservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.common.util.XssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.termsofservice.service.AdminTermsOfServiceService;
import com.cellosquare.adminapp.admin.termsofservice.vo.AdminTermsOfServiceVO;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.nhncorp.lucy.security.xss.XssPreventer;

@Controller
public class AdminTermsOfServiceController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminTermsOfServiceService adminTermsOfServiceServiceImpl;
	
	/**
	 * TOS Management 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/terms/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {

		logger.debug("AdminTermsOfServiceController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		int totalCount = adminTermsOfServiceServiceImpl.getTotalCount(vo);
		List<AdminTermsOfServiceVO> list = new ArrayList<AdminTermsOfServiceVO>();
		
		if (totalCount > 0) {
			
			list = adminTermsOfServiceServiceImpl.getList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/basic/terms/list";
	}
	/**
	 * TOS Management 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		logger.debug("AdminTermsOfServiceController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/terms/registerForm";
	}
	
	/**
	 * TOS Management 등록하기
	 * @param request
	 * @param resonse
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/register.do")
	public String doWrite(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		logger.debug("AdminTermsOfServiceController :: doWrite");

		try {
			
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());

			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));

			adminTermsOfServiceServiceImpl.doWrite(vo);
				
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/terms/list.do";
		}catch(Exception e) {
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
				null, null, null, true);
		return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/terms/registerForm.do";
		}
	}
	
	/**
	 * TOS Management 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		logger.debug("AdminTermsOfServiceController :: detail");

		AdminTermsOfServiceVO detailVO = adminTermsOfServiceServiceImpl.getDetail(vo);
		
		//xss 원복
		detailVO.setDetlInfo(XssPreventer.unescape(detailVO.getDetlInfo()));

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/terms/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/terms/detail";
	}
	
	/**
	 * TOS Management 수정하기 폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		logger.debug("AdminTermsOfServiceController :: updateForm");

		AdminTermsOfServiceVO detailVO = adminTermsOfServiceServiceImpl.getDetail(vo);

		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/terms/registerForm";
	}
	
	/**
	 * TOS Management 수정하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, 
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		
		logger.debug("AdminTermsOfServiceController :: doUpdate");
		
		try {
			
			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());

			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));
					
			adminTermsOfServiceServiceImpl.doUpdate(vo);
				

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("tosSeqNo", vo.getTosSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),
					null, null, null, true);
			return "admin/basic/terms/updateForm";
		}
	}
		
	/** 
	 * TOS Management 삭제하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/terms/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminTermsOfServiceVO vo) {
		logger.debug("AdminTermsOfServiceController :: delete");
		try {
			adminTermsOfServiceServiceImpl.doDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("tosSeqNo", vo.getTosSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/terms/detail";
		}
	}
}
