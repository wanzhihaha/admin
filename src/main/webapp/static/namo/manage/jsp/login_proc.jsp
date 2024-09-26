<%@page contentType="text/html;charset=utf-8" %>
<%@include file="manager_util.jsp"%>
<%	
	/*
	String id = detectXSSEx(request.getParameter("m_id"));
	String passwd = detectXSSEx(encrypt("SHA-256", request.getParameter("passwd")));
	*/
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    byte[] b1 = decoder.decodeBuffer(detectXSSEx(request.getParameter("passwd")));
    String pwval = new String(b1);

	byte[] b2 = decoder.decodeBuffer(detectXSSEx(request.getParameter("m_id")));
    String idval = new String(b2);

	String id = encrypt("SHA-256", idval);
	String passwd = encrypt("SHA-256", pwval);

	String webPageKind = detectXSSEx(request.getParameter("webPageKind"));
	String result_sc = "";
	String fileRealFolder = "";

	String ContextPath = request.getContextPath();
	String urlPath = rootFolderPath(request.getRequestURI());
	
	ServletContext context = getServletConfig().getServletContext();
	fileRealFolder = context.getRealPath(urlPath);

	//2013.08.26 [2.0.5.23] mwhong tomcat8.0 에서 getRealPath가 null을 리턴하여 수정
	if(fileRealFolder == null && urlPath != null && ContextPath != null){
		fileRealFolder = context.getRealPath(urlPath.substring(ContextPath.length()));
	}

	if (ContextPath != null && !ContextPath.equalsIgnoreCase("") && !ContextPath.equalsIgnoreCase("/")){
		File tempFileRealDIR = new File(fileRealFolder);
		if(!tempFileRealDIR.exists()){
			if (urlPath != null && urlPath.indexOf(ContextPath) != -1){
				String rename_image_temp = urlPath.substring(ContextPath.length());
				fileRealFolder = context.getRealPath(rename_image_temp);
			}
		}
	}
	
	if (fileRealFolder != null && fileRealFolder.lastIndexOf(File.separator) != fileRealFolder.length() - 1){
		fileRealFolder = fileRealFolder + File.separator;
	}
	
	String manageInfoStr = manageInFo_text(fileRealFolder);
	
	String u_id = "";
	String u_pass = null;
	String sep = ";";
	String sep_sub = "=";
	String manageArr[] = manageInfoStr.split(sep);

	for (int i = 0; i < manageArr.length; i++)
	{
		if(manageArr[i].indexOf("u_id") != -1){
			if (manageArr[i].indexOf("\"") != -1){
				u_id = manageArr[i].substring(manageArr[i].indexOf("u_id"), manageArr[i].lastIndexOf("\""));

				String[] uIDArr = u_id.split(sep_sub);
				if (!uIDArr[1].equalsIgnoreCase("")){
					u_id = uIDArr[1];
					if (u_id.indexOf("\"") != -1) u_id = u_id.substring(u_id.indexOf("\"") + 1);
					u_id = u_id.trim();
				}
			}
		}
		else if(manageArr[i].indexOf("u_pass") != -1){
			if (manageArr[i].indexOf("\"") != -1){
				u_pass = manageArr[i].substring(manageArr[i].indexOf("u_pass"), manageArr[i].lastIndexOf("\""));

				String[] uIDArr = u_pass.split("=");
				if (!uIDArr[1].equalsIgnoreCase("")){
					u_pass = uIDArr[1];
					if (u_pass.indexOf("\"") != -1) u_pass = u_pass.substring(u_pass.indexOf("\"") + 1);
					u_pass = u_pass.trim();
				}
			}
		}

	}

	if(id.equalsIgnoreCase(u_id) || id.equalsIgnoreCase(encrypt("SHA-256", u_id))){
		passwd = passwd.toLowerCase();
		if(passwd.equalsIgnoreCase(u_pass)){

			session.setAttribute("memId",idval);
			session.setAttribute("webPageKind",webPageKind);
			result_sc = "<script>window.document.location.href='update_check.jsp';</script>";
		}
		else{
			result_sc = "<script>alert(NamoSELang.pe_Ca);history.back();</script>";
		}
	}
	else{
		result_sc = "<script>alert(NamoSELang.pe_Qr);history.back();</script>";
	}

%>

<html>
	<head>
	<script type="text/javascript" src="../../lib/jquery-1.7.2.min.js"> </script>
	<script type="text/javascript">var ce$=namo$.noConflict(true); </script>
		<script type="text/javascript" src="../manage_common.js"> </script>
		<script type="text/javascript" language="javascript" src="../../js/namo_cengine.js"> </script>
	</head>
	<body>
		<%= result_sc%>
	</body>
</html>