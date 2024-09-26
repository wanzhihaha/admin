package com.cellosquare.adminapp.admin.mainviewlibrary;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.mainviewlibrary.service.AdminMainViewLibraryService;
import com.cellosquare.adminapp.admin.mainviewlibrary.vo.AdminMainViewLibraryVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;

@Controller
public class AdminMainViewLibraryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminMainViewLibraryService adminMainViewLibraryServiceImpl;
	
	/**            
	 * 라이브러리 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/mainViewLibrary/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {

		logger.debug("AdminMainViewLibraryController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		
		try {
			int totalCount = adminMainViewLibraryServiceImpl.getTotalCount(vo);
			List<AdminMainViewLibraryVO> list = new ArrayList<AdminMainViewLibraryVO>();
			
			if (totalCount > 0) {
				list = adminMainViewLibraryServiceImpl.getList(vo);

				model.addAttribute("totalCount", totalCount);
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "admin/basic/mainviewlibrary/list";
	}
	
	/**
	 * 라이브러리 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/mainviewlibrary/registerForm";
	}

	/**
	 * 라이브러리 등록하기
	 * @param request
	 * @param resonse
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/register.do")
	public String doWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
			, Model model, @ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		
		logger.debug("AdminMainViewLibraryController :: doWrite");
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());
			
			adminMainViewLibraryServiceImpl.doWrite(vo);

		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/mainViewLibrary/registerForm.do";
		}
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
		return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/mainViewLibrary/list.do";
	
	}
	
	
	/**
	 * 라이브러리 관련 게시글 팝업
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @param index
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/mainViewLibrary/librarySearch.do")
	public String librarySearch(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo)  {

		logger.debug("AdminMainViewLibraryController :: librarySearch");
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminMainViewLibraryServiceImpl.libraryTotalCount(vo);
		
		List<AdminMainViewLibraryVO> list = new ArrayList<AdminMainViewLibraryVO>();
		
		if(totalCount > 0) {
			list = adminMainViewLibraryServiceImpl.librarySearch(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/popup/mainviewlibrary/librarySearch";
	} 
	
	

	/**
	 * 라이브러리 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: detail");

		AdminMainViewLibraryVO detailVO = adminMainViewLibraryServiceImpl.getDetail(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}
	@GetMapping("/celloSquareAdmin/mainViewLibrary/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/mainviewlibrary/detail";
	}

	/**
	 * 라이브러리 수정하기 폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: updateForm");

		AdminMainViewLibraryVO detailVO = adminMainViewLibraryServiceImpl.getDetail(vo);

		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/mainviewlibrary/registerForm";
	}

	/**
	 * 라이브러리 수정하기
	 * @param request
	 * @param response
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, Model model, 
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: doUpdate");
		
		try {
			
			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());
				
			      adminMainViewLibraryServiceImpl.doUpdate(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("mvLibSeqNo", vo.getMvLibSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/mainViewLibrary/updateForm";
		}
	}

	/** 
	 * 라이브러리 삭제하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: delete");
		try {
			adminMainViewLibraryServiceImpl.doDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("mvLibSeqNo", vo.getMvLibSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"),
					null, null, null, true);
			return "admin/basic/mainViewLibrary/detail";
		}

	}
	
	/**
     * 이미지 썸네일
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
	
	  @ResponseBody
	  @GetMapping("/celloSquareAdmin/mainViewLibraryImgView.do") 
	  public String imgView(HttpServletRequest request, HttpServletResponse response, Model model, 
			  @ModelAttribute("vo") AdminMainViewLibraryVO vo) {
	  
	  logger.debug("AdminMainViewLibraryController :: imgView");
	  
	  AdminMainViewLibraryVO adminMainViewLibraryVO = adminMainViewLibraryServiceImpl.getDetail(vo);
	  
	  try { 
		  if(adminMainViewLibraryVO != null) { 
			  FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
			  if(vo.getImgKinds().equals("pcList")) { 
				  SafeFile pcListFile = new SafeFile(adminMainViewLibraryVO.getPcListImgPath() , FilenameUtils.getName(adminMainViewLibraryVO.getPcListImgFileNm())); 
				  if (pcListFile.isFile()) {
					  fileDownLoadManager.fileFlush(pcListFile, adminMainViewLibraryVO.getPcListImgOrgFileNm()); 
				  } 
			  } else if (vo.getImgKinds().equals("mobileList")) {
              	SafeFile mobileDetailFile = new SafeFile(adminMainViewLibraryVO.getPcListImgPath() , FilenameUtils.getName(adminMainViewLibraryVO.getPcListImgFileNm()));
                if (mobileDetailFile.isFile()) {
                	fileDownLoadManager.fileFlush(mobileDetailFile, adminMainViewLibraryVO.getPcListImgOrgFileNm());
                }
			  }
		  }
	  } catch (Exception e) { 
	  } 
	  	  return null; 
	  }
	 
	/**
	 * 정렬순서 저장
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/mainViewLibrary/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMainViewLibraryVO vo) {
		logger.debug("AdminMainViewLibraryController :: doSortOrder");
		try {
			AdminMainViewLibraryVO adminMainViewLibraryVO = null;
			for(int i = 0; i < vo.getListMvLibSeq().length; i++) {
				adminMainViewLibraryVO = new AdminMainViewLibraryVO();
				adminMainViewLibraryVO.setMvLibSeqNo(vo.getListMvLibSeq()[i]);
				
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
							adminMainViewLibraryVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/mainviewlibrary/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/mainviewlibrary/list";
					}
				}
				
				adminMainViewLibraryServiceImpl.doSortOrder(adminMainViewLibraryVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/mainViewLibrary/list.do";
		}
	}
}
