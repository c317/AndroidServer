<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>长江大学教学管理系统</title>
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
  <h1>欢迎进入教学管理系统</h1> <br>
	<form action="LoginAction.action" method="post">
		<tr>
			<td>
				用户名：<input name="sid" type="text">
			</td>
		</tr>
		<tr>
			<td>
				密码：<input name="password" type="password">
			</td>
		</tr>
		<input type="submit" id="sub" value="提交" >
		</button>
	</form>
  </body>
</html>
