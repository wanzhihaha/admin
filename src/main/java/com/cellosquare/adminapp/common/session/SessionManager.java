package main.java.com.cellosquare.adminapp.common.session;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	
	private static final String SESSION_FORM_ADMIN = "SESSION_FORM_ADMIN";

	public static HttpServletRequest getRequest() {
		
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		
		if(servletRequestAttributes != null) {
			return servletRequestAttributes.getRequest();
		}
		
		return null;
	}
	
	/**
	 * <pre>
	 * 세션에 저장한다.
	 * </pre>
	 *
	 * @param name
	 * @param value
	 */
	public static void setSession(String name, String value) {
		
		HttpSession session = getRequest().getSession();
		session.setAttribute(name, value);
	}
	
	/**
	 * <pre>
	 * 세션에서 가저온다.
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static String getSession(String name) {
		
		HttpSession session = getRequest().getSession();
		return (String)session.getAttribute(name);
	}
	
	public static void createAdminSessionForm(AdminSessionForm adminSessionForm) {
		
		HttpSession session = getRequest().getSession(true);
		session.setAttribute(SESSION_FORM_ADMIN, adminSessionForm);
	}
	
	public static void updateAdminSessionForm(AdminSessionForm adminSessionForm) {
		
		HttpSession session = getRequest().getSession();
		session.setAttribute(SESSION_FORM_ADMIN, adminSessionForm);
	}
	
	public static AdminSessionForm getAdminSessionForm() {
		HttpSession session = getRequest().getSession();
		return (AdminSessionForm)session.getAttribute(SESSION_FORM_ADMIN);
	}
	
	public static void invalidateAdminSessionForm() {
		HttpSession session = getRequest().getSession();
		session.setAttribute(SESSION_FORM_ADMIN, null);
	}
	
	public static boolean isLogin() {
		
		AdminSessionForm sessionForm = getAdminSessionForm();
		
		if(sessionForm == null) {
			return false;
		} else {
			return true;
		}
	}
}
