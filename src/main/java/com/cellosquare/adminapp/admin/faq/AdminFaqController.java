package com.cellosquare.adminapp.admin.faq;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.faq.service.AdminFaqService;
import com.cellosquare.adminapp.admin.faq.vo.AdminFaqVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.nhncorp.lucy.security.xss.XssPreventer;

@Controller
public class AdminFaqController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminFaqService adminFaqServiceImpl;
	
	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	
	
	
	@GetMapping("/celloSquareAdmin/faq/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminFaqVO vo) {
		
		logger.debug("AdminFaqController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminFaqServiceImpl.getTotalCount(vo);
		List<AdminFaqVO> list = new ArrayList<AdminFaqVO>();
		
		if(totalCount > 0) {
			list = adminFaqServiceImpl.getList(vo);
		}
		
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/basic/faq/list";
	}
	
	/**
	 * 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		logger.debug("AdminFaqController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/faq/registerForm";
	}
	
	/**
	 * 등록하기
	 * @param request
	 * @param resonse
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/register.do")
	public String doWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
			, Model model, @ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: register");
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());

			vo.setFaqDetlContent(XssUtils.cleanXssNamoContent(vo.getFaqDetlContent()));
			
			// 컨텐츠 저장
			adminFaqServiceImpl.doWrite(vo);
			
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/faq/list.do";
		} catch (Exception e) {
			//e.printStackTrace();
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/faq/registerForm";
		}
		
	}
	
	/**
	 * 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: detail");
		
		
		AdminFaqVO detailVO = adminFaqServiceImpl.getDetail(vo);
		
		// xss 원복
		detailVO.setFaqDetlContent(XssPreventer.unescape(detailVO.getFaqDetlContent()));

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/faq/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/faq/detail";
	}
	
	/**
	 * 수정하기 폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: updateForm");
		
		AdminFaqVO detailVO = adminFaqServiceImpl.getDetail(vo);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/faq/registerForm";
	}
	
	/**
	 * 수정하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
			, Model model, @ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: update");
		
		try {
			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());

			vo.setFaqDetlContent(XssUtils.cleanXssNamoContent(vo.getFaqDetlContent()));
			
			adminFaqServiceImpl.doUpdate(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("faqSeq", vo.getFaqSeq());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
		
			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/faq/registerForm";
		}
	}
	
	/** 
	 * 삭제하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: delete");
		try {
			adminFaqServiceImpl.doDelete(vo);
		
			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("faqSeq", vo.getFaqSeq());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/faq/detail";
		}
	}
	
	/**
	 * 정렬순서 저장
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/faq/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminFaqVO vo) {
		logger.debug("AdminFaqController :: doSortOrder");
		try {
			AdminFaqVO adminFaqVO = null;
			for(int i = 0; i < vo.getListfaqSeq().length; i++) {
				adminFaqVO = new AdminFaqVO();
				adminFaqVO.setFaqSeq(vo.getListfaqSeq()[i]);
				// 문자열이 숫자인지 확인
				String str = "";
				if(vo.getListSortOrder().length>0){
					str = vo.getListSortOrder()[i];
				}
				if(!StringUtil.nvl(str).equals("")) {				
					boolean isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
					// 숫자라면 true
					if(isNumeric) {
						int num = Integer.parseInt(vo.getListSortOrder()[i]);
						if(0 <= num && 1000 > num) {
							adminFaqVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/faq/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/faq/list";
					}
				}
				
				adminFaqServiceImpl.doSortOrder(adminFaqVO);
			}

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/faq/list.do";
		}
	}

}
