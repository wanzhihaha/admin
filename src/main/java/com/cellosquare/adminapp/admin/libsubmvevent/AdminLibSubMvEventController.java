package com.cellosquare.adminapp.admin.libsubmvevent;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;
import com.cellosquare.adminapp.admin.libsubmvevent.service.AdminLibSubMvEventService;
import com.cellosquare.adminapp.admin.libsubmvevent.vo.AdminLibSubMvEventVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;

@Controller
public class AdminLibSubMvEventController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminLibSubMvEventService adminLibSubMvEventServiceImpl;
	
	@GetMapping("/celloSquareAdmin/libSubMvEvt/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		logger.debug("AdminEventController :: list");
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminLibSubMvEventServiceImpl.getTotalCount(vo);
		List<AdminLibSubMvEventVO> list = new ArrayList<AdminLibSubMvEventVO>();
		
		if(totalCount > 0) {
			list = adminLibSubMvEventServiceImpl.getList(vo);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);
		
		return "admin/basic/libsubmvevent/list";
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU","I");
		return "admin/basic/libsubmvevent/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/register.do")
	public String write(HttpServletRequest request, HttpServletResponse response, Model model,  MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			
			vo.setLangCd(sessionForm.getLangCd());
			
			if(!vo.getPcListOrginFile().isEmpty()) {

				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvLib")));
					
					FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
					vo.setPcListImgPath(fileVO.getFilePath());
					vo.setPcListImgFileNm(fileVO.getFileTempName());
					vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
					vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
				}else {
					throw new Exception();
				}
			}
			if(!vo.getMobileListOrginFile().isEmpty()) {
				
				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvLib")));
					
					FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
					vo.setMobileListImgPath(fileVO.getFilePath());
					vo.setMobileListImgFileNm(fileVO.getFileTempName());
					vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
					vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
				}else {
					throw new Exception();
				}
			}
			
			adminLibSubMvEventServiceImpl.doWrite(vo);
			
		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/libSubMvEvt/registerForm";
		}
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
		return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMvEvt/list.do";
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		AdminLibSubMvEventVO detailVO = adminLibSubMvEventServiceImpl.getDetail(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/libSubMvEvt/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/libsubmvevent/detail";
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		AdminLibSubMvEventVO detailVO = adminLibSubMvEventServiceImpl.getDetail(vo);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU","U");
		
		
		return "admin/basic/libsubmvevent/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		String plistFileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
		String mListFileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());
			
			AdminLibSubMvEventVO detailVO = adminLibSubMvEventServiceImpl.getDetail(vo);
			
			if(!vo.getPcListOrginFile().isEmpty()) {

				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvLib")));
					
					FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
					vo.setPcListImgPath(fileVO.getFilePath());
					vo.setPcListImgFileNm(fileVO.getFileTempName());
					vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
					vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
				}
			}else {
				if(plistFileDel.equals("Y")) {// 삭제를 체크했을시
					vo.setPcListImgPath("");
					vo.setPcListImgFileNm("");
					vo.setPcListImgSize("0");
					vo.setPcListImgOrgFileNm("");
					vo.setPcListImgAlt("");
				} else if(plistFileDel.equals("N")){
					vo.setPcListImgPath(detailVO.getPcListImgPath());
					vo.setPcListImgFileNm(detailVO.getPcListImgFileNm());
					vo.setPcListImgSize(String.valueOf(StringUtil.nvl(detailVO.getPcListImgSize(), "0")));
					vo.setPcListImgOrgFileNm(detailVO.getPcListImgOrgFileNm());
				} else {
					throw new Exception();
				}
			}
			if(!vo.getMobileListOrginFile().isEmpty()) {
				
				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvLib")));
					
					FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
					vo.setMobileListImgPath(fileVO.getFilePath());
					vo.setMobileListImgFileNm(fileVO.getFileTempName());
					vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
					vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
				}
			}else {
				if(mListFileDel.equals("Y")) {// 삭제를 체크했을시
					vo.setMobileListImgPath("");
					vo.setMobileListImgFileNm("");
					vo.setMobileListImgSize("0");
					vo.setMobileListImgOrgFileNm("");
					vo.setMobileListImgAlt("");
				} else if(mListFileDel.equals("N")){
					vo.setMobileListImgPath(detailVO.getMobileListImgPath());
					vo.setMobileListImgFileNm(detailVO.getMobileListImgFileNm());
					vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(detailVO.getMobileListImgSize(), "0")));
					vo.setMobileListImgOrgFileNm(detailVO.getMobileListImgOrgFileNm());
				}else {
					throw new Exception();
				}
			}
			
			
			adminLibSubMvEventServiceImpl.doUpdate(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("libSubMevSeqNo", vo.getLibSubMevSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/libSubMvEvt/registerForm";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		try {
			adminLibSubMvEventServiceImpl.doDelete(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("libSubMevSeqNo", vo.getLibSubMevSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/libSubMvEvt/detail";
		}
	}
	
	@ResponseBody
    @GetMapping("/celloSquareAdmin/mvEvtImgView.do")
    public String noTokendownload(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("vo") AdminLibSubMvEventVO vo, Model model) {

		AdminLibSubMvEventVO bean = adminLibSubMvEventServiceImpl.getDetail(vo);
		
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
	
	@GetMapping("/celloSquareAdmin/mvEvtFileDown.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo, RedirectAttributes rtt) throws IOException {
		
		AdminLibSubMvEventVO infoVO = adminLibSubMvEventServiceImpl.getDetail(vo);
		
		String fNm = null;
		String filePath = null;
			
		OutputStream os = null;
		FileInputStream fis = null;
		
		if(vo.getImgKinds().equals("pcList")) {
			fNm = infoVO.getPcListImgOrgFileNm();
			filePath = infoVO.getPcListImgPath() + "/" + FilenameUtils.getName(infoVO.getPcListImgFileNm());
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = infoVO.getMobileListImgOrgFileNm();
			filePath = infoVO.getMobileListImgPath() + "/" + FilenameUtils.getName(infoVO.getMobileListImgFileNm());
		}
		
		try {
			if(infoVO != null) {
				
				
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
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
		if(os != null) {
			os.flush();
		}
	}
	
	@PostMapping("/celloSquareAdmin/libSubMvEvt/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminLibSubMvEventVO vo) {

		try {
			AdminLibSubMvEventVO adminLibSubEvtVO = null;
			for(int i = 0; i < vo.getListMvEvtSeq().length; i++) {
				adminLibSubEvtVO = new AdminLibSubMvEventVO();
				adminLibSubEvtVO.setLibSubMevSeqNo(vo.getListMvEvtSeq()[i]);
				
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
							adminLibSubEvtVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/libsubmvevent/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/libsubmvevent/list";
					}
				}
				
				adminLibSubMvEventServiceImpl.doSortOrder(adminLibSubEvtVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/libSubMvEvt/list.do";
		}
	}
	
	@GetMapping("/celloSquareAdmin/libSubMvEvt/searchEvent.do")
	public String searchEvent(HttpServletRequest request, HttpServletResponse response, Model model,
	@ModelAttribute("vo") AdminLibSubMvEventVO vo) {
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminLibSubMvEventServiceImpl.popupTotalCount(vo);
		
		List<AdminEventVO> list = new ArrayList<AdminEventVO>();
		
		if(totalCount > 0) {
			list = adminLibSubMvEventServiceImpl.getPopUpList(vo);
		}
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/popup/libsubmvevent/searchEventPopup";
		
	}
	
}
