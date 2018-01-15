<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>404 - 页面不存在</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>页面不存在.</h1></div>
		<div><a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a></div>
	</div>
</body>
</html>
