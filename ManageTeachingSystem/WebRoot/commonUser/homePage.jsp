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
  <ul>
  	<li><a href="GetAllCourseAction.action">学生选课</a></li>
  	<li><a href="GetSelectedCourseAction.action">学生已选课程查看</a></li>
  	<li><a href="QueryScoreAction.action">学生成绩查看</li>
  	<li><a href="QueryTableAction.action">学生个人课表查看</li>
  	<li><a href="manager/manageClass.jsp">管理员课程安排</li>
  	<li><a href="GetUnManagedClassAction.action">管理员未排课程查看</li>
  	<!--  <li><a href="manager/manageCourse.jsp">管理员课程管理</li>-->
  	<li><a href="manager/manageScore.jsp">管理员成绩输入</li>
  	<li><a href="manager/allStudent.jsp">课程学生名单查看</li>
  	
  </ul>
  </body>
</html>
