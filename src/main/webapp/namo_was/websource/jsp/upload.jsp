<%@page contentType="text/html;charset=utf-8" %>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileUploadBase"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.json.*"%>
<%@include file="Util.jsp"%>

<%!
	public String getRequestBuffer(InputStream inputStream) {
		String strRet = "";
		StringBuilder stringBuilder = new StringBuilder();  
		BufferedReader bufferedReader = null;  

		try {  
			if (inputStream != null) {  
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  

				char[] charBuffer = new char[128];  
				int bytesRead = -1;  
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {  
					stringBuilder.append(charBuffer, 0, bytesRead);  
				}  
			} else {  
				stringBuilder.append("");  
			}  
		} catch (IOException ex) {  
			ex.printStackTrace();
		} finally {  
			if (bufferedReader != null) {  
				try {  
					bufferedReader.close();  
				} catch (IOException ex) {  
					ex.printStackTrace();
				}  
			}  
		}  
		strRet = stringBuilder.toString();
		return strRet;
	}
	public String Base64ToFile (String strSavePath, String strSaveFName, String strBase64, String strSubDir, double dImageSizeLimit) {
		String strRet = "";
		byte[] imageBytes = Base64.decodeBase64(strBase64.getBytes());
		try {
			if(!strSubDir.equalsIgnoreCase("false")){
				strSavePath = strSavePath + File.separator;
				strSavePath += "images";
			}
			
			File f = new File(strSavePath);
			if (!f.exists()) {
				f.setExecutable(false, true);
				f.setReadable(true);
				f.setWritable(false, true);
				f.mkdir();
			}
			String subPath = "";
			if(!strSubDir.equalsIgnoreCase("false")){
				subPath = getChildDirectory(strSavePath, "100");
				strSavePath += File.separator + subPath; 
			}
			String fileTempName = fileNameTimeSetting();
			String strExt = strSaveFName.substring(strSaveFName.lastIndexOf(".")).toLowerCase();
			String strPath = strSavePath + File.separator + fileTempName  + strExt;
			File ff = new File(strPath);
			if (!ff.exists()) {
				ff.setExecutable(false, true);
				ff.setReadable(true);
				ff.setWritable(false, true);
				ff.createNewFile();
			}
			FileOutputStream fs = new FileOutputStream(ff);
			BufferedOutputStream bs = new BufferedOutputStream(fs);
			bs.write(imageBytes);
			bs.close();
			bs = null;

			double fileLength = ff.length();
			if(dImageSizeLimit < fileLength){
				strRet = "invalid_size";
				ff.delete();
			}else{
				if(!strSubDir.equalsIgnoreCase("false")){
					strRet = "/images/" + subPath + "/" + fileTempName + strExt;
				}else{
					strRet = "/" + fileTempName + strExt;
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return strRet;
	}

	public String fileUploadProc (String strSavePath, String strSaveFName, FileItem fileItem, String strSubDir, double dImageSizeLimit) {
		String strRet = "";
		//byte[] imageBytes = Base64.decodeBase64(strBase64.getBytes());
		try {
			if(!strSubDir.equalsIgnoreCase("false")){
				strSavePath = strSavePath + File.separator;
				strSavePath += "images";
			}
			
			File f = new File(strSavePath);
			if (!f.exists()) {
				f.setExecutable(false, true);
				f.setReadable(true);
				f.setWritable(false, true);
				f.mkdir();
			}
			String subPath = "";
			if(!strSubDir.equalsIgnoreCase("false")){
				subPath = getChildDirectory(strSavePath, "100");
				strSavePath += File.separator + subPath; 
			}
			String fileTempName = fileNameTimeSetting();
			String strExt = strSaveFName.substring(strSaveFName.lastIndexOf(".")).toLowerCase();
			String strPath = strSavePath + File.separator + fileTempName  + strExt;
			File ff = new File(strPath);
			if (!ff.exists()) {
				ff.setExecutable(false, true);
				ff.setReadable(true);
				ff.setWritable(false, true);
				ff.createNewFile();
			}

			fileItem.write(ff);
			fileItem.delete(); 
			
			if(dImageSizeLimit < fileItem.getSize()){
				strRet = "invalid_size";
			}else{
				if(!strSubDir.equalsIgnoreCase("false")){
					strRet = "/images/" + subPath + "/" + fileTempName + strExt;
				}else{
					strRet = "/" + fileTempName + strExt;
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return strRet;
	}
%>
<%
	String imageTemp = "";
	String imageUPath = "";
	String imagePhysicalPath = "";
	String uploadFileExtBlockList = "";
	String uploadFileSubDir = "";
	double imageSizeLimit = 5242880;

	PrintWriter Output = response.getWriter();

	String contentType = request.getContentType();

	List items = null;
	List<FileItem> fileItems = new ArrayList<FileItem>();

	JSONArray filesArray = null;
	JSONArray jsonArr = new JSONArray();
	JSONObject jsonobj = new JSONObject();
	
	if(contentType.indexOf("multipart/form-data;") != -1){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = null;
		ServletFileUpload upload = null;
		String type = "";
		String nm = "";
		if (isMultipart) {
			factory = new DiskFileItemFactory();                             
			factory.setSizeThreshold(2 * 1024 * 1024); 
			upload = new ServletFileUpload(factory);  
			upload.setSizeMax(-1); 
			upload.setHeaderEncoding("utf-8");
			items = upload.parseRequest(request);

			for(int i=0; items.size()>i; i++){
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()){          
						if(fileItem.getFieldName().equalsIgnoreCase("imageUPath")) imageUPath = toString(fileItem.getString("utf-8"));
						if(fileItem.getFieldName().equalsIgnoreCase("uploadFileSubDir")) uploadFileSubDir = toString(fileItem.getString("utf-8"));
						if(fileItem.getFieldName().equalsIgnoreCase("imageSizeLimit")) imageSizeLimit = Double.parseDouble(toString(fileItem.getString("utf-8")));						
				} else {  
					if(fileItem.getSize()>0) {
						fileItems.add(fileItem);
					}
				}
			}      
		}else{
			response.getWriter().println("not encoding type multipart/form-data");
		}

	}else{
		String str = getRequestBuffer(request.getInputStream());
		if (str.length() <= 0) {
			Output.print("");
			Output.close();
			return;
		}

		JSONObject object = new JSONObject(str);
		JSONObject pduObj = (JSONObject)object.get("pdu");
		filesArray = (JSONArray)pduObj.get("files");

		imageUPath = pduObj.get("imageUPath").toString();
		uploadFileSubDir = pduObj.get("uploadFileSubDir").toString();
		imageSizeLimit = Double.parseDouble(pduObj.get("imageSizeLimit").toString());
	}
	
%>
<%@include file="ImagePath.jsp"%>
<%@include file="UploadFileExtBlockList.jsp"%>
<%
	
	String strUPath = "";
	String strAbsolutePath = "";
	String protocol = "http://";
	if(request.isSecure()){
		protocol = "https://";
	}
	String imageUPathHost = protocol + request.getHeader("host");

	if(imageUPath.length() > 0){
		if(imageUPath.indexOf("http") == 0){
			strUPath = imageUPath;
		}else{
			strUPath = imageUPathHost + imageUPath;
		}
	}else{
		String curUrlPath = request.getRequestURI();
		curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
		curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
		curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
		strUPath = imageUPathHost + curUrlPath + "/binary";
	}

	if(imagePhysicalPath.length() > 0){
		strAbsolutePath = imagePhysicalPath;
	}else{
		ServletContext context = getServletContext();
		String contextPath = request.getContextPath();

		if(!imageUPath.equalsIgnoreCase("")){
			if (imageUPath.length() > 7) {
				if (imageUPath.substring(0, 7).equalsIgnoreCase("http://")) {
					imageTemp = imageUPath.substring(7);
					imageUPath = imageTemp.substring(imageTemp.indexOf("/"));
				}
				else if (imageUPath.substring(0, 8).equalsIgnoreCase("https://")) {
					imageTemp = imageUPath.substring(8);
					imageUPath = imageTemp.substring(imageTemp.indexOf("/"));
				}
			}
			strAbsolutePath = context.getRealPath(imageUPath.substring(contextPath.length()));
		}else{
			String curUrlPath = request.getRequestURI();
			
			curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
			curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
			curUrlPath = curUrlPath.substring(0, curUrlPath.lastIndexOf("/"));
			curUrlPath.substring(contextPath.length());

			strAbsolutePath = context.getRealPath(curUrlPath.substring(contextPath.length()) + "/binary");
		}
	}
	
	if(filesArray != null){
		for (int i = 0; i < filesArray.length(); i++) {
			Object jobj = filesArray.get(i);
			String strFileName = ((JSONObject) jobj).get("filename").toString();

			String strExt = strFileName.substring(strFileName.lastIndexOf(".") + 1).toLowerCase();
			if(uploadFileExtBlockList.length() > 0 && !isArray(uploadFileExtBlockList, strExt)){
				final JSONObject o1 = new JSONObject();
				o1.put("url", "UploadFileExtBlock");
				o1.put("filename", strFileName);
				jsonArr.put(o1);
				continue;
			}

			String file = ((JSONObject) jobj).get("base64string").toString();
			String strFileURL = Base64ToFile (strAbsolutePath, strFileName, file, uploadFileSubDir, imageSizeLimit);
			if (strFileURL.length() > 0 && !strFileURL.equals("")) {
				final JSONObject o = new JSONObject();
				if("invalid_size".equalsIgnoreCase(strFileURL)){
					o.put("url", strFileURL);
					o.put("filename", strFileName);
				}else{
					o.put("url", strUPath + strFileURL);
					o.put("filename", strFileName);
				}
				
				jsonArr.put(o);
			}
		}
	}

	if(fileItems != null){
		for(int i=0; fileItems.size()>i; i++){
			FileItem fileItem2 = fileItems.get(i);
			String strFileName2 = fileItem2.getName();
			String strExt = strFileName2.substring(strFileName2.lastIndexOf(".") + 1).toLowerCase();

			if(uploadFileExtBlockList.length() > 0 && !isArray(uploadFileExtBlockList, strExt)){
				final JSONObject o1 = new JSONObject();
				o1.put("url", "UploadFileExtBlock");
				o1.put("filename", strFileName2);
				jsonArr.put(o1);
				continue;
			}
			
			String strFileURL = fileUploadProc (strAbsolutePath, strFileName2, fileItem2, uploadFileSubDir, imageSizeLimit);
			if (strFileURL.length() > 0 && !strFileURL.equals("")) {
				final JSONObject o = new JSONObject();
				if("invalid_size".equalsIgnoreCase(strFileURL)){
					o.put("url", strFileURL);
					o.put("filename", strFileName2);
				}else{
					o.put("url", strUPath + strFileURL);
					o.put("filename", strFileName2);
				}
				jsonArr.put(o);
			}
		}
	}

	jsonobj.put("files",jsonArr);
	String json = jsonobj.toString();
	Output.print(json);
	Output.flush();
	Output.close();
	
%>

