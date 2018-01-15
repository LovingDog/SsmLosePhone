<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
           + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>


<html>
<head>
<title>file upload test</title>
</head>
<body>

	<form method="post" action="<%=path %>fileUploadController/uploadFile"
		enctype="multipart/form-data">
		文件名: <input type="text" name="fileName" /><br />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="file"
			name="clientFile" /><br /> <input type="submit" value="上传文件 " />
	</form>
	
	
</body>
</html>
