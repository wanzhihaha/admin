package com.cellosquare.adminapp.admin.event;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
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
import com.cellosquare.adminapp.admin.event.service.AdminEventService;
import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import com.nhncorp.lucy.security.xss.XssPreventer;

@Controller
public class AdminEventController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminEventService adminEventServiceImpl;

	@Autowired
	private AdminSeoService adminSeoServiceImpl;

	/**
	 * 이벤트 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/event/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo) {

		logger.debug("AdminEventController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		//vpn 접속시 ip확인
		//		try {
		//			System.out.println(InetAddress.getLocalHost().getHostAddress());
		//		} catch (UnknownHostException e) {
		//			e.printStackTrace();
		//		}

		int totalCount = adminEventServiceImpl.getTotalCount(vo);
		List<AdminEventVO> list = new ArrayList<AdminEventVO>();

		if(totalCount > 0 ) {
			list = adminEventServiceImpl.getList(vo);
			// DB에서 TimeStamp형태로 들어와서 값이없을경우 0001-01-01로 값을 받기때문에 다시 없얘주는 작업
			// 다른방법은 쿼리문에서 toChar에서 TimeStamp를 없얘고 String에서 날짜 사이에 -값을 추가해주는 로직을 짜야됨 
			for (int i = 0; i < list.size(); i++) {
				String stDate = StringUtil.nvl(list.get(i).getEvtStatDtm(), "");
				if(stDate.equals("0001-01-01")) {
					list.get(i).setEvtStatDtm(""); 
				}
				String endDate = StringUtil.nvl(list.get(i).getEvtEndDtm(), "");
				if(endDate.equals("0001-01-01")) {
					list.get(i).setEvtEndDtm(""); 
				}
			}
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/basic/event/list";
	}


	/**
	 * 이벤트 등록폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/event/registerForm";
	}

	/**
	 * 이벤트 등록
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @param muServletRequest
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/register.do")
	public String regist(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: register");

		try {
			//-값 제거
			String startDate = vo.getEvtStatDtm().replaceAll("-", "");
			String endDate = vo.getEvtEndDtm().replaceAll("-", "");

			vo.setEvtStatDtm(startDate);
			vo.setEvtEndDtm(endDate);

			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());

			vo.setEvtDetlContent(XssUtils.cleanXssNamoContent(vo.getEvtDetlContent()));

			//메타 SEO 저장
			if(adminSeoServiceImpl.doSeoWrite(request, response, muServletRequest, vo) > 0) {
				adminEventServiceImpl.regist(request, response, muServletRequest, vo);
			} else {
				throw new Exception();
			}

			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/event/list.do";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/event/registerForm";
		}
	}


	/**
	 * 이벤트 상세보기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: detail");

		AdminEventVO detailVO = adminEventServiceImpl.getImg(vo);

		// xss 원복 태그가 적용되게끔 설정을 해줌
		detailVO.setEvtDetlContent(XssPreventer.unescape(detailVO.getEvtDetlContent()));
				
		String stDate = StringUtil.nvl(detailVO.getEvtStatDtm(), "");
		if(stDate.equals("0001-01-01")) {
			detailVO.setEvtStatDtm(""); 
		}
		String endDate = StringUtil.nvl(detailVO.getEvtEndDtm(), "");
		if(endDate.equals("0001-01-01")) {
			detailVO.setEvtEndDtm(""); 
		}
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/event/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/event/detail";
	}
	/**
	 * 이벤트 수정폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: updateForm");

		AdminEventVO detailVO = adminEventServiceImpl.getImg(vo);

		String stDate = StringUtil.nvl(detailVO.getEvtStatDtm(), "");
		if(stDate.equals("0001-01-01")) {
			detailVO.setEvtStatDtm(""); 
		}
		String endDate = StringUtil.nvl(detailVO.getEvtEndDtm(), "");
		if(endDate.equals("0001-01-01")) {
			detailVO.setEvtEndDtm(""); 
		}
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/event/registerForm";
	}

	/**
	 * 이벤트 수정
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @param muServletRequest
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: update");

		try {
			//-값 제거
			String startDate = vo.getEvtStatDtm().replaceAll("-", "");
			String endDate = vo.getEvtEndDtm().replaceAll("-", "");

			vo.setEvtStatDtm(startDate);
			vo.setEvtEndDtm(endDate);

			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

			vo.setEvtDetlContent(XssUtils.cleanXssNamoContent(vo.getEvtDetlContent()));

			//파일 업로드 시작
			if(adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {

				adminEventServiceImpl.update(request, response, muServletRequest, vo);
			} else {
				// 강제 Exception
				throw new Exception(); 
			}

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("evtSeqNo", vo.getEvtSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/event/registerForm";
		}
	}

	/**
	 * 이미지 썸네일
	 *
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @param adminSeoVO
	 * @return
	 */
	@ResponseBody
	@GetMapping("/celloSquareAdmin/eventImgView.do")
	public String eventImgView(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo) {

		logger.debug("AdminEventController :: imageDownload");
		AdminEventVO adminEventVO = adminEventServiceImpl.getImg(vo);

		try {
			if(adminEventVO != null) {
				FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
				if(vo.getImgKinds().equals("pcList")) {
					SafeFile pcListFile = new SafeFile(adminEventVO.getPcListImgPath() , FilenameUtils.getName(adminEventVO.getPcListImgFileNm()));
					if(pcListFile.isFile()) {
						fileDownLoadManager.fileFlush(pcListFile, adminEventVO.getPcListImgOrgFileNm());
					}
				} else if (vo.getImgKinds().equals("pcDetail")) {
					SafeFile pcDetailFile = new SafeFile(adminEventVO.getPcDetlImgPath() , FilenameUtils.getName(adminEventVO.getPcDetlImgFileNm()));
					if(pcDetailFile.isFile()) {
						fileDownLoadManager.fileFlush(pcDetailFile, adminEventVO.getPcDetlImgOrgFileNm());
					}
				} else if (vo.getImgKinds().equals("mobileList")) {
					SafeFile mobileListfile = new SafeFile(adminEventVO.getMobileListImgPath() , FilenameUtils.getName(adminEventVO.getMobileListImgFileNm()));
					if(mobileListfile.isFile()) {
						fileDownLoadManager.fileFlush(mobileListfile, adminEventVO.getMobileListImgOrgFileNm());
					}
				} else if (vo.getImgKinds().equals("mobileDetail")) {
					SafeFile mobileDetailfile = new SafeFile(adminEventVO.getMobileDetlImgPath() , FilenameUtils.getName(adminEventVO.getMobileDetlImgFileNm()));
					if(mobileDetailfile.isFile()) {
						fileDownLoadManager.fileFlush(mobileDetailfile, adminEventVO.getMobileDetlImgOrgFileNm());
					}
				}

			}
		} catch (Exception e) {
			//			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 이벤트 이미지 다운로드
	 *
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/eventDownload.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo) throws IOException {

		AdminEventVO adminEventVO = adminEventServiceImpl.getImg(vo);

		String fNm = null;
		String filePath = null;
		if(vo.getImgKinds().equals("pcList")) {
			fNm = adminEventVO.getPcListImgOrgFileNm();
			filePath = adminEventVO.getPcListImgPath() + "/" + adminEventVO.getPcListImgFileNm();
		} else if (vo.getImgKinds().equals("pcDetail")) {
			fNm = adminEventVO.getPcDetlImgOrgFileNm();
			filePath = adminEventVO.getPcDetlImgPath() + "/" + adminEventVO.getPcDetlImgFileNm();
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = adminEventVO.getMobileListImgOrgFileNm();
			filePath = adminEventVO.getMobileListImgPath() + "/" + adminEventVO.getMobileListImgFileNm();
		} else if (vo.getImgKinds().equals("mobileDetail")) {
			fNm = adminEventVO.getMobileDetlImgOrgFileNm();
			filePath = adminEventVO.getMobileDetlImgPath() + "/" + adminEventVO.getMobileDetlImgFileNm();
		}


		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminEventVO != null) {

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
	 * 이벤트 삭제하기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/event/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo)  {

		logger.debug("AdminEventController :: delete");
		try {
			
			adminEventServiceImpl.delete(vo);

			adminSeoServiceImpl.doSeoDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("evtSeqNo", vo.getEvtSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/event/detail";
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
	@PostMapping("/celloSquareAdmin/event/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminEventVO vo) {
		logger.debug("AdminEventController :: doSortOrder");
		try {
			AdminEventVO adminEventVO = null;
			for(int i = 0; i < vo.getListEvtSeqNo().length; i++) {
				adminEventVO = new AdminEventVO();
				adminEventVO.setEvtSeqNo(vo.getListEvtSeqNo()[i]);
				
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
							adminEventVO.setOrdb(vo.getListOrdb()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/event/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/event/list";
					}
				}
				adminEventServiceImpl.doSortOrder(adminEventVO);
			}


			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/event/list.do";
		}
	}


}
