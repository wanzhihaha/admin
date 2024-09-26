package com.cellosquare.adminapp.admin.namo;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.SafeFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;

@Controller
public class NamoController {
	@ResponseBody
	@GetMapping("/namoImgView.do")	
	public String namoImgView(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String filePath =  StringUtil.nvl(request.getParameter("nfile"),"");
		String orgPath = XmlPropertyManager.getPropertyValue("uploadFilePathNamo");

		if(filePath.contains("../")){
			filePath = "";
		}
		
		try {
			if(!filePath.equals("")) {
				FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
				SafeFile file = new SafeFile(orgPath + File.separator +filePath);
				if(file.isFile()) {
					fileDownLoadManager.fileFlush(file, "");
				}
			}
			
		} catch (Exception e) {
		}
		
		return null;
	}
}
