<%
/*
String namoFileKind = request.getParameter("namofilekind");

//filelink
String namoFilePhysicalPath = "D:\\cejava\\htdocs\\ce3\\namofile";
String namoFileUPath = "/ce3/namofile";

//movie
String namoFlashPhysicalPath = "D:\\cejava\\htdocs\\ce3\\namomovie";
String namoFlashUPath = "/ce3/namomovie";

//image
String namoImagePhysicalPath = "D:\\cejava\\htdocs\\ce3\\namoimage";
String namoImageUPath = "/ce3/namoimage";

System.out.println("namoFileKind: "+ namoFileKind);

if(namoFileKind != null && "file".equals(namoFileKind)){
	imagePhysicalPath = namoFilePhysicalPath;
	imageUPath = namoFileUPath;
}else if(namoFileKind != null && "flash".equals(namoFileKind)){
	imagePhysicalPath = namoFlashPhysicalPath;
	imageUPath = namoFlashUPath;
}else{
	imagePhysicalPath = namoImagePhysicalPath;
	imageUPath = namoImageUPath;
}

*/

//imagePhysicalPath = "C:\\cejava\\htdocs\\uploadfile";
//imageUPath = "/uploadfile";

// 운영
imagePhysicalPath = "/DATA/UPLOAD/namoupload";
// 개발
//imagePhysicalPath = "/home/cello/upload/namoupload";
// 로컬
//imagePhysicalPath = "D:\\DATA\\UPLOAD\\namoupload";
imageUPath = "/";
%>