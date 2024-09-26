package com.cellosquare.adminapp.admin.report;

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
import com.cellosquare.adminapp.admin.attachfile.service.AdminAttachFileService;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;
import com.cellosquare.adminapp.admin.report.service.AdminReportService;
import com.cellosquare.adminapp.admin.report.vo.AdminReportVO;
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
public class AdminReportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminReportService adminReportServiceImpl;

	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	
	@Autowired
	private AdminAttachFileService adminAttachFileServiceImpl;
	
	/**            
	 * 리포트 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/report/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) {

		logger.debug("AdminReportController :: list");
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		int totalCount = adminReportServiceImpl.getTotalCount(vo);
		List<AdminReportVO> list = new ArrayList<AdminReportVO>();

		if (totalCount > 0) {
			list = adminReportServiceImpl.getList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/basic/report/list";
	}
	
	/**
	 * 리포트 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		logger.debug("AdminReportController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("vo");
		session.removeAttribute("attachFileList");
		model.addAttribute("contIU", "I");
		return "admin/basic/report/registerForm";
	}
	
	/**
	 * 리포트 등록하기
	 * @param request
	 * @param resonse
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/register.do")
	public String doWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
			, Model model, @ModelAttribute("vo") AdminReportVO vo) {
		logger.debug("AdminReportController :: doWrite");

		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			vo.setLangCd(sessionForm.getLangCd());
			
			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));

			// 메타 SEO 저장
			if (adminSeoServiceImpl.doSeoWrite(request, response, muServletRequest, vo) > 0) {
				// 컨텐츠 저장
				
				if(!vo.getPcListImgUpload().isEmpty()) {
					if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListImgUpload")) {
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				         
				        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListImgUpload");
				         
				        vo.setPcListImgPath(fileVO.getFilePath());
				        vo.setPcListImgFileNm(fileVO.getFileTempName());
				        vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
				        vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
				    } else {
						throw new Exception();
					}
				}
				if(!vo.getPcDetlImgUpload().isEmpty()) {
				    if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetlImgUpload")) {
				    	String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				         
				        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetlImgUpload");
				         
				        vo.setPcDetlImgPath(fileVO.getFilePath());
				        vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				        vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				        vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
				    } else {
						throw new Exception();
					}
				}
				if(!vo.getMobileListImgUpload().isEmpty()) {
				    if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListImgUpload")) {
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				         
				        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListImgUpload");
				         
				        vo.setMobileListImgPath(fileVO.getFilePath());
				        vo.setMobileListImgFileNm(fileVO.getFileTempName());
				        vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				        vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
				    } else {
						throw new Exception();
					}
				}
				if(!vo.getMobileDetlImgUpload().isEmpty()) {
				    if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetlImgUpload")) {
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				         
				        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetlImgUpload");
				         
				        vo.setMobileDetlImgPath(fileVO.getFilePath());
				        vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				        vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				        vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
				    } else {
						throw new Exception();
					}
				}
				
				// 첨부파일 업로드
				if (FileUploadManager.isUploadFileValids(muServletRequest, response, true, "fileUpload")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
					List<FileUploadVO> fileList = FileUploadManager.uploadFiles(muServletRequest, path, "fileUpload");
					vo.setFileList(fileList);
				} else {
					throw new Exception();
				}

				adminReportServiceImpl.doWrite(vo);

			} else {
				// 강제 Exception

				throw new Exception();
			}

			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/report/list.do";
		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
				null, null, null, true);
			return "admin/basic/report/registerForm";
		}

	}

	/**
	 * 리포트 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) {
		logger.debug("AdminReportController :: detail");

		AdminReportVO detailVO = adminReportServiceImpl.getDetail(vo);
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		
		//xss 원복
		detailVO.setDetlInfo(XssPreventer.unescape(detailVO.getDetlInfo()));
		
		// 첨부파일 목록 구하기
		AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
		adminAttachFileVO.setContentsCcd("RPT");
		adminAttachFileVO.setContentsSeqNo(detailVO.getRptSeqNo());
		List<AdminAttachFileVO> attachFileList = adminAttachFileServiceImpl.getAttachFileList(adminAttachFileVO);

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("attachFileList", attachFileList);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/report/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("attachFileList", session.getAttribute("attachFileList"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/report/detail";
	}

	/**
	 * 리포트 수정하기 폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) {
		logger.debug("AdminReportController :: updateForm");

		AdminReportVO detailVO = adminReportServiceImpl.getDetail(vo);
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		// 첨부파일 목록 구하기
		AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
		adminAttachFileVO.setContentsCcd("RPT");
		adminAttachFileVO.setContentsSeqNo(detailVO.getRptSeqNo());
		
		List<AdminAttachFileVO> attachFileList = adminAttachFileServiceImpl.getAttachFileList(adminAttachFileVO);

		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("contIU", "U");
		
		return "admin/basic/report/registerForm";
	}

	/**
	 * 리포트 수정하기
	 * @param request
	 * @param response
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, Model model, 
			@ModelAttribute("vo") AdminReportVO vo) {
		
		logger.debug("AdminReportController :: doUpdate");

		
		
		try {
			
			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());

			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));

				
				if (adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {
					
					String pcListfileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
					String pcDetailfileDel = StringUtil.nvl(vo.getPcDetlFileDel(), "N");
					String mobileListfileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
					String mobileDetailfileDel = StringUtil.nvl(vo.getMobileDetlFileDel(), "N");
					
					AdminReportVO adminWhppVO = adminReportServiceImpl.getDetail(vo);
					
					// pcList 첨부파일을 선택 했을시 
				    if(!vo.getPcListImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListImgUpload");
				            
				            vo.setPcListImgPath(fileVO.getFilePath());
				            vo.setPcListImgFileNm(fileVO.getFileTempName());
				            vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
				            vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
				         } else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(pcListfileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setPcListImgPath("");
				            vo.setPcListImgFileNm("");
				            vo.setPcListImgSize("0");
				            vo.setPcListImgOrgFileNm("");
				            vo.setPcListImgAlt("");
				         } else {
				            // 디비에서 기존 가져오기 
				            vo.setPcListImgPath(adminWhppVO.getPcListImgPath());
				            vo.setPcListImgFileNm(adminWhppVO.getPcListImgFileNm());
				            vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminWhppVO.getPcListImgSize(), "0")));
				            vo.setPcListImgOrgFileNm(adminWhppVO.getPcListImgOrgFileNm());
				         }
				      }
				      // pcDetail 첨부파일을 선택 했을시
				      if(!vo.getPcDetlImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetlImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetlImgUpload");
				            
				            vo.setPcDetlImgPath(fileVO.getFilePath());
				            vo.setPcDetlImgFileNm(fileVO.getFileTempName());
				            vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
				            vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
				         } else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(pcDetailfileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setPcDetlImgPath("");
				            vo.setPcDetlImgFileNm("");
				            vo.setPcDetlImgSize("0");
				            vo.setPcDetlImgOrgFileNm("");
				            vo.setPcDetlImgAlt("");
				         } else {
				            vo.setPcDetlImgPath(adminWhppVO.getPcDetlImgPath());
				            vo.setPcDetlImgFileNm(adminWhppVO.getPcDetlImgFileNm());
				            vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(adminWhppVO.getPcDetlImgSize(),"0")));
				            vo.setPcDetlImgOrgFileNm(adminWhppVO.getPcDetlImgOrgFileNm());
				         }
				      }
				      // mobileList 첨부파일을 선택 했을시
				      if(!vo.getMobileListImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListImgUpload");
				            
				            vo.setMobileListImgPath(fileVO.getFilePath());
				            vo.setMobileListImgFileNm(fileVO.getFileTempName());
				            vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
				            vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
				         } else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(mobileListfileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setMobileListImgPath("");
				            vo.setMobileListImgFileNm("");
				            vo.setMobileListImgSize("0");
				            vo.setMobileListImgOrgFileNm("");
				            vo.setMobileListImgAlt("");
				         } else {
				            vo.setMobileListImgPath(adminWhppVO.getMobileListImgPath());
				            vo.setMobileListImgFileNm(adminWhppVO.getMobileListImgFileNm());
				            vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminWhppVO.getMobileListImgSize(), "0")));
				            vo.setMobileListImgOrgFileNm(adminWhppVO.getMobileListImgOrgFileNm());
				         }
				      }
				      // mobileDetail 첨부파일을 선택 했을시
				      if(!vo.getMobileDetlImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetlImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetlImgUpload");
				            
				            vo.setMobileDetlImgPath(fileVO.getFilePath());
				            vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				            vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				            vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
				         } else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(mobileDetailfileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setMobileDetlImgPath("");
				            vo.setMobileDetlImgFileNm("");
				            vo.setMobileDetlImgSize("0");
				            vo.setMobileDetlImgOrgFileNm("");
				            vo.setMobileDetlImgAlt("");
				         } else {
				        	vo.setMobileDetlImgPath(adminWhppVO.getMobileDetlImgPath());
				            vo.setMobileDetlImgFileNm(adminWhppVO.getMobileDetlImgFileNm());
				            vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(adminWhppVO.getMobileDetlImgSize(), "0")));
				            vo.setMobileDetlImgOrgFileNm(adminWhppVO.getMobileDetlImgOrgFileNm());
				         }
				      }

				    // 첨부파일 업로드
					if (FileUploadManager.isUploadFileValids(muServletRequest, response, true, "fileUpload")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathreport")));
						List<FileUploadVO> fileList = FileUploadManager.uploadFiles(muServletRequest, path, "fileUpload");
						vo.setFileList(fileList);
					} else {
						throw new Exception();
					}
					
					adminReportServiceImpl.doUpdate(vo);
				
			} else {
				// 강제 Exception
				throw new Exception();
			}

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("rptSeqNo", vo.getRptSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),
					null, null, null, true);
			return "admin/basic/report/registerForm";
		}
	}

	/** 
	 * 리포트 삭제하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/report/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) {
		logger.debug("AdminReportController :: delete");
		try {
			adminReportServiceImpl.doDelete(vo);

			// seo정보 삭제
			adminSeoServiceImpl.doSeoDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("rptSeqNo", vo.getRptSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"),
					null, null, null, true);
			return "admin/basic/report/detail";
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
    @GetMapping("/celloSquareAdmin/reportImgView.do")
    public String reportImgView(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute("vo") AdminReportVO vo) {

    	logger.debug("AdminReportController :: ReportImgView");

    	AdminReportVO adminWhppVO = adminReportServiceImpl.getDetail(vo);
    	
    	try {
    		if(adminWhppVO != null) {
    			FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
    			if(vo.getImgKinds().equals("pcList")) {
    				SafeFile pcListFile = new SafeFile(adminWhppVO.getPcListImgPath() , FilenameUtils.getName(adminWhppVO.getPcListImgFileNm()));
    				if(pcListFile.isFile()) {
    					fileDownLoadManager.fileFlush(pcListFile, adminWhppVO.getPcListImgOrgFileNm());
    				}
    			} else if (vo.getImgKinds().equals("pcDetail")) {
    				SafeFile pcDetailFile = new SafeFile(adminWhppVO.getPcDetlImgPath() , FilenameUtils.getName(adminWhppVO.getPcDetlImgFileNm()));
    				if(pcDetailFile.isFile()) {
    					fileDownLoadManager.fileFlush(pcDetailFile, adminWhppVO.getPcDetlImgOrgFileNm());
    				}
    			} else if (vo.getImgKinds().equals("mobileList")) {
    				SafeFile mobileListfile = new SafeFile(adminWhppVO.getMobileListImgPath() , FilenameUtils.getName(adminWhppVO.getMobileListImgFileNm()));
    				if(mobileListfile.isFile()) {
    					fileDownLoadManager.fileFlush(mobileListfile, adminWhppVO.getMobileListImgOrgFileNm());
    				}
    			} else if (vo.getImgKinds().equals("mobileDetail")) {
    				SafeFile mobileDetailfile = new SafeFile(adminWhppVO.getMobileDetlImgPath() , FilenameUtils.getName(adminWhppVO.getMobileDetlImgFileNm()));
    				if(mobileDetailfile.isFile()) {
    					fileDownLoadManager.fileFlush(mobileDetailfile, adminWhppVO.getMobileDetlImgOrgFileNm());
    				}
    			}
    		
    		}
		} catch (Exception e) {
		}
    	
        return null;
    }
	
    /**
     * 이미지 다운로드
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/celloSquareAdmin/reportImgDown.do")
	public void reportImgDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) throws IOException {

    	logger.debug("AdminReportController :: ReportImgDown");
    	
    	AdminReportVO adminWhppVO = adminReportServiceImpl.getDetail(vo);

    	String fNm = null;
		String filePath = null;
		
		if(vo.getImgKinds().equals("pcList")) {
			fNm = adminWhppVO.getPcListImgOrgFileNm();
			filePath = adminWhppVO.getPcListImgPath() + "/" + FilenameUtils.getName(adminWhppVO.getPcListImgFileNm());
		} else if (vo.getImgKinds().equals("pcDetail")) {
			fNm = adminWhppVO.getPcDetlImgOrgFileNm();
			filePath = adminWhppVO.getPcDetlImgPath() + "/" + FilenameUtils.getName(adminWhppVO.getPcDetlImgFileNm());
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = adminWhppVO.getMobileListImgOrgFileNm();
			filePath = adminWhppVO.getMobileListImgPath() + "/" + FilenameUtils.getName(adminWhppVO.getMobileListImgFileNm());
		} else if (vo.getImgKinds().equals("mobileDetail")) {
	    	fNm = adminWhppVO.getMobileDetlImgOrgFileNm();
	    	filePath = adminWhppVO.getMobileDetlImgPath() + "/" + FilenameUtils.getName(adminWhppVO.getMobileDetlImgFileNm());
	    }
    	
		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminWhppVO != null) {
				
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
    
    /**
	 * 정렬순서 저장
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
    @PostMapping("/celloSquareAdmin/report/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminReportVO vo) {
		logger.debug("AdminReportVOController :: doSortOrder");
		try {
			AdminReportVO adminReportVO = null;
			for(int i = 0; i < vo.getListRptSeq().length; i++) {
				adminReportVO = new AdminReportVO();
				adminReportVO.setRptSeqNo(vo.getListRptSeq()[i]);
				
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
							adminReportVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/report/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/report/list";
					}
				}
				
				adminReportServiceImpl.doSortOrder(adminReportVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/report/list.do";
		}
	}
	
}