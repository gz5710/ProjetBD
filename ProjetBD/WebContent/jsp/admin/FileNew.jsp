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
<title>Add a file</title>
</head>
<body>
<%
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<View> views = viewDao.getAllViews();
	FileDao fileDao = new FileDaoImpl(curUser.getLogin());
	List<String> fileTypes = fileDao.getAllFileTypes();
%>
<form action="/ProjetBD/NewFile" method="post" enctype="multipart/form-data">
<table>
	<thead>
		<h1>Upload a new file</h1>
	</thead>
	<tr>
		<td>Name : </td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>Author : </td>
		<td><input type="text" name="author"></td>
	</tr>
	<tr>
		<td>Descriptions : </td>
		<td><textarea rows="10" cols="20" name="descriptions"></textarea></td>
	</tr>
	<tr>
		<td>File Group : </td>
		<td>
			<select name="filegroup">
				<%
					for(View v : views){
						%>
						<option><%=v.getViewName() %></option>
						<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td>File Type : </td>
		<td>
			<select name="filetype">
				<%
					for(String ft : fileTypes){
						%>
						<option><%=ft %></option>
						<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td>File : </td>
		<td><input type="file" name="file"></td>
	</tr>
</table>
<input type="submit" value="Upload">
</form>
</body>
</html>