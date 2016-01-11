<%@ page language="java" import="java.util.*,com.manageTeaching.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	
	ArrayList<Student> students=(ArrayList<Student>)request.getAttribute("students");
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
    <h2>该课程所有学生</h2><br>
    <table border="1">
 	<thead  align="center">
	<tr>
		<td>学生编号            </td>
	 	<td>学生姓名        </td>
		<td>学生年龄         </td>
		<td>所属年级      </td>
	</tr>
	
	<%
		for(int i=0;i<students.size();i++) {
		
	 %>
	 <tr>
	<td>
		<div align="center"><%=students.get(i).getSid() %></div>
	</td>
	<td>
		<div align="center"><%=students.get(i).getSname() %></div>
	</td>
	<td>
		<div align="center"><%=students.get(i).getSage() %></div>
	</td>
	<td>
		<div align="center"><%=students.get(i).getGrade() %></div>
	</td>
	</tr>
	<%} 
	%>
	</thead>
</table>
<a href="commonUser/homePage.jsp">返回主菜单</a>
  </body>
</html>
