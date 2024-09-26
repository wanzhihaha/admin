package com.cellosquare.adminapp.admin.mvsqprd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.mvsqprd.service.AdminMvSqprdService;
import com.cellosquare.adminapp.admin.mvsqprd.vo.AdminMvSqprdVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;

@Controller
public class AdminMvSqprdController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminMvSqprdService adminMvSqprdServiceImpl;
	
	@GetMapping("/celloSquareAdmin/mvProduct/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		

		int totalCount = adminMvSqprdServiceImpl.getTotalCount(vo);
		List<AdminMvSqprdVO> list = new ArrayList<AdminMvSqprdVO>();
		
		if(totalCount > 0 ) {
			list = adminMvSqprdServiceImpl.getList(vo);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);
		
		
		return "admin/basic/mvsqprd/list";
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/registerForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		model.addAttribute("contIU", "I");
		return "admin/basic/mvsqprd/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/register.do")
	public String write(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());
			
			vo.setLangCd(sessionForm.getLangCd());
			
			adminMvSqprdServiceImpl.doWrite(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.write.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/mvsqprd/registerForm";
		}
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		
		AdminMvSqprdVO detailVO = adminMvSqprdServiceImpl.getDetail(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/mvProduct/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail,Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("adminSeoVO", session.getAttribute("adminSeoVO"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/mvsqprd/detail";
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		
		AdminMvSqprdVO detailVO = adminMvSqprdServiceImpl.getDetail(vo);
		
		model.addAttribute("detail", detailVO);
		model.addAttribute("contIU","U");
		
		return "admin/basic/mvsqprd/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/update.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model, MultipartHttpServletRequest muServletRequest,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
	
		try {
			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(sessionForm.getAdminId());

//			AdminMvSqprdVO detailVO = adminMvSqprdServiceImpl.getDetail(vo);
			
			
			adminMvSqprdServiceImpl.doUpdate(vo);
			
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("mvSqprdSeqNo", vo.getMvSqprdSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method", "post");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/mvsqprd/registerForm";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/mvProduct/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {
		
		try {
			adminMvSqprdServiceImpl.doDelete(vo);
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("mvSqprdSeqNo", vo.getMvSqprdSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/baisc/mvsqprd/detail";
		}
		
	}
	
	@ResponseBody
	@GetMapping("/celloSquareAdmin/mvPrdImgView.do")
    public String noTokendownload(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("vo") AdminMvSqprdVO vo, Model model) {

		AdminMvSqprdVO bean = adminMvSqprdServiceImpl.getDetail(vo);
		
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
	
	@PostMapping("/celloSquareAdmin/mvProduct/doSortOrder.do")
	public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo) {

		try {
			AdminMvSqprdVO adminMvSqprdVO = null;
			for(int i = 0; i < vo.getListMvSqprdSeq().length; i++) {
				adminMvSqprdVO = new AdminMvSqprdVO();
				adminMvSqprdVO.setMvSqprdSeqNo(vo.getListMvSqprdSeq()[i]);
				
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
							adminMvSqprdVO.setOrdb(vo.getListSortOrder()[i]);
						} else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
							ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
							return "admin/basic/mvsqprd/list";
						}
					} else { //숫자가 아니면 오류메세지
						ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
						return "admin/basic/mvsqprd/list";
					}
				}
				
				adminMvSqprdServiceImpl.doSortOrder(adminMvSqprdVO);
			}
			

			Map<String, String> hmParam = new HashMap<String, String>();
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);

			return "admin/common/message";

		}catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/mvProduct/list.do";
		}
	}
	
	@GetMapping("/celloSquareAdmin/mvProduct/searchGoods.do")
	public String searchGoods(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminMvSqprdVO vo)  {

		logger.debug("AdminGoodsController :: searchGoods");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());


		int totalCount = adminMvSqprdServiceImpl.popupTotalCount(vo);
		

		List<AdminGoodsVO> list = new ArrayList<AdminGoodsVO>();

		if(totalCount > 0) {
			list = adminMvSqprdServiceImpl.getPopUpList(vo);
		}

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/popup/mvsqprd/searchGoodsPopup";
	}
	
	
}
