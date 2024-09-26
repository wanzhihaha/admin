package com.cellosquare.adminapp.admin.libsubmv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.libsubmv.service.AdminLibSubMvService;
import com.cellosquare.adminapp.admin.libsubmv.vo.AdminLibSubMvVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;

@Controller
public class AdminLibSubMvController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminLibSubMvService adminLibSubMvServiceImpl;
	
	
	/**
	 * 리스트
	 * @param request
	 * @param reponse
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/libSubMv/list.do")
	public String list(HttpServletRequest request, HttpServletResponse reponse, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		
		logger.debug("AdminLibSubMvController :: list");
		System.out.println("값 : " + vo.getSearchType());
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		vo.setSearchType(StringUtil.nvl(vo.getSearchType(), "LIBSUBMV_02"));
		
		int totalCount = adminLibSubMvServiceImpl.getTotalCount(vo);
		List<AdminLibSubMvVO> list = new ArrayList<AdminLibSubMvVO>();
		
		if(totalCount > 0 ) {
			list = adminLibSubMvServiceImpl.getList(vo);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);
		
		
		return "admin/basic/libsubmv/list";
	}
	
	/**
	 * 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/libsubmv/registerForm";
	}
	
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/register.do")
	public String regist(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			
			vo.setLangCd(sessionForm.getLangCd());
			
			adminLibSubMvServiceImpl.regist(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.write.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMv/registerForm.do";
		}
	}
	
	/**
	 * 상세 페이지
	 * @param request
	 * @param reponse
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse reponse, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		AdminLibSubMvVO detailVO = adminLibSubMvServiceImpl.getDetail(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/libSubMv/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/libsubmv/detail";
	}
	
	/**
	 * 수정페이지
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		
		AdminLibSubMvVO detailVO = adminLibSubMvServiceImpl.getDetail(vo);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU","U");
		
		return "admin/basic/libsubmv/registerForm";
	}
	
	/**
	 * 수정
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, 
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
	
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

			adminLibSubMvServiceImpl.doUpdate(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("libSubMvSeqNo", vo.getLibSubMvSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			e.printStackTrace();
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMv/updateForm.do";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/libSubMv/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {
		
		try {
			adminLibSubMvServiceImpl.doDelete(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("libSubMvSeqNo", vo.getLibSubMvSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMv/updateForm.do";
		}
		
	}
	
	/**
	 * 이미지 가져오기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@GetMapping("/celloSquareAdmin/libSubMvImgView.do")
    public String noTokendownload(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("vo") AdminLibSubMvVO vo, Model model) {

		
		logger.debug("AdminLibSubMvController :: libSubMvImgView");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		AdminLibSubMvVO bean = adminLibSubMvServiceImpl.getDetail(vo);
		
    	try {
            if(bean != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if(vo.getImgKinds().equals("pcList")) {
					SafeFile pcListFile = new SafeFile(bean.getPcListImgPath() , FilenameUtils.getName(bean.getPcListImgFileNm()));
                	
					if (pcListFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcListFile, bean.getPcListImgOrgFileNm());
					}
                } else if (vo.getImgKinds().equals("mobileList")) {
					SafeFile mobileListfile = new SafeFile(bean.getMobileListImgPath() , FilenameUtils.getName(bean.getMobileListImgFileNm()));
					
                   	if (mobileListfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileListfile, bean.getMobileListImgOrgFileNm());						
                   	}
                }
            }
		} catch (Exception e) {
		}
		
        return null;
    }
	
	/**
	 * 정렬 순서 수정
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/libSubMv/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo) {

		try {
			AdminLibSubMvVO adminLibSubMvVO = null;
			for(int i = 0; i < vo.getListMvSqprdSeq().length; i++) {
				adminLibSubMvVO = new AdminLibSubMvVO();
				adminLibSubMvVO.setLibSubMvSeqNo(vo.getListMvSqprdSeq()[i]);
				
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
							adminLibSubMvVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/libsubmv/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/libsubmv/list";
					}
				}
				
				adminLibSubMvServiceImpl.doSortOrder(adminLibSubMvVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMv/list.do";
		}
	}
	
	/**
	 * 팝업창 
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/libSubMv/searchLibrary.do")
	public String searchPopup(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvVO vo)  {

		logger.debug("AdminLibSubMvController :: searchPopup");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		vo.setSearchType(StringUtil.nvl(vo.getSearchType(), "LIBSUBMV_02"));
		
		int totalCount = adminLibSubMvServiceImpl.popupTotalCount(vo);

		List<AdminLibSubMvVO> list = new ArrayList<AdminLibSubMvVO>();

		if(totalCount > 0) {
			list = adminLibSubMvServiceImpl.getPopUpList(vo);
		}
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/popup/libsubmv/searchLibraryPopup";
	}
	
	
}
