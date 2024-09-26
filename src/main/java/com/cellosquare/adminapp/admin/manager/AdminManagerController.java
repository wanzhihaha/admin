package com.cellosquare.adminapp.admin.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluewaves.lab.encrypt.Encrypt;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.manager.service.AdminManagerService;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.EncryptU;
import com.cellosquare.adminapp.common.util.MailSenderUtil;
import com.cellosquare.adminapp.common.util.RandomUtil;

@Controller
public class AdminManagerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminManagerService adminManagerServiceImpl;
	
	@GetMapping("/celloSquareAdmin/manager/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		
		logger.debug("AdminManagerController :: list");
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		if(!sessionForm.getAdminSeCode().equals("TA")) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.not.auth"), null, null, null, true);
			return "admin/basic/manager/list";
		}
		vo.setLangCd(sessionForm.getLangCd());
		
		int totalCount = adminManagerServiceImpl.getTotalCount(vo);
		List<AdminManagerVO> list = new ArrayList<AdminManagerVO>();
		
		if(totalCount > 0) {
			list = adminManagerServiceImpl.getAdminList(vo);
		}		
	
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "admin/basic/manager/list";
	}
	
	@PostMapping("/celloSquareAdmin/manager/registerForm.do")
	public String registerForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
//		logger.debug("AdminManagerController :: registerForm");
		HttpSession session = request.getSession();
		session.removeAttribute("detail");
		session.removeAttribute("vo");
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());

		model.addAttribute("pageType", "I");
		
		return "admin/basic/manager/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/manager/register.do")
	public String registAdmin(HttpServletRequest request, HttpServletResponse resonse, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		
		logger.debug("AdminManagerController :: register");
		try {
			int res = adminManagerServiceImpl.getChkId(vo);
			if(res > 0) {// 아디 중복 체크
				ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.login.id.dup"), null, null, null, true);
				return "admin/basic/manager/registerForm";
			}

			String passWord = vo.getAdmPw();
			if(passWord == null || "".equals(passWord.trim())){
				ActionMessageUtil.setActionMessage(request, "Please enter your Password.", null, null, null, true);
				return "admin/basic/manager/registerForm";
			}
			String pwPattern = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
			passWord = StringEscapeUtils.unescapeXml(passWord);
			if(!passWord.matches(pwPattern)){
				ActionMessageUtil.setActionMessage(request, "Please write a password with a mixture of at least one uppercase/lowercase letter, number, and special symbol.", null, null, null, true);
				return "admin/basic/manager/registerForm";
			}

			AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
			vo.setInsPersonId(sessionForm.getAdminId());
			vo.setUpdPersonId(sessionForm.getAdminId());

//			String email = vo.getAdmEmailAddr();
//			vo.setAdmEmailAddr(EncryptU.encrypt(request, vo.getAdmEmailAddr()));
			
			byte[] salt = Encrypt.getSalt();
			String dbSalt = Encrypt.byteToBase64(salt);
			
			vo.setSaltVal(dbSalt);
			
//			String ranPwd = RandomUtil.get_random_alphnumlow_value(10);
//			
//			logger.debug("==========insert randPw====="+ ranPwd); // 로그인하기위한 임시비밀번호
//			
			vo.setAdmPw(Encrypt.passwordEncrypt(vo.getAdmPw(), salt));
			
			String admCode = vo.getAdmAuth();
			if(!admCode.isEmpty() && admCode.equals("TA")) {
				vo.setTempPwSts("N");
			} else {
				vo.setTempPwSts("Y");
			}
			
			adminManagerServiceImpl.registAdmin(vo);
//			if(adminManagerServiceImpl.registAdmin(vo) > 0) {
//				Map<String,Object> mo = new HashMap<String,Object>();
//				
//				StringBuffer sb = new StringBuffer();
//				sb.append("안녕하세요.<br/><br/>");
//				sb.append("cello 관리자 사이트 임시 비밀번호가 발급되었습니다.<br/><br/>");
//				sb.append("관리자 ID : "+vo.getAdmUserId()+"<br/>");
//				sb.append("임시 비밀번호 : "+ranPwd+"<br/><br/>");
//				sb.append("사이트에 로그인 하신 후 비밀번호를 변경해 주세요.<br/><br/>");
//				sb.append("감사합니다.");
//				
//				mo.put("title", "[celloSquare] 비빌번호 발송");
//				mo.put("receiver", email);
//				mo.put("contents", sb.toString());
//				try {
//					MailSenderUtil.mailSenderApi(mo);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
			return "admin/basic/manager/registerForm";
		}
		
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
		return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/manager/list.do";
	}
	
	@ResponseBody
	@PostMapping("/celloSquareAdmin/manager/chkId.do")
	public String chkId(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo)	{
		
		JSONObject jobj = new JSONObject();
		
		int res = adminManagerServiceImpl.getChkId(vo);
		if(res > 0) {
			jobj.put("res", false);
		} else {
			jobj.put("res", true);
		}
		
		return jobj.toString();
	}
	
	@PostMapping("/celloSquareAdmin/manager/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		vo.setLangCd(sessionForm.getLangCd());
		
		AdminManagerVO detailVO = adminManagerServiceImpl.getDetail(vo);
		if(!StringUtil.isEmpty(detailVO.getAdmEmailAddr())) {
			try {
				detailVO.setAdmEmailAddr(EncryptU.decrypt(request, detailVO.getAdmEmailAddr()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("detail", detailVO);
		session.setAttribute("vo", vo);
		return "redirect:detail.do";
	}

	@GetMapping("/celloSquareAdmin/manager/detail.do")
	public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		model.addAttribute("detail", session.getAttribute("detail"));
		model.addAttribute("vo", session.getAttribute("vo"));
		return "admin/basic/manager/detail";
	}
	
	@ResponseBody
	@PostMapping("/celloSquareAdmin/manager/logincntreset.do")
	public String logincntreste(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) throws EncoderException {
		
		JSONObject jobj = new JSONObject();
		int res = adminManagerServiceImpl.updateLoginFailCntReset(vo);
		if(res > 0) {
			jobj.put("res", true);
		} else {
			jobj.put("res", false);
		}
		
		return jobj.toString();
	}
	
	@PostMapping("/celloSquareAdmin/manager/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) throws EncoderException {
		
		AdminManagerVO detailVO = adminManagerServiceImpl.getDetail(vo);
		if(!StringUtil.isEmpty(detailVO.getAdmEmailAddr())) {
			try {
				detailVO.setAdmEmailAddr(EncryptU.decrypt(request, detailVO.getAdmEmailAddr()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("pageType", "U");
		model.addAttribute("detail", detailVO);
		return "admin/basic/manager/registerForm";
	}
	
	@PostMapping("/celloSquareAdmin/manager/update.do")
	public String updateAdmin(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		
		try {

			String passWord = vo.getAdmPw();
			if(passWord == null || "".equals(passWord.trim())){
				ActionMessageUtil.setActionMessage(request, "Please enter your Password.", null, null, null, true);
				return "admin/basic/manager/registerForm";
			}
			String pwPattern = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
			passWord = StringEscapeUtils.unescapeXml(passWord);
			if(!passWord.matches(pwPattern)){
				ActionMessageUtil.setActionMessage(request, "Please write a password with a mixture of at least one uppercase/lowercase letter, number, and special symbol.", null, null, null, true);
				return "admin/basic/manager/registerForm";
			}

			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			vo.setUpdPersonId(adminSessionForm.getAdminId());
			
			if(!StringUtil.isEmpty(vo.getAdmPw()) && !StringUtil.isNull(vo.getAdmPw())) {
				byte[] salt = Encrypt.getSalt();
				String dbSalt = Encrypt.byteToBase64(salt);
				vo.setSaltVal(dbSalt);
				vo.setAdmPw(Encrypt.passwordEncrypt(vo.getAdmPw(), salt));
			}
				
			
//			vo.setAdmEmailAddr(EncryptU.encrypt(request, vo.getAdmEmailAddr()));
			
			adminManagerServiceImpl.updateAdmin(vo);
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
			return "admin/basic/manager/registerForm";
		}
		
		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.success"));
		
		Map<String, String> hmParam = new HashMap<String, String>();
		hmParam.put("admMngSeqNo", vo.getAdmMngSeqNo());
		hmParam.put("admUserId", vo.getAdminId());
		hmParam.put("admUserNm", vo.getAdmUserNm());
		model.addAttribute("msg_type", ":submit");
		model.addAttribute("method","post");
		model.addAttribute("url", "./detail.do");
		model.addAttribute("hmParam", hmParam);

		return "admin/common/message";
	}
	
	@PostMapping("/celloSquareAdmin/manager/doDelete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) {
		
		try {
			adminManagerServiceImpl.doDelete(vo);
			
			
			Map<String, String> hmParam = new HashMap<String, String>();
			
			hmParam.put("admMngSeqNo", vo.getAdmMngSeqNo());
			
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
			model.addAttribute("url", "./list.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
			return "admin/basic/manager/detail";
		}
		
	}
	
	@PostMapping("/celloSquareAdmin/manager/sendEmail.do")
	public String sendEmailupdt(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminManagerVO vo) throws EncoderException {
		
		AdminManagerVO detailVO = adminManagerServiceImpl.getDetail(vo);
		if(!StringUtil.isEmpty(detailVO.getAdmEmailAddr())) {
			try {
				detailVO.setAdmEmailAddr(EncryptU.decrypt(request, detailVO.getAdmEmailAddr()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
				
			byte[] salt = Encrypt.getSalt();
			String dbSalt = Encrypt.byteToBase64(salt);
			
			vo.setSaltVal(dbSalt);
			
			String ranPwd = RandomUtil.get_random_alphnumlow_value(10);
			vo.setPasswordEncpt(Encrypt.passwordEncrypt(ranPwd, salt));
			vo.setAdmPw(vo.getPasswordEncpt());	
			
			vo.setAdmAuth(detailVO.getAdmAuth());
			vo.setUseYn(detailVO.getUseYn());
			vo.setUpdPersonId(detailVO.getUpdPersonId());
			vo.setTempPwSts("Y");
			
			logger.debug("==== sendEmail ranPwd확인======= "+ranPwd);
				
			//메일발송 필요	
			// 메일발송 구현 필요
			boolean sendmailTF = true;
			Map<String,Object> mo = new HashMap<String,Object>();
			StringBuffer sb = new StringBuffer();
			
			sb.append("안녕하세요.<br/><br/>");
			sb.append("cello 관리자 사이트 임시 비밀번호가 발급되었습니다.<br/><br/>");
			sb.append("관리자 ID : "+detailVO.getAdmUserId()+"<br/>");
			sb.append("임시 비밀번호 : "+ranPwd+"<br/><br/>");
			sb.append("사이트에 로그인 하신 후 비밀번호를 변경해 주세요.<br/><br/>");
			sb.append("감사합니다.");
			
			mo.put("title", "[celloSquare] 비빌번호 발송");
			mo.put("receiver", detailVO.getAdmEmailAddr());
			mo.put("contents", sb.toString());
			try {
				sendmailTF = MailSenderUtil.mailSenderApi(mo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
			
			if(sendmailTF) {
				adminManagerServiceImpl.sendEmail(vo);
			} else {
				throw new Exception();
			}
			
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.eamilSend.success"));
			
			Map<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("admMngSeqNo", vo.getAdmMngSeqNo());
			hmParam.put("admUserId", vo.getAdminId());
			hmParam.put("admUserNm", vo.getAdmUserNm());
			model.addAttribute("msg_type", ":submit");
			model.addAttribute("method","post");
			model.addAttribute("url", "./detail.do");
			model.addAttribute("hmParam", hmParam);
			
			return "admin/common/message";
			
		} catch (Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.eamilSend.fail"), null, null, null, true);
			return "admin/basic/manager/detail";
		}
		
		
	}	
	
	
}
