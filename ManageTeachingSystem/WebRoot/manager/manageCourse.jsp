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
     <br>
     <form action="ManageCourseAction.action" method="post">
		<tr>
			<td>
				请输入课程名：<input name="cname" type="text">
			</td>
		</tr>
		<tr>
			<td>
				请输入课程号：<input name="cname" type="text">
			</td>
		</tr>
		<input type="submit" id="sub" name="fun1" value="增加" >
		<input type="submit" id="sub" name="fun2" value="删除" >
		<input type="submit" id="sub" name="fun3" value="修改" >
		</button>
	</form>
  </body>
</html>
