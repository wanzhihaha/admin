package com.cellosquare.adminapp.admin.blog;

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
import com.cellosquare.adminapp.admin.blog.service.AdminBlogService;
import com.cellosquare.adminapp.admin.blog.vo.AdminBlogVO;
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
public class AdminBlogController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminBlogService adminBlogServiceImpl;
	
	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	
	/**
	 * 블로그 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/blog/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {

		logger.debug("AdminBlogController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		int totalCount = adminBlogServiceImpl.getTotalCount(vo);
		List<AdminBlogVO> list = new ArrayList<AdminBlogVO>();

		if (totalCount > 0) {
			list = adminBlogServiceImpl.getList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/basic/blog/list";
	}

	/**
	 * 블로그 등록폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/blog/registerForm";
	}

	/**
	 * 블로그 등록하기
	 * @param request
	 * @param resonse
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/register.do")
	public String doWrite(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, 
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: doWrite");

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
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
				         
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
				    	String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
				         
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
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
				         
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
				        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
				         
				        FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetlImgUpload");
				         
				        vo.setMobileDetlImgPath(fileVO.getFilePath());
				        vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
				        vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
				        vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
				    } else {
						throw new Exception();
					}
				}
				adminBlogServiceImpl.doWrite(vo);
			} else {
				throw new Exception();
			}

		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/blog/registerForm";
		}
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
		return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/blog/list.do";

	}

	/**
	 * 블로그 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: detail");

		AdminBlogVO detailVO = adminBlogServiceImpl.getDetail(vo);
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		//xss 원복
		detailVO.setDetlInfo(XssPreventer.unescape(detailVO.getDetlInfo()));
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/blog/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/blog/detail";
	}

	/**
	 * 블로그 수정하기 폼
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: updateForm");

		AdminBlogVO detailVO = adminBlogServiceImpl.getDetail(vo);
		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("contIU", "U");
		return "admin/basic/blog/registerForm";
	}

	/**
	 * 블로그 수정하기
	 * @param request
	 * @param response
	 * @param muServletRequest
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, Model model, 
			@ModelAttribute("vo") AdminBlogVO vo) {
		
		logger.debug("AdminBlogController :: doUpdate");
		
		try {
			
			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());

			vo.setDetlInfo(XssUtils.cleanXssNamoContent(vo.getDetlInfo()));
				
			if (adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {
				
				String pcListfileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
			    String pcDetailfileDel = StringUtil.nvl(vo.getPcDetlFileDel(), "N");
			    String mobileListfileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
			    String mobileDetailfileDel = StringUtil.nvl(vo.getMobileDetlFileDel(), "N");
				
			    AdminBlogVO adminBlogVO = adminBlogServiceImpl.getDetail(vo);
			  	      
				// pcList 첨부파일을 선택 했을시 
			    if(!vo.getPcListImgUpload().isEmpty()) {
			         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListImgUpload")) {
			            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
			            
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
			            vo.setPcListImgPath(adminBlogVO.getPcListImgPath());
			            vo.setPcListImgFileNm(adminBlogVO.getPcListImgFileNm());
			            vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminBlogVO.getPcListImgSize(), "0")));
			            vo.setPcListImgOrgFileNm(adminBlogVO.getPcListImgOrgFileNm());
			         }
			      }
			      // pcDetail 첨부파일을 선택 했을시
			      if(!vo.getPcDetlImgUpload().isEmpty()) {
			         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetlImgUpload")) {
			            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
			            
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
			            vo.setPcDetlImgPath(adminBlogVO.getPcDetlImgPath());
			            vo.setPcDetlImgFileNm(adminBlogVO.getPcDetlImgFileNm());
			            vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(adminBlogVO.getPcDetlImgSize(),"0")));
			            vo.setPcDetlImgOrgFileNm(adminBlogVO.getPcDetlImgOrgFileNm());
			         }
			      }
			      // mobileList 첨부파일을 선택 했을시
			      if(!vo.getMobileListImgUpload().isEmpty()) {
			         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListImgUpload")) {
			            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
			            
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
			            vo.setMobileListImgPath(adminBlogVO.getMobileListImgPath());
			            vo.setMobileListImgFileNm(adminBlogVO.getMobileListImgFileNm());
			            vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminBlogVO.getMobileListImgSize(), "0")));
			            vo.setMobileListImgOrgFileNm(adminBlogVO.getMobileListImgOrgFileNm());
			         }
			      }
			      // mobileDetail 첨부파일을 선택 했을시
			      if(!vo.getMobileDetlImgUpload().isEmpty()) {
			         if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetlImgUpload")) {
			            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBlog")));
			            
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
			        	vo.setMobileDetlImgPath(adminBlogVO.getMobileDetlImgPath());
			            vo.setMobileDetlImgFileNm(adminBlogVO.getMobileDetlImgFileNm());
			            vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(adminBlogVO.getMobileDetlImgSize(), "0")));
			            vo.setMobileDetlImgOrgFileNm(adminBlogVO.getMobileDetlImgOrgFileNm());
			         }
			      }
					
			      adminBlogServiceImpl.doUpdate(vo);
				
			} else {
				// 강제 Exception
				throw new Exception();
			}

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("blogSeqNo", vo.getBlogSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/blog/registerForm";
		}
	}

	/** 
	 * 블로그 삭제하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/blog/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: delete");
		try {
			adminBlogServiceImpl.doDelete(vo);

			// seo정보 삭제
			adminSeoServiceImpl.doSeoDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("blogSeqNo", vo.getBlogSeqNo());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/blog/detail";
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
    @GetMapping("/celloSquareAdmin/blogImgView.do")
    public String blogImgView(HttpServletRequest request, HttpServletResponse response, Model model,
    		@ModelAttribute("vo") AdminBlogVO vo) {

    	logger.debug("AdminBlogController :: blogImgView");
    	
    	AdminBlogVO adminBlogVO = adminBlogServiceImpl.getDetail(vo);
    	
    	try {
            if(adminBlogVO != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if(vo.getImgKinds().equals("pcList")) {
                   SafeFile pcListFile = new SafeFile(adminBlogVO.getPcListImgPath() , FilenameUtils.getName(adminBlogVO.getPcListImgFileNm()));
                   if (pcListFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcListFile, adminBlogVO.getPcListImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("pcDetail")) {
                	SafeFile pcDetailFile = new SafeFile(adminBlogVO.getPcDetlImgPath() , FilenameUtils.getName(adminBlogVO.getPcDetlImgFileNm()));
                   if (pcDetailFile.isFile()) {
                	   fileDownLoadManager.fileFlush(pcDetailFile, adminBlogVO.getPcDetlImgOrgFileNm());
                   }
                } else if (vo.getImgKinds().equals("mobileList")) {
                	SafeFile mobileListfile = new SafeFile(adminBlogVO.getMobileListImgPath() , FilenameUtils.getName(adminBlogVO.getMobileListImgFileNm()));
                   if (mobileListfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileListfile, adminBlogVO.getMobileListImgOrgFileNm());						
                   }
                } else if (vo.getImgKinds().equals("mobileDetail")) {
                	SafeFile mobileDetailfile = new SafeFile(adminBlogVO.getMobileDetlImgPath() , FilenameUtils.getName(adminBlogVO.getMobileDetlImgFileNm()));
                   if (mobileDetailfile.isFile()) {
                	   fileDownLoadManager.fileFlush(mobileDetailfile, adminBlogVO.getMobileDetlImgOrgFileNm());						
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
    @GetMapping("/celloSquareAdmin/blogImgpDown.do")
	public void blogImgpDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) throws IOException {

    	logger.debug("AdminBlogController :: blogImgpDown");
    	
    	AdminBlogVO adminBlogVO = adminBlogServiceImpl.getDetail(vo);

    	String fNm = null;
    	String filePath = null;
    	
        if(vo.getImgKinds().equals("pcList")) {
            fNm = adminBlogVO.getPcListImgOrgFileNm();
            filePath = adminBlogVO.getPcListImgPath() + "/" + adminBlogVO.getPcListImgFileNm();
            System.out.println("filePath : " + filePath);
         } else if (vo.getImgKinds().equals("pcDetail")) {
            fNm = adminBlogVO.getPcDetlImgOrgFileNm();
            filePath = adminBlogVO.getPcDetlImgPath() + "/" + adminBlogVO.getPcDetlImgFileNm();
            System.out.println("filePath : " + filePath);
         } else if (vo.getImgKinds().equals("mobileList")) {
            fNm = adminBlogVO.getMobileListImgOrgFileNm();
            filePath = adminBlogVO.getMobileListImgPath() + "/" + adminBlogVO.getMobileListImgFileNm();
            System.out.println("filePath : " + filePath);
         } else if (vo.getImgKinds().equals("mobileDetail")) {
             fNm = adminBlogVO.getMobileDetlImgOrgFileNm();
             filePath = adminBlogVO.getMobileDetlImgPath() + "/" + adminBlogVO.getMobileDetlImgFileNm();
             System.out.println("filePath : " + filePath);
          }

    	OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminBlogVO != null) {
				
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
    
	/**
	 * 정렬순서 저장
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
    @PostMapping("/celloSquareAdmin/blog/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminBlogVO vo) {
		logger.debug("AdminBlogController :: doSortOrder");
		try {
			AdminBlogVO adminBlogVO = null;
			for(int i = 0; i < vo.getListblogSeq().length; i++) {
				adminBlogVO = new AdminBlogVO();
				adminBlogVO.setBlogSeqNo(vo.getListblogSeq()[i]);
				
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
							adminBlogVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/blog/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/blog/list";
					}
				}
				
				adminBlogServiceImpl.doSortOrder(adminBlogVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/blog/list.do";
		}
	}
	    
}
