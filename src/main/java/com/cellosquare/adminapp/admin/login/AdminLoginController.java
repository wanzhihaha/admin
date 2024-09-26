package com.cellosquare.adminapp.admin.login;

import com.bluewaves.lab.encrypt.Encrypt;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.login.service.AdminLoginService;
import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.service.IProductsMenuService;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionConfig;
import com.cellosquare.adminapp.common.session.SessionManager;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class AdminLoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminLoginService adminLoginServiceImpl;
	@Autowired
	private IProductsMenuService productsMenuServiceImpl;

	@Autowired
	private ApiCodeService apiCodeServiceImpl;

	private static final String DEFUALT_LANGUAGE="cn-zh";

	@GetMapping("/celloSquareAdmin/login/loginForm.do")
	public String loginForm(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.debug("AdminLoginController :: loginForm");

		SessionManager.invalidateAdminSessionForm();

		return "admin/full/login/loginForm";
	}


	@PostMapping(value="/celloSquareAdmin/login/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute("vo") AdminLoginVO vo) {
		logger.debug("AdminLoginController :: login");

		AdminManagerVO adminManagerVO = adminLoginServiceImpl.getLogin(vo);

		if(Objects.nonNull(adminManagerVO) && adminManagerVO.getLoginCnt() > 4) {	// 비밀번호 5회 실패시 로그인 못함
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.login.fail.cnt")); //패스워드가 맞지 않음
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");
		}

		boolean isPass = false;


		if(adminManagerVO != null) {
			String passwordAdmin = vo.getPasswordEncpt();
			String passwordEncpt = adminManagerVO.getAdmPw();
			String saltV = adminManagerVO.getSaltVal();

			try {
				if(!StringUtil.isEmpty(passwordAdmin) && !StringUtil.isEmpty(passwordEncpt) && !StringUtil.isEmpty(saltV)) {
					isPass = Encrypt.isEqualsPassowrd(passwordAdmin, passwordEncpt, saltV);
				}
			} catch (Exception e) {
			}
			if(isPass) {

				if(adminManagerVO.getTempPwSts().equals("Y")) {

					Map<String, String> hmParam = new HashMap<String, String>();
					hmParam.put("admUserId", adminManagerVO.getAdmUserId());
					model.addAttribute("msg_type", ":submit");
					model.addAttribute("method","post");
					model.addAttribute("url", XmlPropertyManager.getPropertyValue("system.admin.path")+"/login/updateForm.do");
					model.addAttribute("hmParam", hmParam);

					return "admin/common/message";
				} else {
					AdminSessionForm adminSessionForm = new AdminSessionForm();
					adminSessionForm.setAdminSn(adminManagerVO.getAdmMngSeqNo());
					adminSessionForm.setAdminId(adminManagerVO.getAdmUserId());
					adminSessionForm.setAdminNm(adminManagerVO.getAdmUserNm());
					adminSessionForm.setAdminSeCode(adminManagerVO.getAdmAuth());
					
				/*	ApiCodeVO apiCodeVO_ = new ApiCodeVO();
					apiCodeVO_.setGrpCd("LANG_CD");
					apiCodeVO_.setLangCd("A");
					
					ApiCodeVO apiCodeVO = apiCodeServiceImpl.getApiCodeFirst(apiCodeVO_);*/
					adminSessionForm.setLangCd(DEFUALT_LANGUAGE);	// 한국어 Default (cn-zh)

					//Gson gson = new Gson();
					//adminSessionForm = gson.fromJson(adminManagerVO.toString(), AdminSessionForm.class);

					SessionConfig.getSessionidCheck(adminManagerVO.getAdmUserId());	// 로그인 중복 방지
					SessionManager.createAdminSessionForm(adminSessionForm);

					// 최종 로그인 일자 업데이트
					adminLoginServiceImpl.updateLastLoginDt(adminManagerVO);
				}


			} else {
				adminLoginServiceImpl.updateLoginFailCnt(vo);	// 비밀번호 실패 횟수 카운트
				ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.login.pw.fail")); //패스워드가 맞지 않음
				return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");
			}
		}else {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.login.pw.fail")); //계정정보가 일치하지 않음
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");
		}
        List<ProductsMenu> list = productsMenuServiceImpl.lambdaQuery()
                .eq(ProductsMenu::getUseYn, UseEnum.USE.getCode())
                .orderByAsc(ProductsMenu::getOrdb)
                .orderByDesc(ProductsMenu::getInsDtm).list();
        return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.frist.url")+"?productCtgry="+list.get(0).getProductCtgry();
	}

	/**
	 * 언어코드 세션 설정
	 * @param request
	 * @param response
	 * @param model
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/celloSquareAdmin/login/setLang.do", produces="application/json;charset=UTF-8")
	public String setLang(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute("vo") AdminLoginVO vo) {
		logger.debug("AdminLoginController :: setLang");
		JSONObject jsonObject = new JSONObject();

		if(SessionManager.isLogin()) {
			SessionManager.getAdminSessionForm().setLangCd(vo.getLangCd());

			jsonObject.put("result", "OK");
		} else {
			jsonObject.put("result", "FAIL");
		}
		return jsonObject.toString();

	}

	@PostMapping("/celloSquareAdmin/login/updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute("vo") AdminManagerVO vo) {


		return "admin/full/login/updatePassword";
	}

	@PostMapping("/celloSquareAdmin/login/updtPwd.do")
	public String updtPwd(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute("vo") AdminManagerVO vo) {

		try {
			if(!StringUtil.isEmpty(vo.getPasswordEncpt()) && !StringUtil.isNull(vo.getPasswordEncpt())) {

				byte[] salt = Encrypt.getSalt();
				String dbSalt = Encrypt.byteToBase64(salt);

				vo.setSaltVal(dbSalt);

				vo.setAdmPw(Encrypt.passwordEncrypt(vo.getPasswordEncpt(), salt));	// 변경시에는 받은값으로 넣어줘야한다.

				vo.setTempPwSts("N");

				}

			adminLoginServiceImpl.updatePassword(vo);

		}catch(Exception e) {
			ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"));
			return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");
		}

		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.success"));
		return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");

	}


	@GetMapping("/celloSquareAdmin/login/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		SessionManager.invalidateAdminSessionForm();

		ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.logout"));
		return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.login.url");
	}


}
