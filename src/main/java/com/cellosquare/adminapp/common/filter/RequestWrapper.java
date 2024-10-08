package main.java.com.cellosquare.adminapp.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class RequestWrapper extends HttpServletRequestWrapper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
    }
 
	public String[] getParameterValues(String parameter) {
 
		String[] values = super.getParameterValues(parameter);
		if (values==null)  {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
    }
 
    public String getParameter(String parameter) {
    	String value = super.getParameter(parameter);
    	if (value == null) {
    		return null;
    	}
    	return cleanXSS(value);
    }
 
    public String getHeader(String name) {
    	String value = super.getHeader(name);
    	if (value == null)
    		return null;
    	return cleanXSS(value);
    }
 
    private String cleanXSS(String value) {
    	value = value.replaceAll("(?i)\\<script", "<_script_");
    	value = value.replaceAll("(?i)\\<iframe", "<_iframe_");
    	value = value.replaceAll("(?i)\\<object", "<_object_");
    	value = value.replaceAll("(?i)\\<form", "<_form_");
    	value = value.replaceAll("(?i)document.cookie", "");
    	value = value.replaceAll("(?i)javascript", "");
    	value = value.replaceAll("(?i)alert", "");
    	value = value.replaceAll("(?i)onmouseover", "");
    	value = value.replaceAll("(?i)ondblclick", "");
//    	value = HtmlUtil.filter(value);
    	return value;
    }
}
