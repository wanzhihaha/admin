package com.cellosquare.adminapp.admin.code;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;

@Controller
public class ApiCodeController {
	
	@Autowired
	private ApiCodeService apiCodeServiceImpl;
	
	@ResponseBody
	@GetMapping(value="/api/getCodeList.do", produces="application/json;charset=UTF-8")
	public String getApiCodeList(HttpServletRequest request, HttpServletResponse response
			, ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		
		ApiCodeVO vo = new ApiCodeVO();
		vo.setGrpCd(StringUtil.nvl(request.getParameter("sGb")));
		vo.setLangCd(StringUtil.nvl(request.getParameter("langCd")));
		List<ApiCodeVO> list = apiCodeServiceImpl.getApiCodeList(vo);
		
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
}
