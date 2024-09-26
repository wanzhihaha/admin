package main.java.com.cellosquare.adminapp.common.interceptor.login;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginCheckInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private com.cellosquare.adminapp.admin.login.service.AdminLoginService adminLoginServiceImpl;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOG.debug("================================ LoginCheckInterceptor ================================");
		String requestURI = request.getRequestURI();
		LOG.debug("requestURI=========="+requestURI);

		if(SessionManager.isLogin()) {

			AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
			com.cellosquare.adminapp.admin.login.vo.AdminLoginVO vo = new com.cellosquare.adminapp.admin.login.vo.AdminLoginVO();
			vo.setAdminId(adminSessionForm.getAdminId());
			com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO login = adminLoginServiceImpl.getLogin(vo);
			if(login == null) {
				goPopupAlertRedirectURLInter(response, XmlMessageManager.getMessageValue("message.login.auth"),XmlPropertyManager.getPropertyValue("system.admin.login.url"));
				return false;
			}

			if(requestURI.startsWith("/celloSquareAdmin/manager/") && !isTopAdmin()){
					goPopupAlertRedirectURLInter(response, XmlMessageManager.getMessageValue("message.login.auth"),XmlPropertyManager.getPropertyValue("system.admin.login.url"));
					return false;
			}
			return true;
		} else {
			goPopupAlertRedirectURLInter(response, XmlMessageManager.getMessageValue("message.login.auth"),XmlPropertyManager.getPropertyValue("system.admin.login.url"));
    		return false;
			//return true;
		}

	}
	
	
	protected void goPopupAlertRedirectURLInter(HttpServletResponse response, String msg, String url) throws IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		   PrintWriter printwriter = response.getWriter();
		   
		   printwriter.println("<html>");
		   printwriter.println("<script type=\"text/javascript\">");
		   printwriter.println("alert(\"" + msg + "\");");
		   printwriter.println("window.location.href = '"+url+"'; ");
		   printwriter.println("</script>");
		   printwriter.println("</html>");
		   
		   printwriter.flush();
		   printwriter.close();
		
	}



	public  boolean isTopAdmin() {
		AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
		com.cellosquare.adminapp.admin.login.vo.AdminLoginVO vo=new com.cellosquare.adminapp.admin.login.vo.AdminLoginVO();
		vo.setAdminId(sessionForm.getAdminId());
		com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO adminManagerVO = adminLoginServiceImpl.getLogin(vo);
		if(adminManagerVO !=null && "TA".equals(adminManagerVO.getAdmAuth()) ) {
			return true;
		} else {
			return false;
		}
	}
}
