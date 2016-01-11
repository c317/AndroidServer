<%@ page language="java" import="java.util.*,com.manageTeaching.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	ArrayList<Course> courses=(ArrayList<Course>)request.getAttribute("courses");
	ArrayList<Course> course1=new ArrayList<Course>();
	ArrayList<Course> course2=new ArrayList<Course>();
	ArrayList<Course> course3=new ArrayList<Course>();
	ArrayList<Course> course4=new ArrayList<Course>();
	ArrayList<Course> course5=new ArrayList<Course>();
	ArrayList<Course> course6=new ArrayList<Course>();
	ArrayList<Course> course7=new ArrayList<Course>();
	for(int i=0;i<courses.size();i++) {
		if(courses.get(i).getDay().equals("周一")){ 
			course1.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周二")){ 
			course2.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周三")){ 
			course3.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周四")){ 
			course4.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周五")){ 
			course5.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周六")){ 
			course6.add(courses.get(i));
		}
		if(courses.get(i).getDay().equals("周天")){ 
			course7.add(courses.get(i));
		}
	}
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
    <h2>学生课表查看</h2> <br>
     <table border="1">
    <thead  align="center">
 
    <tr>
		<td>课程名   </td>
		<td>节次           </td>
	 	<td>开始周次        </td>
	 	<td>结束周次        </td>
	 	<td>教室地点 </td>
	 	<td>教师姓名 </td>
	</tr>
	   <tr>
		<td>周一：   </td>
	</tr>
    <%
		for(int i=0;i<course1.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course1.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course1.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%= course1.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%= course1.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%= course1.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%= course1.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
	  <tr>
		<td>周二：   </td>
	</tr>
    <%
		for(int i=0;i<course2.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course2.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course2.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%= course2.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%= course2.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course2.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%= course2.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
	  <tr>
		<td>周三：   </td>
	</tr>
    <%
		for(int i=0;i<course3.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course3.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course3.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%=course3.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%=course3.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course3.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%=course3.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
	  <tr>
		<td>周四：   </td>
	</tr>
    <%
		for(int i=0;i<course4.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course4.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course4.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%=course4.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%=course4.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course4.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%=course4.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
		  <tr>
		<td>周五：   </td>
	</tr>
    <%
		for(int i=0;i<course5.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course5.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course5.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%=course5.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%=course5.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course5.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%=course5.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
			  <tr>
		<td>周六：   </td>
	</tr>
    <%
		for(int i=0;i<course6.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course6.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course6.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%=course6.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%=course6.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course6.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%=course6.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
			<td>周天：   </td>
	</tr>
    <%
		for(int i=0;i<course7.size();i++) {
		
	 %>
<tr>	 
	<td>
		<div align="center"><%=course7.get(i).getcName() %></div>
	</td>
	<td>
		<div align="center"><%= course7.get(i).getTime() %></div>
	</td>
	<td>
		<div align="center"><%=course7.get(i).getBeginTime() %></div>
	</td>
	<td>
		<div align="center"><%=course7.get(i).getEndTime() %></div>
	</td>
	<td>
		<div align="center"><%=course7.get(i).getrName() %></div>
	</td>
	<td>
		<div align="center"><%=course7.get(i).getTeacher() %></div>
	</td>
	</tr>
	<% } 
	%>
	</thead>
	</table>
	<a href="commonUser/homePage.jsp">返回主菜单</a>
  </body>
</html>
