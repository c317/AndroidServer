<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<h2>未排课程</h2>
	<br>
	<form action="ManageClassAction.action" method="post">
	<tr><td>课程名称 <input name="cname" type="text"></td>
		 <td>教师姓名 <input name="tname" type="text"></td>
	</tr>
		<tr>
			<td>开始周次</td>
			<td><select id="class1" name="startTime">
					<option value="1周">1周</option>
					<option value="2周 ">2周</option>
					<option value="3周">3周</option>
					<option value="4周">4周</option>
					<option value="5周">5周</option>
					<option value="6周">6周</option>
					<option value="7周">7周</option>
					<option value="8周">8周</option>
					<option value="9周">9周</option>
					<option value="10周">10周</option>
					<option value="11周">11周</option>
					<option value="12周">12周</option>
			</select>
			</td>
			<td>结束周次</td>
			<td><select id="class2" name="endTime">
					<option value="6周">6周</option>
					<option value="7周">7周</option>
					<option value="8周">8周</option>
					<option value="9周">9周</option>
					<option value="10周">10周</option>
					<option value="11周">11周</option>
					<option value="12周">12周</option>
					<option value="13周">13周</option>
					<option value="14周">14周</option>
					<option value="15周">15周</option>
					<option value="16周">16周</option>
					<option value="17周">17周</option>
					<option value="18周">18周</option>
			</select>
			</td>
			<td>教师安排</td>
			<td><select id="class3" name="classRoom">
					<option value="A-101">A-101</option>
					<option value="A-102">A-102</option>
					<option value="A-103">A-103</option>
					<option value="A-104">A-104</option>
					<option value="A-105">A-105</option>
					<option value="A-106">A-106</option>
					<option value="A-201">A-201</option>
					<option value="A-202">A-202</option>
					<option value="A-203">A-203</option>
					<option value="A-204">A-204</option>
					<option value="A-205">A-205</option>
					<option value="A-206">A-206</option>
					<option value="A-301">A-301</option>
					<option value="A-302">A-302</option>
					<option value="A-303">A-303</option>
					<option value="A-304">A-304</option>
					<option value="A-305">A-305</option>
					<option value="A-306">A-306</option>
			</select>
			</td>
		</tr>

		<td>周次</td>
		<td><select id="class4" name="day">
				<option value="周一">周一</option>
				<option value="周二">周二</option>
				<option value="周三">周三</option>
				<option value="周四">周四</option>
				<option value="周五">周五</option>
				<option value="周六">周六</option>
				<option value="周天">周天</option>
		</select>
		</td>
		<td>节次</td>
		<td><select id="class5" name="class">
				<option value="第一大节">第一大节</option>
				<option value="第二大节">第二大节</option>
				<option value="第三大节">第三大节</option>
				<option value="第四大节">第四大节</option>
				<option value="第五大节">第五大节</option>
				<option value="第六大节">第六大节</option>
		</select>
		</td>
		<tr>
			<td><input type="submit" value="提交" /></td>
		</tr>
		<a href="commonUser/homePage.jsp">返回主菜单</a>
	</form>
</body>
</html>
