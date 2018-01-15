<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>系统内部错误.</h1></div>
		<div class="errorMessage">
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
		</div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
