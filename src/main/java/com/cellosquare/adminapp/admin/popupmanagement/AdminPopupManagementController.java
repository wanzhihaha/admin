package com.cellosquare.adminapp.admin.popupmanagement;

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

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.popupmanagement.service.AdminPopupManagementService;
import com.cellosquare.adminapp.admin.popupmanagement.vo.AdminPopupManagementVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;

@Controller
public class AdminPopupManagementController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminPopupManagementService adminPopupManagementServiceImpl;
	
	/**
	 * 팝업 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
		@GetMapping("/celloSquareAdmin/popup/list.do")
		public String list(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) {

			logger.debug("AdminPopupManagementController :: list");
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setLangCd(sessionForm.getLangCd());

			int totalCount = adminPopupManagementServiceImpl.getTotalCount(vo);
			List<AdminPopupManagementVO> list = new ArrayList<AdminPopupManagementVO>();

			if (totalCount > 0) {
				list = adminPopupManagementServiceImpl.getList(vo);
				// DB에서 TimeStamp형태로 들어와서 값이없을경우 0001-01-01로 값을 받기때문에 다시 없얘주는 작업
				// 다른방법은 쿼리문에서 toChar에서 TimeStamp를 없얘고 String에서 날짜 사이에 -값을 추가해주는 로직을 짜야됨 
				for (int i = 0; i < list.size(); i++) {
					String stDate = StringUtil.nvl(list.get(i).getPeridStatDate(), "");
					if(stDate.equals("0001-01-01")) {
						list.get(i).setPeridStatDate(""); 
					}
					String endDate = StringUtil.nvl(list.get(i).getPeridEndDate(), "");
					if(endDate.equals("0001-01-01")) {
						list.get(i).setPeridEndDate(""); 
					}
				}
			}

			model.addAttribute("totalCount", totalCount);
			model.addAttribute("list", list);

			return "admin/basic/popup/list";
		}
		
		/**
		 * 팝업 등록폼
		 * @param request
		 * @param response
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/registerForm.do")
		public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) {
			logger.debug("AdminPopupManagementController :: registerForm");
			HttpSession session = request.getSession();
			session.removeAttribute("detail");
			session.removeAttribute("vo");
			model.addAttribute("contIU", "I");
			return "admin/basic/popup/registerForm";
		}
	
		/**
		 * 팝업 등록하기
		 * @param request
		 * @param resonse
		 * @param muServletRequest
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/register.do")
		public String doWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
				, Model model, @ModelAttribute("vo") AdminPopupManagementVO vo) {
			logger.debug("AdminPopupManagementController :: doWrite");

			try {
				//-값 제거
				String startDate = vo.getPeridStatDate().replaceAll("-", "");
				String endDate = vo.getPeridEndDate().replaceAll("-", "");
				
				vo.setPeridStatDate(startDate);
				vo.setPeridEndDate(endDate);
				
				AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
				vo.setInsPersonId(sessionForm.getAdminId());
				vo.setUpdPersonId(sessionForm.getAdminId());
				vo.setLangCd(sessionForm.getLangCd());

					// 컨텐츠 저장
					if(!vo.getPcPopImgUpload().isEmpty()) {
						if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcPopImgUpload")) {
					        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathPopup")));
					         
					        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcPopImgUpload");
					         
					        vo.setPcPopImgPath(fileVO.getFilePath());
					        vo.setPcPopImgFileNm(fileVO.getFileTempName());
					        vo.setPcPopImgSize(String.valueOf(fileVO.getFileSize()));
					        vo.setPcPopImgOrgFileNm(fileVO.getFileOriginName());
					    }else {
							throw new Exception();
						}
					}
					if(!vo.getMobilePopImgUpload().isEmpty()) {
					    if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobilePopImgUpload")) {
					    	String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathPopup")));
					         
					        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobilePopImgUpload");
					         
					        vo.setMobilePopImgPath(fileVO.getFilePath());
					        vo.setMobilePopImgFileNm(fileVO.getFileTempName());
					        vo.setMobilePopImgSize(String.valueOf(fileVO.getFileSize()));
					        vo.setMobilePopImgOrgFileNm(fileVO.getFileOriginName());
					    }else {
							throw new Exception();
						}
					}
					adminPopupManagementServiceImpl.doWrite(vo);
					
				ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
				return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/popup/list.do";
			}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
					null, null, null, true);
			return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/popup/registerForm.do";
			}
		}
		
		/**
		 * 팝업 상세보기
		 * @param request
		 * @param response
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/detail.do")
		public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) {
			logger.debug("AdminPopupManagementController :: detail");

			AdminPopupManagementVO detailVO = adminPopupManagementServiceImpl.getDetail(vo);

			String stDate = StringUtil.nvl(detailVO.getPeridStatDate(), "");
			if(stDate.equals("0001-01-01")) {
				detailVO.setPeridStatDate(""); 
			}
			String endDate = StringUtil.nvl(detailVO.getPeridEndDate(), "");
			if(endDate.equals("0001-01-01")) {
				detailVO.setPeridEndDate(""); 
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("detail", detailVO);
			session.setAttribute("vo", vo);
			return "redirect:detail.do";
		}

	@GetMapping("/celloSquareAdmin/popup/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/popup/detail";
	}

		/**
		 * 팝업 수정하기 폼
		 * @param request
		 * @param response
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/updateForm.do")
		public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) {
			logger.debug("AdminPopupManagementController :: updateForm");

			AdminPopupManagementVO detailVO = adminPopupManagementServiceImpl.getDetail(vo);
			
			String stDate = StringUtil.nvl(detailVO.getPeridStatDate(), "");
			if(stDate.equals("0001-01-01")) {
				detailVO.setPeridStatDate(""); 
			}
			String endDate = StringUtil.nvl(detailVO.getPeridEndDate(), "");
			if(endDate.equals("0001-01-01")) {
				detailVO.setPeridEndDate(""); 
			}
			
			model.addAttribute("detail", detailVO);
			model.addAttribute("contIU", "U");
			return "admin/basic/popup/registerForm";
		}
	
		/**
		 * 팝업 수정하기
		 * @param request
		 * @param response
		 * @param muServletRequest
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/update.do")
		public String doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, 
				Model model, @ModelAttribute("vo") AdminPopupManagementVO vo) {
			
			logger.debug("AdminPopupManagementController :: doUpdate");
			
			try {
				
				//-값 제거
				String startDate = vo.getPeridStatDate().replaceAll("-", "");
				String endDate = vo.getPeridEndDate().replaceAll("-", "");
				
				vo.setPeridStatDate(startDate);
				vo.setPeridEndDate(endDate);
				
				AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
				vo.setUpdPersonId(adminSessionForm.getAdminId());
					
					String pcPopFileDel = StringUtil.nvl(vo.getPcPopFileDel(), "N");
				    String mobilePopFileDel = StringUtil.nvl(vo.getMobilePopFileDel(), "N");
					
				    AdminPopupManagementVO adminPopupManagementVO = adminPopupManagementServiceImpl.getDetail(vo);
				  	      
					// pcList 첨부파일을 선택 했을시 
				    if(!vo.getPcPopImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcPopImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathPopup")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcPopImgUpload");
				            
					        vo.setPcPopImgPath(fileVO.getFilePath());
					        vo.setPcPopImgFileNm(fileVO.getFileTempName());
					        vo.setPcPopImgSize(String.valueOf(fileVO.getFileSize()));
					        vo.setPcPopImgOrgFileNm(fileVO.getFileOriginName());
				         }else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(pcPopFileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setPcPopImgPath("");
				            vo.setPcPopImgFileNm("");
				            vo.setPcPopImgSize("0");
				            vo.setPcPopImgOrgFileNm("");
				            vo.setPcPopImgAlt("");
				         } else {
				            // 디비에서 기존 가져오기 
				            vo.setPcPopImgPath(adminPopupManagementVO.getPcPopImgPath());
				            vo.setPcPopImgFileNm(adminPopupManagementVO.getPcPopImgFileNm());
				            vo.setPcPopImgSize(String.valueOf(StringUtil.nvl(adminPopupManagementVO.getPcPopImgSize(), "0")));
				            vo.setPcPopImgOrgFileNm(adminPopupManagementVO.getPcPopImgOrgFileNm());
				         }
				      }
				      // pcDetail 첨부파일을 선택 했을시
				      if(!vo.getMobilePopImgUpload().isEmpty()) {
				         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobilePopImgUpload")) {
				            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathPopup")));
				            
				            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobilePopImgUpload");
				            
					        vo.setMobilePopImgPath(fileVO.getFilePath());
					        vo.setMobilePopImgFileNm(fileVO.getFileTempName());
					        vo.setMobilePopImgSize(String.valueOf(fileVO.getFileSize()));
					        vo.setMobilePopImgOrgFileNm(fileVO.getFileOriginName());
				         }else {
								throw new Exception();
							}
				      } else {   // 파일을 선택안했을시
				         if(mobilePopFileDel.equals("Y")) {// 삭제를 체크했을시
				            vo.setMobilePopImgPath("");
				            vo.setMobilePopImgFileNm("");
				            vo.setMobilePopImgSize("0");
				            vo.setMobilePopImgOrgFileNm("");
				            vo.setMobilePopImgAlt("");
				         } else {
				            vo.setMobilePopImgPath(adminPopupManagementVO.getMobilePopImgPath());
				            vo.setMobilePopImgFileNm(adminPopupManagementVO.getMobilePopImgFileNm());
				            vo.setMobilePopImgSize(String.valueOf(StringUtil.nvl(adminPopupManagementVO.getMobilePopImgSize(),"0")));
				            vo.setMobilePopImgOrgFileNm(adminPopupManagementVO.getMobilePopImgOrgFileNm());
				         }
				      }
						
				      adminPopupManagementServiceImpl.doUpdate(vo);
					

				Map<String, String> hmParam = new HashMap<String, String>();
				hmParam.put("popSeqNo", vo.getPopSeqNo());
				model.addAttribute("msg_type", ":submit");
				model.addAttribute("method", "post");
				model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
				model.addAttribute("url", "./detail.do");
				model.addAttribute("hmParam", hmParam);

				return "admin/common/message";
			} catch (Exception e) {
				ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),
						null, null, null, true);
				return "admin/basic/popup/updateForm";
			}
		}
		
		/** 
		 * 팝업 삭제하기
		 * @param request
		 * @param response
		 * @param model
		 * @param vo
		 * @return
		 */
		@PostMapping("/celloSquareAdmin/popup/doDelete.do")
		public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) {
			logger.debug("AdminPopupManagementController :: delete");
			try {
				adminPopupManagementServiceImpl.doDelete(vo);

				Map<String, String> hmParam = new HashMap<String, String>();
				hmParam.put("popSeqNo", vo.getPopSeqNo());
				model.addAttribute("msg_type", ":submit");
				model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
				model.addAttribute("url", "./list.do");
				model.addAttribute("hmParam", hmParam);

				return "admin/common/message";

			} catch (Exception e) {
				ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
				return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/popup/detail.do";
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
	    @GetMapping("/celloSquareAdmin/popupImgView.do")
	    public String popupImgView(HttpServletRequest request, HttpServletResponse response, Model model,
	    		@ModelAttribute("vo") AdminPopupManagementVO vo) {

	    	logger.debug("AdminPopupManagementController :: popupImgView");
	    	
	    	AdminPopupManagementVO adminPopupManagementVO = adminPopupManagementServiceImpl.getDetail(vo);
	    	
	    	try {
	            if(adminPopupManagementVO != null) {
	                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
	                if(vo.getImgKinds().equals("pcPop")) {
						SafeFile pcPopFile = new SafeFile(adminPopupManagementVO.getPcPopImgPath() , FilenameUtils.getName(adminPopupManagementVO.getPcPopImgFileNm()));
						if (pcPopFile.isFile()) {
							fileDownLoadManager.fileFlush(pcPopFile, adminPopupManagementVO.getPcPopImgOrgFileNm());
						}
	                } else if (vo.getImgKinds().equals("mobilePop")) {
						SafeFile mobilePopFile = new SafeFile(adminPopupManagementVO.getMobilePopImgPath() , FilenameUtils.getName(adminPopupManagementVO.getMobilePopImgFileNm()));
	                   	if (mobilePopFile.isFile()) {
	                   		fileDownLoadManager.fileFlush(mobilePopFile, adminPopupManagementVO.getMobilePopImgOrgFileNm());
	                   	}
	                } 
	             }
			} catch (Exception e) {
			}
	    	
	        return null;
	    }
	    
		/**
	     * 팝업 이미지
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param vo
	     * @return
	     */
	    @GetMapping("/celloSquareAdmin/popup/popupPreView.do")
	    public String popupPreView(HttpServletRequest request, HttpServletResponse response, Model model,
	    		@ModelAttribute("vo") AdminPopupManagementVO vo) {

	    	logger.debug("AdminPopupManagementController :: popupPreView");
	    	
	    	return "admin/popup/popup/popupPreView";
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
	    @GetMapping("/celloSquareAdmin/popupImgDown.do")
		public void popupImgDown(HttpServletRequest request, HttpServletResponse response, Model model,
				@ModelAttribute("vo") AdminPopupManagementVO vo) throws IOException {

	    	logger.debug("AdminPopupManagementController :: popupImgDown");
	    	
	    	AdminPopupManagementVO adminPopupManagementVO = adminPopupManagementServiceImpl.getDetail(vo);

	    	String fNm = null;
	    	String filePath = null;
	    	
	        if(vo.getImgKinds().equals("pcPop")) {
	            fNm = adminPopupManagementVO.getPcPopImgOrgFileNm();
	            filePath = adminPopupManagementVO.getPcPopImgPath() + "/" + FilenameUtils.getName(adminPopupManagementVO.getPcPopImgFileNm());
	            System.out.println("filePath : " + filePath);
	         } else if (vo.getImgKinds().equals("mobilePop")) {
	            fNm = adminPopupManagementVO.getMobilePopImgOrgFileNm();
	            filePath = adminPopupManagementVO.getMobilePopImgPath() + "/" + FilenameUtils.getName(adminPopupManagementVO.getMobilePopImgFileNm());
	            System.out.println("filePath : " + filePath);
	         } 

	    	OutputStream os = null;
			FileInputStream fis = null;

			try {
				if(adminPopupManagementVO != null) {
					
					SafeFile file = new SafeFile(filePath);

					response.setContentType("application/octet-stream; charset=utf-8");
					response.setContentLength((int) file.length());
								
					String browser = FileDownUtil.getBrowser(request);
					String pcListdisposition = FileDownUtil.getDisposition(fNm, browser);

					//fileName으로 다운받아라 라는뜻  attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지
					response.setHeader("Content-Disposition", pcListdisposition);
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
}
