<%@ page language="java" import="java.util.*,com.manageTeaching.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	ArrayList<Score> scores=(ArrayList<Score>)request.getAttribute("scores");
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
    <h2> 学生成绩查询</h2><br>
    <table border="1">
    <thead  align="center">
    <tr>
		<td>学生姓名   </td>
		<td>课程名            </td>
	 	<td>分数        </td>
	</tr>
    <%
		for(int i=0;i<scores.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=scores.get(i).getsName() %></div>
	</td>
	<td>
		<div align="center"><%= scores.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= scores.get(i).getScore() %></div>
	</td>
	</tr>
	<% } 
	%>
	</thead>
	</table>
	<a href="commonUser/homePage.jsp">返回主菜单</a>
  </body>
</html>
