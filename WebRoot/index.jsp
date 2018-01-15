<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    	<h5><a href="<%=basePath%>user/findAllUser">进入用户管理页</a></h5>
    	<a href="javascript:;" class="a-upload">
　　<input class="" type="file" name="file" id="file" required="required" onclick="test()">上传
</a>

<script type="text/javascript">
function test(){
$('#file').on('change', function () {
 var $this = $(this);
 var formData = new FormData();
 formData.append('file', $('#file')[0].files[0]);
 var fileName = $('#file')[0].files[0].name;
 var fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
 var fileSize = $('#file')[0].files[0].size;
 if (fileType != 'jpg' && fileType != 'png' && fileType != 'gif') {
  alert("请上传.jpg、.png、.gif格式的图片！");
  return;
 }
 if (fileSize > 300 * 1024) {
  alert("请上传大小小于300KB的图片！");
  return;
 }
 $.ajax({
  url: '/admin/upload',
  type: 'POST',
  data: formData,
  cache: false,
  processData: false,
  contentType: false
 }).done(function (result) {
  if (result != '') {
   $this.closest('div').append('<div class="img-preview"><img src="' + result + '"/></div>');
  } else {
   alert("请上传.jpg、.png、.gif格式的图片！");
  }
 }).fail(function () {
  alert("图片上传失败！");
 });
});

}
</script>
  </body>
  
</html>
