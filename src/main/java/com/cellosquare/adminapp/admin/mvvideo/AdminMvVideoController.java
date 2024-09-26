package com.cellosquare.adminapp.admin.mvvideo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.ServerException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cellosquare.adminapp.admin.activities.service.ICorporateActivitiesService;
import com.cellosquare.adminapp.admin.apihistory.entity.ApiHistory;
import com.cellosquare.adminapp.admin.dic.entity.Dic;
import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.admin.videoUploadHistory.entity.MvVideoUploadHistory;
import com.cellosquare.adminapp.admin.videoUploadHistory.service.impl.MvVideoUploadHistoryServiceImpl;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.enums.ApihistoryEnum;
import com.cellosquare.adminapp.common.util.*;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.mvvideo.service.AdminMvVideoService;
import com.cellosquare.adminapp.admin.mvvideo.vo.AdminMvVideoVO;
import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

@Controller
public class AdminMvVideoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminMvVideoService adminMvVideoServiceImpl;
	@Autowired
	private AdminSeoService adminSeoServiceImpl;
	@Autowired
	private MvVideoUploadHistoryServiceImpl mvVideoUploadHistoryService;
	@GetMapping("/celloSquareAdmin/mvVideo/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		logger.debug("AdminMainViewController :: list");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());


		int totalCount = adminMvVideoServiceImpl.getTotalCount(vo);
		List<AdminMvVideoVO> list = new ArrayList<AdminMvVideoVO>();

		if(totalCount > 0 ) {
			list = adminMvVideoServiceImpl.getList(vo);
		}

		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);


		return "admin/basic/mvvideo/list";
	}

	@PostMapping("/celloSquareAdmin/mvVideo/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/mvvideo/registerForm";
	}

	@PostMapping("/celloSquareAdmin/mvVideo/register.do")
	@CleanCacheAnnotion
	public String write(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());

			vo.setLangCd(sessionForm.getLangCd());

			// 대표이미지가 있을경우
			if(!vo.getPcListOrginFile().isEmpty()) {

				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvVideo")));

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
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvVideo")));

					FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
					vo.setMobileListImgPath(fileVO.getFilePath());
					vo.setMobileListImgFileNm(fileVO.getFileTempName());
					vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
					vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
				}else {
					throw new Exception();
				}
			}

			//处理富文本信息
			this.setXssUtilsoperationContent(vo);
			adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
			adminMvVideoServiceImpl.doWrite(vo);
			//保存seo信息
			BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.VIDEO.getCode(), vo.getTitleNm(), null,
					vo.getContent(), null, vo);
			SeoUtils.setSeoMsg(baseSeoParam);
			adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
			if(!vo.getVideo().isEmpty() || !vo.getShortVideo().isEmpty()) {
				adminMvVideoServiceImpl.saveVideo(muServletRequest,vo);
			}
		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/mvVideo/registerForm";
		}


		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
		return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/mvVideo/list.do";
	}

	@PostMapping("/celloSquareAdmin/mvVideo/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		AdminMvVideoVO detailVO = adminMvVideoServiceImpl.getDetail(vo);
		detailVO.setContent(XssPreventer.unescape(detailVO.getContent()));
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		session.setAttribute("adminSeoVO", adminSeoVO);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/mvVideo/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/mvvideo/detail";
	}

	@PostMapping("/celloSquareAdmin/mvVideo/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		AdminMvVideoVO detailVO = adminMvVideoServiceImpl.getDetail(vo);

		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU","U");

		return "admin/basic/mvvideo/registerForm";
	}

	private void setXssUtilsoperationContent(AdminMvVideoVO vo){
		if(StringUtils.isNotEmpty(vo.getContent())){
			vo.setContent(XssUtils.operationContent(vo.getContent(), vo.getTitleNm()));
		}
	}

	@PostMapping("/celloSquareAdmin/mvVideo/update.do")
	@CleanCacheAnnotion
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminMvVideoVO vo) {
		String plistFileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
		String mListFileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");

		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

			AdminMvVideoVO detailVO = adminMvVideoServiceImpl.getDetail(vo);

			if(!vo.getPcListOrginFile().isEmpty()) {

				if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvVideo")));

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
					String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMvVideo")));

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
			vo.setMetaSeqNo(detailVO.getMetaSeqNo());
			//处理富文本信息
			this.setXssUtilsoperationContent(vo);
			BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.VIDEO.getCode(), vo.getTitleNm(), null,
					vo.getContent(), null, vo);
			SeoUtils.setSeoMsg(baseSeoParam);
			adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
			adminMvVideoServiceImpl.doUpdate(vo);


			Map<String, String> hmParam = new HashMap<String, String>();

			hmParam.put("mvVideoSeqNo", vo.getMvVideoSeqNo());

			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			if(!vo.getVideo().isEmpty() || !vo.getShortVideo().isEmpty()) {
				adminMvVideoServiceImpl.saveVideo(muServletRequest,vo);
			}
			return "admin/common/message";

		} catch (Exception e) {
			throw new RuntimeException(e);
//			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
//			return "admin/basic/mvVideo/registerForm";
		}

	}

	@PostMapping("/celloSquareAdmin/mvVideo/doDelete.do")
	@CleanCacheAnnotion
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		try {
			adminMvVideoServiceImpl.doDelete(vo);

			Map<String, String> hmParam = new HashMap<String, String>();

			hmParam.put("mvVideoSeqNo", vo.getMvVideoSeqNo());

			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");

			return "admin/common/message";

		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/mvvideo/detail";
		}

	}

	@ResponseBody
	@GetMapping("/celloSquareAdmin/mvVideoImgView.do")
    public String noTokendownload(HttpServletRequest request, HttpServletResponse response,
    		@ModelAttribute("vo") AdminMvVideoVO vo, Model model) {

		AdminMvVideoVO bean = adminMvVideoServiceImpl.getDetail(vo);

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

	@GetMapping("/celloSquareAdmin/mvVideoFileDown.do")
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo, RedirectAttributes rtt) throws IOException {

		AdminMvVideoVO infoVO = adminMvVideoServiceImpl.getDetail(vo);

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

	@PostMapping("/celloSquareAdmin/mvVideo/doSortOrder.do")
	@CleanCacheAnnotion
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo) {

		try {
			AdminMvVideoVO adminMvVideoVO = null;
			for(int i = 0; i < vo.getListMvVideoSeq().length; i++) {
				adminMvVideoVO = new AdminMvVideoVO();
				adminMvVideoVO.setMvVideoSeqNo(vo.getListMvVideoSeq()[i]);

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
							adminMvVideoVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/mvvideo/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/mvvideo/list";
					}
				}

				adminMvVideoServiceImpl.doSortOrder(adminMvVideoVO);
			}


			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/mvVideo/list.do";
		}
	}

	@GetMapping("/celloSquareAdmin/mvVideo/searchVideo.do")
	public String searchVideo(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvVideoVO vo)  {

		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		int totalCount = adminMvVideoServiceImpl.popupTotalCount(vo);

		List<AdminVideoVO> list = new ArrayList<AdminVideoVO>();

		if(totalCount > 0) {
			list = adminMvVideoServiceImpl.getPopUpList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);

		return "admin/popup/mvvideo/searchVideoPopup";
	}
	@GetMapping("/celloSquareAdmin/mvVideo/dicList.do")
	public String searchHashtag(@ModelAttribute("vo") TerminologyVO vo, Model model){
		Page<MvVideoUploadHistory> page = mvVideoUploadHistoryService.lambdaQuery().orderByDesc(MvVideoUploadHistory::getId)
				.page(new Page<MvVideoUploadHistory>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
		model.addAttribute("list", page.getRecords());
		model.addAttribute("totalCount", page.getTotal());
		return "admin/popup/mvvideo/dicList";
	}

}
