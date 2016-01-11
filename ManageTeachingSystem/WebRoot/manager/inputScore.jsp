<%@ page language="java" import="java.util.*,com.manageTeaching.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	ArrayList<Score> scores = (ArrayList<Score>) request
			.getAttribute("scores");
			int cid =(Integer)request.getAttribute("cid") ;
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
	<h2>请编辑该门课成绩</h2>
	
	<tr>
		<td>学生总数： <%
			int count = scores.size();
		%>
		<form action="SetStudentScoreAction.action" method="post">
		<td><input type="text" name="count" value="<%=count%>"></td>
		</td>
	</tr>
	<table border="1">
		<thead align="center">
			<tr>
				<td>学生编号</td>
				<td>学生姓名</td>
				<td>学生成绩</td>
			</tr>

			
				<%
					String name="name";
					String sid="sid";
					String score="score";
					for (int i = 0; i < scores.size(); i++) {
					System.out.println(sid+String.valueOf(i));
					System.out.println(name +String.valueOf(i));
					System.out.println(score+ String.valueOf(i));
				%>
				<tr>
					<td><input type="text" name="<%=sid+String.valueOf(i) %>"
						value="<%=scores.get(i).getSid()%>">
					</td>
					<td><input type="text" name="<%=name +String.valueOf(i) %>"
						value="<%=scores.get(i).getsName()%>">
					</td>
					<td><input type="text" name="<%=score+ String.valueOf(i) %>"
						value="<%=scores.get(i).getScore()%>">
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td><input type="submit" value="提交" /></td>
				</tr>
		</thead>
	</table>
	<a href="commonUser/homePage.jsp">返回主菜单</a>
</body>
</html>
