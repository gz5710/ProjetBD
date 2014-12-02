<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.DB.dao.*" %>
<%@ page import="com.DB.model.*" %>
<%@ page import="com.DB.dao.SQLServer.*" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="curUser" class="com.DB.model.User" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User View</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/User.js"></script>

</head>
<body>
<%
	String userName = request.getParameter("username");
	UserDao userDao = new UserDaoImpl(curUser.getLogin());
	User user = userDao.getUserByName(userName);
	LogDao logDao = new LogDaoImpl(curUser.getLogin());
	
%>
<table>
	<thead>
		<h1><%=user.getTitre() + " " + user.getUsername() %></h1>
	</thead>
	<tr>
		<td>Email : </td>
		<td><%=user.getEmail() %></td>
	</tr>
	<tr>
		<td>Date of birth : </td>
		<td><%=user.getDOB() %></td>
	</tr>
	<tr>
		<td>Date of inscription</td>
		<td><%=user.getDate_Inscription() %></td>
	</tr>
	<tr>
		<td>Date of last login</td>
		<td><%=user.getDate_Dernier_Acces() %></td>
	</tr>
	<tr>
		<td>Frequency of login : </td>
		<td><%=logDao.getLogsCount(user.getId()) %> times</td>
	</tr>
</table>

<input type="button" value=" < Return " onclick="backToFileList()">
</body>
</html>