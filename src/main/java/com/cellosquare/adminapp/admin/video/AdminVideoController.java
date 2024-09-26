package com.cellosquare.adminapp.admin.video;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.common.util.XssUtils;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.video.service.AdminVideoService;
import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import com.nhncorp.lucy.security.xss.XssPreventer;

@Controller
public class AdminVideoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminVideoService adminVideoServiceImpl;
	
	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	
	/**
	 * 비디오 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/video/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo) {
		
		logger.debug("AdminVideoController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminVideoServiceImpl.getTotalCount(vo);
		List<AdminVideoVO> list = new ArrayList<AdminVideoVO>();
		
		if(totalCount > 0 ) {
			list = adminVideoServiceImpl.getList(vo);
		}
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/basic/video/list";
	}
	
	
	/**
	 * 비디오 등록폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/video/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo)  {

//		logger.debug("AdminVideoController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/video/registerForm";
	}
	
	/**
	 * 비디오 등록
	 * @param request
	 * @param response
	 * @param model
	 * @param muServletRequest
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/video/register.do")
	public String regist(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminVideoVO vo)  {
		
		logger.debug("AdminVideoController :: register");
		
		
		try {
			
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());

			vo.setContents(XssUtils.cleanXssNamoContent(vo.getContents()));
			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));
			
			//메타 SEO 저장
			if(adminSeoServiceImpl.doSeoWrite(request, response, muServletRequest, vo) > 0) {
				adminVideoServiceImpl.regist(request, response, muServletRequest, vo);
			} else {
				throw new Exception();
			}
			
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/video/list.do";
		} catch (Exception e) {
//			e.printStackTrace();
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/video/registerForm";
		}
	}
	
	
    /**
	 * 비디오 상세보기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/video/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo)  {

		logger.debug("AdminVideoController :: detail");
		
		AdminVideoVO detailVO = adminVideoServiceImpl.getDetail(vo);

		detailVO.setDetlInfo(XssPreventer.unescape(detailVO.getDetlInfo()));
		detailVO.setContents(XssPreventer.unescape(detailVO.getContents()));
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/video/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/video/detail";
	}
	
	/**
	 * 비디오 수정폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/video/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo)  {

		logger.debug("AdminVideoController :: updateForm");

		AdminVideoVO detailVO = adminVideoServiceImpl.getDetail(vo);
		
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/video/registerForm";
	}
	
	/**
	 * 비디오 수정
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @param muServletRequest
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/video/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminVideoVO vo)  {
		
		logger.debug("AdminVideoController :: update");
		
		try {
			
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

			vo.setContents(XssUtils.cleanXssNamoContent(vo.getContents()));
			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));

			//파일 업로드 시작
			if(adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {
				
				adminVideoServiceImpl.update(request, response, muServletRequest, vo);
			} else {
				// 강제 Exception
				throw new Exception(); 
			}
			
			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("videoSeqNo", vo.getVideoSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/video/registerForm";
		}
	}
	
	
    /**
     * 이미지 썸네일
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @ResponseBody
    @GetMapping("/celloSquareAdmin/videoImgView.do")
    public String eventImgView(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute("vo") AdminVideoVO vo) {

    	logger.debug("AdminEventController :: imageDownload");
    	AdminVideoVO adminVideoVO = adminVideoServiceImpl.getDetail(vo);
    	
    	try {
            if(adminVideoVO != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if(vo.getImgKinds().equals("pcList")) {
                	SafeFile pcListFile = new SafeFile(adminVideoVO.getPcListImgPath() , FilenameUtils.getName(adminVideoVO.getPcListImgFileNm()));
                   if (pcListFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcListFile, adminVideoVO.getPcListImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("pcDetail")) {
                	SafeFile pcDetailFile = new SafeFile(adminVideoVO.getPcDetlImgPath() , FilenameUtils.getName(adminVideoVO.getPcDetlImgFileNm()));
                   if (pcDetailFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcDetailFile, adminVideoVO.getPcDetlImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("mobileList")) {
                	SafeFile mobileListfile = new SafeFile(adminVideoVO.getMobileListImgPath() , FilenameUtils.getName(adminVideoVO.getMobileListImgFileNm()));
                   if (mobileListfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileListfile, adminVideoVO.getMobileListImgOrgFileNm());						
                   }
                } else if (vo.getImgKinds().equals("mobileDetail")) {
                	SafeFile mobileDetailfile = new SafeFile(adminVideoVO.getMobileDetlImgPath() , FilenameUtils.getName(adminVideoVO.getMobileDetlImgFileNm()));
                   if (mobileDetailfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileDetailfile, adminVideoVO.getMobileDetlImgOrgFileNm());						
                   }
                }
            }
		} catch (Exception e) {
//			e.printStackTrace();
		}
    	
        return null;
    }
    
    /**
     * 이미지 다운로드
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @GetMapping("/celloSquareAdmin/videoDownload.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo) throws IOException {

    	AdminVideoVO adminVideoVO = adminVideoServiceImpl.getDetail(vo);

    	String fNm = null;
		String filePath = null;
		if(vo.getImgKinds().equals("pcList")) {
			fNm = adminVideoVO.getPcListImgOrgFileNm();
			filePath = adminVideoVO.getPcListImgPath() + "/" + FilenameUtils.getName(adminVideoVO.getPcListImgFileNm());
		} else if (vo.getImgKinds().equals("pcDetail")) {
			fNm = adminVideoVO.getPcDetlImgOrgFileNm();
			filePath = adminVideoVO.getPcDetlImgPath() + "/" + FilenameUtils.getName(adminVideoVO.getPcDetlImgFileNm());
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = adminVideoVO.getMobileListImgOrgFileNm();
			filePath = adminVideoVO.getMobileListImgPath() + "/" + FilenameUtils.getName(adminVideoVO.getMobileListImgFileNm());
		} else if (vo.getImgKinds().equals("mobileDetail")) {
	    	fNm = adminVideoVO.getMobileDetlImgOrgFileNm();
	    	filePath = adminVideoVO.getMobileDetlImgPath() + "/" + FilenameUtils.getName(adminVideoVO.getMobileDetlImgFileNm());
	    }

		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminVideoVO != null) {

				SafeFile file = new SafeFile(filePath);

				response.setContentType("application/octet-stream; charset=utf-8");
				response.setContentLength((int) file.length());

				String browser = FileDownUtil.getBrowser(request);
				String disposition = FileDownUtil.getDisposition(fNm, browser);

				//fileName으로 다운받아라 라는뜻  attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지
				response.setHeader("Content-Disposition", disposition);
				response.setHeader("Content-Transfer-Encoding", "binary");

				os = response.getOutputStream();
				fis = new FileInputStream(file);

				FileCopyUtils.copy(fis, os);
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
		if(os != null) {
			os.flush();
		}
	}
    
    
    /**
	 * 비디오 삭제하기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
    @PostMapping("/celloSquareAdmin/video/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo)  {

		logger.debug("AdminVideoController :: delete");
		try {
			adminVideoServiceImpl.delete(vo);

			adminSeoServiceImpl.doSeoDelete(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("videoSeqNo", vo.getVideoSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/video/detail";
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
    @PostMapping("/celloSquareAdmin/video/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminVideoVO vo) {
		logger.debug("AdminVideoController :: doSortOrder");
		try {
			AdminVideoVO adminVideoVO = null;
			for(int i = 0; i < vo.getListVideoSeqNo().length; i++) {
				adminVideoVO = new AdminVideoVO();
				adminVideoVO.setVideoSeqNo(vo.getListVideoSeqNo()[i]);
				
				// 문자열이 숫자인지 확인
				String str = "";
				if(vo.getListOrdb().length>0){
					str = vo.getListOrdb()[i];
				}
				if(!StringUtil.nvl(str).equals("")) {				
					boolean isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
					// 숫자라면 true
					if(isNumeric) {
						int num = Integer.parseInt(vo.getListOrdb()[i]);
						if(0 <= num && 1000 > num) {
							adminVideoVO.setOrdb(vo.getListOrdb()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/video/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/video/list";
					}
				}
				
				adminVideoServiceImpl.doSortOrder(adminVideoVO);
			}
			
			
			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/video/list.do";
		}
	}
    
}
