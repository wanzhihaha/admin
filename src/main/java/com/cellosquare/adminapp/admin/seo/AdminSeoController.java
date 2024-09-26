package com.cellosquare.adminapp.admin.seo;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.util.FileDownUtil;

@Controller
public class AdminSeoController {
	@Autowired
	private AdminSeoService adminSeoServiceImpl;

	@GetMapping("/celloSquareAdmin/seoDownload.do")	
	public void seoDownload(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("vo") AdminSeoVO vo, Model model) throws IOException {
		
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(vo);
				
		OutputStream os = null;
		FileInputStream fis = null;

		try {
			if(adminSeoVO != null) {
				String fNm = adminSeoVO.getOgImgOrgFileNm();
				String filePath = adminSeoVO.getOgImgPath() + "/" + adminSeoVO.getOgImgFileNm();
				
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
	
	
	@ResponseBody
	@GetMapping("/celloSquareAdmin/seoImgView.do")	
	public String seoImgView(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("vo") AdminSeoVO vo, Model model) {
		
		AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(vo);
		
		try {
			if(adminSeoVO != null) {
				FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
				SafeFile file = new SafeFile(adminSeoVO.getOgImgPath() + "/" + adminSeoVO.getOgImgFileNm());
				if(file.isFile()) {
					fileDownLoadManager.fileFlush(file, adminSeoVO.getOgImgFileNm());
				}
			}
			
		} catch (Exception e) {
		}
		
		return null;
	}
}
