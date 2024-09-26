package com.cellosquare.adminapp.admin.attachfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cellosquare.adminapp.admin.attachfile.service.AdminAttachFileService;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;

@Controller
public class AdminAttachFileController {
	@Autowired
	private AdminAttachFileService adminAttachFileServiceImpl;
	
	@GetMapping(value={"/celloSquareAdmin/fileDown.do"})
	public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("vo") AdminAttachFileVO vo) throws IOException {

		AdminAttachFileVO adminAttachFileVO = adminAttachFileServiceImpl.getDetail(vo);

		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminAttachFileVO != null) {
				String fNm = adminAttachFileVO.getAtchOrgFileNm();
				String filePath = adminAttachFileVO.getAtchFilePath() + File.separator + adminAttachFileVO.getAtchFileNm();
				
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
	
	
	
}
