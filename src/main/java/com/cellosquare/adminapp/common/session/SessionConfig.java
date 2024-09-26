package main.java.com.cellosquare.adminapp.common.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class SessionConfig implements HttpSessionListener{

	private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();
	private static final String SESSION_FORM_ADMIN = "SESSION_FORM_ADMIN";
	
	//중복로그인 지우기
	public synchronized static String getSessionidCheck(String compareId){
		String result = "";
		//System.out.println("====================sessionConfig start");
		
		for( String key : sessions.keySet() ){
			HttpSession hs = sessions.get(key);
			if(hs != null){
				//System.out.println("=================result====="+key.toString());
				AdminSessionForm form = (AdminSessionForm)hs.getAttribute(SESSION_FORM_ADMIN);
				if(form != null && form.getAdminId().toString().equals(compareId)) {
					result =  key.toString();
					removeSessionForDoubleLogin(result);
				}
			}
		}
		
		return result;
	}
	
	private static void removeSessionForDoubleLogin(String userId){    	
		//System.out.println("remove userId : " + userId);
		if(userId != null && userId.length() > 0){
			sessions.get(userId).invalidate();
			sessions.remove(userId);    		
		}
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		 sessions.put(se.getSession().getId(), se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
		if(sessions.get(se.getSession().getId()) != null){
			sessions.get(se.getSession().getId()).invalidate();
			sessions.remove(se.getSession().getId());	
		}
	}

}
