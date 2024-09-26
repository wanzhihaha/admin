package com.cellosquare.adminapp.admin.goods;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.attachfile.service.AdminAttachFileService;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.goods.service.AdminGoodsService;
import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AdminGoodsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminGoodsService adminGoodsServiceImpl;

	@Autowired
	private ApiCodeService apiCodeServiceImpl;

	@Autowired
	private AdminSeoService adminSeoServiceImpl;

	@Autowired
	private AdminAttachFileService adminAttachFileServiceImpl;

	/**
	 * 상품 리스트
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */

	@GetMapping("/celloSquareAdmin/goods/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo) {

		logger.debug("AdminGoodsController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		ApiCodeVO apiCodeVO = new ApiCodeVO();
		// 카테고리 값이 없을경우 Expree로 맵핑
		apiCodeVO.setCd(StringUtil.nvl(vo.getSqprdCtgry(), "GOODS_DIV_01"));
		apiCodeVO.setGrpCd("GOODS_DIV");
		apiCodeVO.setLangCd(sessionForm.getLangCd());

		String categoryNm = apiCodeServiceImpl.getCategoryNm(apiCodeVO);
		vo.setSqprdCtgryNm(categoryNm);


		int totalCount = adminGoodsServiceImpl.getTotalCount(vo);
		List<AdminGoodsVO> list = new ArrayList<AdminGoodsVO>();

		if(totalCount > 0 ) {
			list = adminGoodsServiceImpl.getList(vo);

			for (int i = 0; i < list.size(); i++) {
				String stDate = StringUtil.nvl(list.get(i).getSvcValidStatDate(), "");
				if(stDate.equals("0001-01-01")) {
					list.get(i).setSvcValidStatDate(""); 
				}
				String endDate = StringUtil.nvl(list.get(i).getSvcValidEndDate(), "");
				if(endDate.equals("0001-01-01")) {
					list.get(i).setSvcValidEndDate(""); 
				}
			}
		}


		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/basic/goods/list";
	}


	/**
	 * 상품 등록폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/registerForm.do")
	public String registForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo)  {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("adminSeoVO");
		session.removeAttribute("attachFileList");
		session.removeAttribute("vo");
		logger.debug("AdminGoodsController :: registerForm");

		model.addAttribute("contIU", "I");
		return "admin/basic/goods/registerForm";
	}

	/**
	 * 상품 등록
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model, 
			@ModelAttribute("vo") AdminGoodsVO vo, MultipartHttpServletRequest muServletRequest)  {

		logger.debug("AdminGoodsController :: register");

		try {
			//날짜 -값 제거
			String startDate = vo.getSvcValidStatDate().replaceAll("-", "");
			String endDate = vo.getSvcValidEndDate().replaceAll("-", "");

			vo.setSvcValidStatDate(startDate);
			vo.setSvcValidEndDate(endDate);

			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());

			vo.setLangCd(sessionForm.getLangCd());

			vo.setSqprdNotiContents(XssUtils.cleanXssNamoContent(vo.getSqprdNotiContents()));
			vo.setSqprdDetlInfo(XssUtils.cleanXssNamoContent(vo.getSqprdDetlInfo()));

			// 메타 SEO 저장
			if(adminSeoServiceImpl.doSeoWrite(request, response, muServletRequest, vo) > 0) {

				// 대표이미지가 있을경우
				if(!vo.getPcListOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
						vo.setPcListImgPath(fileVO.getFilePath());
						vo.setPcListImgFileNm(fileVO.getFileTempName());
						vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				}

				if(!vo.getPcDetailOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");
						vo.setPcDetlImgPath(fileVO.getFilePath());
						vo.setPcDetlImgFileNm(fileVO.getFileTempName());
						vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				}

				if(!vo.getMobileListOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
						vo.setMobileListImgPath(fileVO.getFilePath());
						vo.setMobileListImgFileNm(fileVO.getFileTempName());
						vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				}

				if(!vo.getMobileDetailOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");
						vo.setMobileDetlImgPath(fileVO.getFilePath());
						vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
						vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				}
				//nullPointEception 발생해서 조건 하나더추가함 
				//오류가 나는이유는 다른건 다 인풋창에 정의가되어있는데 얘만 어펜드로 요소추가를 해주고있어서 없는경우가있음!
				if(vo.getViewTypeOrginFile() != null && !vo.getViewTypeOrginFile().isEmpty()) {	
					
					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "viewTypeOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "viewTypeOrginFile");
						vo.setViewTypeImgPath(fileVO.getFilePath());
						vo.setViewTypeImgFileNm(fileVO.getFileTempName());
						vo.setViewTypeImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setViewTypeImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				}

				// 첨부파일 업로드
				if (FileUploadManager.isUploadFileValids(muServletRequest, response, true, "fileUpload")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
					List<FileUploadVO> fileList =FileUploadManager.uploadFiles(muServletRequest, path, "fileUpload");
					vo.setFileList(fileList);
				} else {
					throw new Exception(); 
				}

				// 컨텐츠 저장
				adminGoodsServiceImpl.regist(vo);
			} else {
				// 강제 Exception  
				throw new Exception(); 
			}

			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/goods/list.do" + "?" + "sqprdCtgry=" + vo.getSqprdCtgry();
		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/goods/registerForm";
		}

	}

	/**
	 * 상품 수정폼
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo)  {

		logger.debug("AdminGoodsController :: updateForm");

		AdminGoodsVO detailVO = adminGoodsServiceImpl.getDetail(vo);
		String stDate = StringUtil.nvl(detailVO.getSvcValidStatDate(), "");
		if(stDate.equals("0001-01-01")) {
			detailVO.setSvcValidStatDate(""); 
		}
		String endDate = StringUtil.nvl(detailVO.getSvcValidEndDate(), "");
		if(endDate.equals("0001-01-01")) {
			detailVO.setSvcValidEndDate(""); 
		}
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
		AdminGoodsVO reltVO1 = null;
		AdminGoodsVO reltVO2 = null;
		AdminGoodsVO reltVO3 = null;
		try {
			//상품 시퀀스로 상품이름 받아오기
			if(!detailVO.getReltdSqprd1().trim().isEmpty() && detailVO.getReltdSqprd1() != null) {
				String reltdStr = detailVO.getReltdSqprd1();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO1 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO1 != null) {
					model.addAttribute("goodsNm1", reltVO1.getSqprdNm());
				}
			} 
			  
			if(!detailVO.getReltdSqprd2().trim().isEmpty() && detailVO.getReltdSqprd2() != null) {
				String reltdStr = detailVO.getReltdSqprd2();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO2 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO2 != null) {
					model.addAttribute("goodsNm2", reltVO2.getSqprdNm());
				}
			} 
			
			if(!detailVO.getReltdSqprd3().trim().isEmpty() && detailVO.getReltdSqprd3() != null) {
				String reltdStr = detailVO.getReltdSqprd3();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO3 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO3 != null) {
					model.addAttribute("goodsNm3", reltVO3.getSqprdNm());
				}
			} 
				
				
		} catch (Exception e) {
			//		e.printStackTrace();
		}


		//첨부파일 목록 구하기
		AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
		adminAttachFileVO.setContentsCcd("GOODS");
		adminAttachFileVO.setContentsSeqNo(detailVO.getSqprdSeqNo());

		List<AdminAttachFileVO> attachFileList = adminAttachFileServiceImpl.getAttachFileList(adminAttachFileVO);

		model.addAttribute("detail", detailVO);
		model.addAttribute("adminSeoVO", adminSeoVO);
		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("contIU", "U");
		return "admin/basic/goods/registerForm";
	}

	/**
	 * 상품 수정하기
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/update.do")
	public String doUpdate(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest muServletRequest, Model model, 
			@ModelAttribute("vo") AdminGoodsVO vo) {

		logger.debug("AdminGoodsController :: update");

		try {

			//날짜 -값 제거
			String startDate = vo.getSvcValidStatDate().replaceAll("-", "");
			String endDate = vo.getSvcValidEndDate().replaceAll("-", "");

			vo.setSvcValidStatDate(startDate);
			vo.setSvcValidEndDate(endDate);

			vo.setSqprdNotiContents(XssUtils.cleanXssNamoContent(vo.getSqprdNotiContents()));
			vo.setSqprdDetlInfo(XssUtils.cleanXssNamoContent(vo.getSqprdDetlInfo()));

			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());



			if (adminSeoServiceImpl.doSeoUpdate(request, response, muServletRequest, vo) > 0) {

				String pcListfileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
				String pcDetailfileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
				String mobileListfileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");
				String mobileDetailfileDel = StringUtil.nvl(vo.getMobileDetailFileDel(), "N");

				AdminGoodsVO adminGoodsVO = adminGoodsServiceImpl.getDetail(vo);

				// pcList 첨부파일을 선택 했을시 
				if(!vo.getPcListOrginFile().isEmpty()) {

					if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
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
						vo.setPcListImgPath(adminGoodsVO.getPcListImgPath());
						vo.setPcListImgFileNm(adminGoodsVO.getPcListImgFileNm());
						vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminGoodsVO.getPcListImgSize(), "0")));
						vo.setPcListImgOrgFileNm(adminGoodsVO.getPcListImgOrgFileNm());
					}
				}
				// pcDetail 첨부파일을 선택 했을시
				if(!vo.getPcDetailOrginFile().isEmpty()) {
					if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

						vo.setPcDetlImgPath(fileVO.getFilePath());
						vo.setPcDetlImgFileNm(fileVO.getFileTempName());
						vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				} else {   
					if(pcDetailfileDel.equals("Y")) {// 삭제를 체크했을시
						vo.setPcDetlImgPath("");
						vo.setPcDetlImgFileNm("");
						vo.setPcDetlImgSize("0");
						vo.setPcDetlImgOrgFileNm("");
						vo.setPcDetlImgAlt("");
					} else {
						vo.setPcDetlImgPath(adminGoodsVO.getPcDetlImgPath());
						vo.setPcDetlImgFileNm(adminGoodsVO.getPcDetlImgFileNm());
						vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(adminGoodsVO.getPcDetlImgSize(),"0")));
						vo.setPcDetlImgOrgFileNm(adminGoodsVO.getPcDetlImgOrgFileNm());
					}
				}
				// mobileList 첨부파일을 선택 했을시
				if(!vo.getMobileListOrginFile().isEmpty()) {
					if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

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
						vo.setMobileListImgPath(adminGoodsVO.getMobileListImgPath());
						vo.setMobileListImgFileNm(adminGoodsVO.getMobileListImgFileNm());
						vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminGoodsVO.getMobileListImgSize(), "0")));
						vo.setMobileListImgOrgFileNm(adminGoodsVO.getMobileListImgOrgFileNm());
					}
				}
				// mobileDetail 첨부파일을 선택 했을시
				if(!vo.getMobileDetailOrginFile().isEmpty()) {
					if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");

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
						vo.setMobileDetlImgPath(adminGoodsVO.getMobileDetlImgPath());
						vo.setMobileDetlImgFileNm(adminGoodsVO.getMobileDetlImgFileNm());
						vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(adminGoodsVO.getMobileDetlImgSize(), "0")));
						vo.setMobileDetlImgOrgFileNm(adminGoodsVO.getMobileDetlImgOrgFileNm());
					}
				}
				// ViewType 첨부파일을 선택 했을시
				if(vo.getViewTypeOrginFile() != null && !vo.getViewTypeOrginFile().isEmpty()) {
					if(FileUploadManager.isUploadImageValid(muServletRequest, response, true, "viewTypeOrginFile")) {
						String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
						
						FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "viewTypeOrginFile");
						
						vo.setViewTypeImgPath(fileVO.getFilePath());
						vo.setViewTypeImgFileNm(fileVO.getFileTempName());
						vo.setViewTypeImgSize(String.valueOf(fileVO.getFileSize()));
						vo.setViewTypeImgOrgFileNm(fileVO.getFileOriginName());
					} else {
						throw new Exception();
					}
				} else {   // 파일을 선택안했을시
					if(mobileDetailfileDel.equals("Y")) {// 삭제를 체크했을시
						vo.setViewTypeImgPath("");
						vo.setViewTypeImgFileNm("");
						vo.setViewTypeImgSize("0");
						vo.setViewTypeImgOrgFileNm("");
						vo.setViewTypeImgAlt("");
					} else {
						vo.setViewTypeImgPath(adminGoodsVO.getViewTypeImgPath());
						vo.setViewTypeImgFileNm(adminGoodsVO.getViewTypeImgFileNm());
						vo.setViewTypeImgSize(String.valueOf(StringUtil.nvl(adminGoodsVO.getViewTypeImgSize(), "0")));
						vo.setViewTypeImgOrgFileNm(adminGoodsVO.getViewTypeImgOrgFileNm());
					}
				}

				// 첨부파일 업로드
				if (FileUploadManager.isUploadFileValids(muServletRequest, response, true, "fileUpload")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
					List<FileUploadVO> fileList = FileUploadManager.uploadFiles(muServletRequest, path, "fileUpload");
					vo.setFileList(fileList);
				} else {
					throw new Exception(); 
				}

				adminGoodsServiceImpl.doUpdate(vo);

			} else {
				// 강제 Exception
				throw new Exception();
			}

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("sqprdSeqNo", vo.getSqprdSeqNo());
			hmParam.put("sqprdCtgry", vo.getSqprdCtgry());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";
		} catch (Exception e) {
			e.printStackTrace();
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),null, null, null, true);
			return "admin/basic/goods/registerForm";
		}
	}
	
	/**
	 * 팝업 연관 상품 검색
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @param index
	 * @return
	 */
	@GetMapping("/celloSquareAdmin/goods/searchGoods.do")
	public String searchGoods(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo, @Param("index") String index)  {

		logger.debug("AdminGoodsController :: searchGoods");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		ApiCodeVO apiCodeVO = new ApiCodeVO();
		// 카테고리 값이 없을경우 Expree로 맵핑
		apiCodeVO.setCd(StringUtil.nvl(vo.getSqprdCtgry(), "GOODS_DIV_01"));
		apiCodeVO.setGrpCd("GOODS_DIV");

		String categoryNm = apiCodeServiceImpl.getCategoryNm(apiCodeVO);
		vo.setSqprdCtgryNm(categoryNm);

		int totalCount = adminGoodsServiceImpl.popupTotalCount(vo);
		

		List<AdminGoodsVO> list = new ArrayList<AdminGoodsVO>();

		if(totalCount > 0) {
			list = adminGoodsServiceImpl.getPopUpList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		model.addAttribute("relIndex", index);
		
		return "admin/popup/goods/searchGoodsPopup";
	}

	/**
	 * 상세보기
	 * @param request
	 * @param response
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo)  {

		logger.debug("AdminGoodsController :: detail");

		AdminGoodsVO detailVO = adminGoodsServiceImpl.getDetail(vo);

		// xss 원복
		detailVO.setSqprdDetlInfo(XssPreventer.unescape(detailVO.getSqprdDetlInfo()));
		detailVO.setSqprdNotiContents(XssPreventer.unescape(detailVO.getSqprdNotiContents()));
		
		AdminGoodsVO reltVO1 = null;
		AdminGoodsVO reltVO2 = null;
		AdminGoodsVO reltVO3 = null;
		
		List<AdminGoodsVO> goodsList = new ArrayList<AdminGoodsVO>();
		
		try {
			String stDate = StringUtil.nvl(detailVO.getSvcValidStatDate(), "");
			if(stDate.equals("0001-01-01")) {
				detailVO.setSvcValidStatDate(""); 
			}
			String endDate = StringUtil.nvl(detailVO.getSvcValidEndDate(), "");
			if(endDate.equals("0001-01-01")) {
				detailVO.setSvcValidEndDate(""); 
			}
			if(!detailVO.getReltdSqprd1().trim().isEmpty() && detailVO.getReltdSqprd1() != null) {
				String reltdStr = detailVO.getReltdSqprd1();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO1 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO1 != null) {
					goodsList.add(reltVO1);
				}
			} 
			if(!detailVO.getReltdSqprd2().trim().isEmpty() && detailVO.getReltdSqprd2() != null) {
				String reltdStr = detailVO.getReltdSqprd2();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO2 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO2 != null) {
					goodsList.add(reltVO2);
				}
			} 
			if(!detailVO.getReltdSqprd3().trim().isEmpty() && detailVO.getReltdSqprd3() != null) {
				String reltdStr = detailVO.getReltdSqprd3();
				AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(reltdStr);
				reltVO3 =  adminGoodsServiceImpl.getGoodsNm(adminGoodsVO);
				if(reltVO3 != null) {
					goodsList.add(reltVO3);
				}
			} 

			detailVO.setGoodsList(goodsList);
			
		} catch (Exception e) {
		}

		// seo 정보 가져오기
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		//첨부파일 목록 구하기
		AdminAttachFileVO adminAttachFileVO = new AdminAttachFileVO();
		adminAttachFileVO.setContentsCcd("GOODS");
		adminAttachFileVO.setContentsSeqNo(detailVO.getSqprdSeqNo());
		List<AdminAttachFileVO> attachFileList = adminAttachFileServiceImpl.getAttachFileList(adminAttachFileVO);

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("adminSeoVO", adminSeoVO);
		session.setAttribute("attachFileList", attachFileList);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/goods/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail,Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("attachFileList", session.getAttribute("attachFileList"));
		model.addAttribute("vo", session.getAttribute("vo"));

		return "admin/basic/goods/detail";
	}

	/** 
	 * 상품 삭제
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo) {
		logger.debug("AdminGoodsController :: delete");
		try {
			adminGoodsServiceImpl.delete(vo);

			// seo정보 삭제
			adminSeoServiceImpl.doSeoDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("sqprdSeqNo", vo.getSqprdSeqNo());
			hmParam.put("sqprdCtgry", vo.getSqprdCtgry());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"),null, null, null, true);
			return "admin/basic/goods/detail.do";
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
	@GetMapping("/celloSquareAdmin/goodsImgView.do")
	public String eventImgView(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo) {

		logger.debug("AdminEventController :: imageDownload");
		AdminGoodsVO adminGoodsVO = adminGoodsServiceImpl.getDetail(vo);

		try {
			if(adminGoodsVO != null) {
				FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
				if(vo.getImgKinds().equals("pcList")) {
					SafeFile pcListFile = new SafeFile(adminGoodsVO.getPcListImgPath() , FilenameUtils.getName(adminGoodsVO.getPcListImgFileNm()));
					if (pcListFile.isFile()) {
						fileDownLoadManager.fileFlush(pcListFile, adminGoodsVO.getPcListImgOrgFileNm());
					}
				} else if (vo.getImgKinds().equals("pcDetail")) {
					SafeFile pcDetailFile = new SafeFile(adminGoodsVO.getPcDetlImgPath() , FilenameUtils.getName(adminGoodsVO.getPcDetlImgFileNm()));
					if (pcDetailFile.isFile()) {
						fileDownLoadManager.fileFlush(pcDetailFile, adminGoodsVO.getPcDetlImgOrgFileNm());
					}
				} else if (vo.getImgKinds().equals("mobileList")) {
					SafeFile mobileListfile = new SafeFile(adminGoodsVO.getMobileListImgPath() , FilenameUtils.getName(adminGoodsVO.getMobileListImgFileNm()));
					if (mobileListfile.isFile()) {
						fileDownLoadManager.fileFlush(mobileListfile, adminGoodsVO.getMobileListImgOrgFileNm());						
					}
				} else if (vo.getImgKinds().equals("mobileDetail")) {
					SafeFile mobileDetailfile = new SafeFile(adminGoodsVO.getMobileDetlImgPath() , FilenameUtils.getName(adminGoodsVO.getMobileDetlImgFileNm()));
					if (mobileDetailfile.isFile()) {
						fileDownLoadManager.fileFlush(mobileDetailfile, adminGoodsVO.getMobileDetlImgOrgFileNm());						
					}
				} else if (vo.getImgKinds().equals("viewType")) {
					SafeFile viewTypefile = new SafeFile(adminGoodsVO.getViewTypeImgPath() , FilenameUtils.getName(adminGoodsVO.getViewTypeImgFileNm()));
					if (viewTypefile.isFile()) {
						fileDownLoadManager.fileFlush(viewTypefile, adminGoodsVO.getViewTypeImgOrgFileNm());						
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
	@GetMapping("/celloSquareAdmin/goodsDownload.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo) throws IOException {

		AdminGoodsVO adminGoodsVO = adminGoodsServiceImpl.getDetail(vo);

		String fNm = null;
		String filePath = null;
		if(vo.getImgKinds().equals("pcList")) {
			fNm = adminGoodsVO.getPcListImgOrgFileNm();
			filePath = adminGoodsVO.getPcListImgPath() + "/" + FilenameUtils.getName(adminGoodsVO.getPcListImgFileNm());
		} else if (vo.getImgKinds().equals("pcDetail")) {
			fNm = adminGoodsVO.getPcDetlImgOrgFileNm();
			filePath = adminGoodsVO.getPcDetlImgPath() + "/" + FilenameUtils.getName(adminGoodsVO.getPcDetlImgFileNm());
		} else if (vo.getImgKinds().equals("mobileList")) {
			fNm = adminGoodsVO.getMobileListImgOrgFileNm();
			filePath = adminGoodsVO.getMobileListImgPath() + "/" + FilenameUtils.getName(adminGoodsVO.getMobileListImgFileNm());
		} else if (vo.getImgKinds().equals("mobileDetail")) {
			fNm = adminGoodsVO.getMobileDetlImgOrgFileNm();
			filePath = adminGoodsVO.getMobileDetlImgPath() + "/" + FilenameUtils.getName(adminGoodsVO.getMobileDetlImgFileNm());
		} else if (vo.getImgKinds().equals("viewType")) {
			fNm = adminGoodsVO.getViewTypeImgOrgFileNm();
			filePath = adminGoodsVO.getViewTypeImgPath() + "/" + FilenameUtils.getName(adminGoodsVO.getViewTypeImgFileNm());
		}


		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminGoodsVO != null) {

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
	 * 정렬순서 저장
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@PostMapping("/celloSquareAdmin/goods/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminGoodsVO vo) {
		logger.debug("AdminGoodsController :: doSortOrder");
		try {
			AdminGoodsVO adminGoodsVO = null;
			for(int i = 0; i < vo.getListSqprdSeqNo().length; i++) {
				adminGoodsVO = new AdminGoodsVO();
				adminGoodsVO.setSqprdSeqNo(vo.getListSqprdSeqNo()[i]);
				adminGoodsVO.setSqprdCtgry(vo.getSqprdCtgry());
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
							adminGoodsVO.setOrdb(vo.getListOrdb()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/goods/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/goods/list";
					}
				}
				adminGoodsServiceImpl.doSortOrder(adminGoodsVO);
			}


			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("sqprdCtgry", vo.getSqprdCtgry());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/goods/list.do" + "?" + "sqprdCtgry=" + vo.getSqprdCtgry();
		}
	}


}
