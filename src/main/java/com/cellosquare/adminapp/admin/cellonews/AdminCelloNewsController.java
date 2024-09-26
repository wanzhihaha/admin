package com.cellosquare.adminapp.admin.cellonews;

import java.io.File;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.cellonews.service.AdminCelloNewsService;
import com.cellosquare.adminapp.admin.cellonews.vo.AdminCelloNewsVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import com.nhncorp.lucy.security.xss.XssPreventer;

@Controller
public class AdminCelloNewsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminCelloNewsService adminCelloNewsServiceImpl;
	
	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	
	@GetMapping("/celloSquareAdmin/celloNews/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {

		logger.debug("AdminCelloNewsController :: list");
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminCelloNewsServiceImpl.getTotalCount(vo);
		List<AdminCelloNewsVO> list = new ArrayList<AdminCelloNewsVO>();
		
		if(totalCount > 0 ) {
			list = adminCelloNewsServiceImpl.getList(vo); 
		}
		
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/basic/cellonews/list";
	}
	
	@PostMapping("/celloSquareAdmin/celloNews/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/cellonews/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/celloNews/register.do")
	public String write(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			
			vo.setLangCd(sessionForm.getLangCd());

			vo.setNewsDetlInfo(XssUtils.cleanXssNamoContent(vo.getNewsDetlInfo()));
			
			// 메타 SEO 저장
			if(adminSeoServiceImpl.doSeoWrite(request, response, muServletRequest, vo) > 0) {

				// 대표이미지가 있을경우
				if(!vo.getPcListOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
						vo.setPcListImgPath(fileVO.getFilePath());
						vo.setPcListImgFileNm(fileVO.getFileTempName());
						vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
					}
				}
				
				if(!vo.getPcDetailOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");
						vo.setPcDetlImgPath(fileVO.getFilePath());
						vo.setPcDetlImgFileNm(fileVO.getFileTempName());
						vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
					}
				}
				
				if(!vo.getMobileListOrginFile().isEmpty()) {
					
					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
						vo.setMobileListImgPath(fileVO.getFilePath());
						vo.setMobileListImgFileNm(fileVO.getFileTempName());
						vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
					}
				}
				
				if(!vo.getMobileDetailOrginFile().isEmpty()) {
					
					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");
						vo.setMobileDetlImgPath(fileVO.getFilePath());
						vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
						vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
					}
				}

				adminCelloNewsServiceImpl.doWrite(vo);
			} else {
				throw new Exception();
			}
		
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/celloNews/list.do";
			
		} catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/cellonews/registerForm";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/celloNews/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		
		AdminCelloNewsVO detailVO = adminCelloNewsServiceImpl.getDetail(vo);
		
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		//xss 원복
		detailVO.setNewsDetlInfo(XssPreventer.unescape(detailVO.getNewsDetlInfo()));
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/celloNews/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/cellonews/detail";
	}
	
	
	@PostMapping("/celloSquareAdmin/celloNews/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		
		AdminCelloNewsVO detailVO = adminCelloNewsServiceImpl.getDetail(vo);
		
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("contIU","U");
		
		return "admin/basic/cellonews/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/celloNews/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		
		String plistFileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
		String pDetlFileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
		String mListFileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
		String mDetlFileDel = StringUtil.nvl(vo.getMobileDetailFileDel(), "N");
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

			vo.setNewsDetlInfo(XssUtils.cleanXssNamoContent(vo.getNewsDetlInfo()));
			
			if(adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {
				
				AdminCelloNewsVO detailVO = adminCelloNewsServiceImpl.getDetail(vo);
				if(!vo.getPcListOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
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
					} else {
						
						vo.setPcListImgPath(detailVO.getPcListImgPath());
						vo.setPcListImgFileNm(detailVO.getPcListImgFileNm());
						vo.setPcListImgSize(String.valueOf(StringUtil.nvl(detailVO.getPcListImgSize(), "0")));
						vo.setPcListImgOrgFileNm(detailVO.getPcListImgOrgFileNm());
					}
				}
				
				if(!vo.getPcDetailOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");
						vo.setPcDetlImgPath(fileVO.getFilePath());
						vo.setPcDetlImgFileNm(fileVO.getFileTempName());
						vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
					}
				}else {
					if(pDetlFileDel.equals("Y")) {// 삭제를 체크했을시
						vo.setPcDetlImgPath("");
						vo.setPcDetlImgFileNm("");
						vo.setPcDetlImgSize("0");
						vo.setPcDetlImgOrgFileNm("");
						vo.setPcDetlImgAlt("");
					} else {
						
						vo.setPcDetlImgPath(detailVO.getPcDetlImgPath());
						vo.setPcDetlImgFileNm(detailVO.getPcDetlImgFileNm());
						vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(detailVO.getPcDetlImgSize(), "0")));
						vo.setPcDetlImgOrgFileNm(detailVO.getPcDetlImgOrgFileNm());
					}
				}
				
				if(!vo.getMobileListOrginFile().isEmpty()) {
					
					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
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
					} else {
						
						vo.setMobileListImgPath(detailVO.getMobileListImgPath());
						vo.setMobileListImgFileNm(detailVO.getMobileListImgFileNm());
						vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(detailVO.getMobileListImgSize(), "0")));
						vo.setMobileListImgOrgFileNm(detailVO.getMobileListImgOrgFileNm());
					}
				}
				
				if(!vo.getMobileDetailOrginFile().isEmpty()) {
					
					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathNews")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");
						vo.setMobileDetlImgPath(fileVO.getFilePath());
						vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
						vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
					}
				}else {
					if(mDetlFileDel.equals("Y")) {// 삭제를 체크했을시
						vo.setMobileDetlImgPath("");
						vo.setMobileDetlImgFileNm("");
						vo.setMobileDetlImgSize("0");
						vo.setMobileDetlImgOrgFileNm("");
						vo.setMobileDetlImgAlt("");
					} else {
						
						vo.setMobileDetlImgPath(detailVO.getMobileDetlImgPath());
						vo.setMobileDetlImgFileNm(detailVO.getMobileDetlImgFileNm());
						vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(detailVO.getMobileDetlImgSize(), "0")));
						vo.setMobileDetlImgOrgFileNm(detailVO.getMobileDetlImgOrgFileNm());
					}
				}
				adminCelloNewsServiceImpl.doUpdate(vo);
			} else {
				// 강제 Exception
				throw new Exception(); 
			}
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("newsSeqNo", vo.getNewsSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/cellonews/registerForm";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/celloNews/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {
		
		try {
			adminCelloNewsServiceImpl.doDelete(vo);
			
			// seo정보 삭제
			adminSeoServiceImpl.doSeoDelete(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("newsSeqNo", vo.getNewsSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/cellonews/detail";
		}
		
	}
	
	@ResponseBody
    @GetMapping("/celloSquareAdmin/newsImgView.do")
    public String noTokendownload(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("vo") AdminCelloNewsVO vo, Model model) {

		AdminCelloNewsVO bean = adminCelloNewsServiceImpl.getDetail(vo);
		
    	try {
            if(bean != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if(vo.getImgKinds().equals("pcList")) {
                	SafeFile pcListFile = new SafeFile(bean.getPcListImgPath() , FilenameUtils.getName(bean.getPcListImgFileNm()));
                   if (pcListFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcListFile, bean.getPcListImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("pcDetail")) {
                	SafeFile pcDetailFile = new SafeFile(bean.getPcDetlImgPath() , FilenameUtils.getName(bean.getPcDetlImgFileNm()));
                   if (pcDetailFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcDetailFile, bean.getPcDetlImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("mobileList")) {
                	SafeFile mobileListfile = new SafeFile(bean.getMobileListImgPath() , FilenameUtils.getName(bean.getMobileListImgFileNm()));
                   if (mobileListfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileListfile, bean.getMobileListImgOrgFileNm());						
                   }
                } else if (vo.getImgKinds().equals("mobileDetail")) {
                	SafeFile mobileDetailfile = new SafeFile(bean.getMobileDetlImgPath() , FilenameUtils.getName(bean.getMobileDetlImgFileNm()));
                   if (mobileDetailfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileDetailfile, bean.getMobileDetlImgOrgFileNm());						
                   }
                }
            }
		} catch (Exception e) {
		}
		
        return null;
    }
	
	
	@GetMapping("/celloSquareAdmin/newsFileDown.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo, RedirectAttributes rtt) throws IOException {
		
		AdminCelloNewsVO infoVO = adminCelloNewsServiceImpl.getDetail(vo);
		
		String fNm = null;
		String filePath = null;
			
		OutputStream os = null;
		FileInputStream fis = null;
		
		if(vo.getImgKinds().equals("pcList")) {
			fNm = infoVO.getPcListImgOrgFileNm();
			filePath = infoVO.getPcListImgPath() + "/" + infoVO.getPcListImgFileNm();
		} else if (vo.getImgKinds().equals("pcDetail")) {
			fNm = infoVO.getPcDetlImgOrgFileNm();
			filePath = infoVO.getPcDetlImgPath() + "/" + infoVO.getPcDetlImgFileNm();
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = infoVO.getMobileListImgOrgFileNm();
			filePath = infoVO.getMobileListImgPath() + "/" + infoVO.getMobileListImgFileNm();
		} else if (vo.getImgKinds().equals("mobileDetail")) {
	    	fNm = infoVO.getMobileDetlImgOrgFileNm();
	    	filePath = infoVO.getMobileDetlImgPath() + "/" + infoVO.getMobileDetlImgFileNm();
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
	
	@PostMapping("/celloSquareAdmin/celloNews/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminCelloNewsVO vo) {

		try {
			AdminCelloNewsVO adminCelNewsVO = null;
			for(int i = 0; i < vo.getListNewsSeq().length; i++) {
				adminCelNewsVO = new AdminCelloNewsVO();
				adminCelNewsVO.setNewsSeqNo(vo.getListNewsSeq()[i]);
				
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
							adminCelNewsVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/cellonews/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/cellonews/list";
					}
				}
				adminCelloNewsServiceImpl.doSortOrder(adminCelNewsVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/celloNews/list.do";
		}
	}
	
	
}
