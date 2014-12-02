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
<title>File View</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/File.js"></script>

</head>
</head>
<body>
<%
	int fileid = Integer.parseInt(request.getParameter("fileid"));
	FileDao fileDao = new FileDaoImpl(curUser.getLogin());
	File file = fileDao.getFileById(fileid);
%>
<table>
	<thead>
		<h1>File : <%=file.getNom() %></h1>
	</thead>
	<tr>
		<td>Name : </td>
		<td><%=file.getNom() %></td>
	</tr>
	<tr>
		<td>Author : </td>
		<td><%=file.getAutheur() %></td>
	</tr>
	<tr>
		<td>Date of publish : </td>
		<td><%=file.getDate_Pub() %></td>
	</tr>
	<tr>
		<td>Descriptions : </td>
		<td><%=file.getDescription() %></td>
	</tr>
	<tr>
		<td>Views : </td>
		<td><%=file.getVu() %> times</td>
	</tr>
	<tr>
		<td>File Group : </td>
		<td><%=file.getTag_View().getViewName() %></td>
	</tr>
	<tr>
		<td>File Type : </td>
		<td><%=file.getType_File() %></td>
	</tr>
</table>
<input type="button" value=" < Return " onclick="backToFileList()">
</body>
</html>