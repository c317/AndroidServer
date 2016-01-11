<%@ page language="java" import="java.util.*,com.manageTeaching.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	ArrayList<Course> courses=(ArrayList<Course>)request.getAttribute("courses");
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
       <h2>学生选课</h2> <br>
       
 <table border="1">
 	<thead  align="center">
	<tr>
		<td>课程编号            </td>
		<td>课程名            </td>
		<td>基本操作        </td>
	</tr>
	<tr>
	<%
		int tempCid=0;
		for(int i=0;i<courses.size();i++) {
		
	 %>
		<% tempCid=courses.get(i).getCid(); %>
	
	<td>
		<div align="center"><%=courses.get(i).getCid() %></div>
	</td>
	<td>
		<div align="center"><%=courses.get(i).getCname() %></div>
	</td>
	<td>
		<div align="center">
		<form action="DeleteClassAction.action" method="get">
		<input id="cid" name="cid" value=<%=tempCid %> type="hidden">
		<input type="submit" id="sub" value="删除" ></button>
		</form>
		</div>
	</td>
	</tr>
	<%} 
	%>
	</thead>
</table>
<a href="commonUser/homePage.jsp">返回主菜单</a>
  </body>
</html>
