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
<title>File Edit</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/File.js"></script>

</head>
<body>
<%
	int fileid = Integer.parseInt(request.getParameter("fileid"));
	FileDao fileDao = new FileDaoImpl(curUser.getLogin());
	File file = fileDao.getFileById(fileid);
	List<String> fileTypes = fileDao.getAllFileTypes();
	
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<View> views = viewDao.getAllViews();
%>
<form action="/ProjetBD/EditFile" method="post">
<table>
	<thead>
		<h1>File : <%=file.getNom() %></h1>
	</thead>
	<tr>
		<td>Name : </td>
		<td><input type="text" name="name" value="<%=file.getNom() %>"></td>
	</tr>
	<tr>
		<td>Author : </td>
		<td><input type="text" name="author" value="<%=file.getAutheur() %>"></td>
	</tr>
	<tr>
		<td>Date of publish : </td>
		<td><%=file.getDate_Pub() %></td>
	</tr>
	<tr>
		<td>Descriptions : </td>
		<td><textarea rows="10" cols="20" name="descriptions"><%=file.getDescription() %></textarea></td>
	</tr>
	<tr>
		<td>Views : </td>
		<td><%=file.getVu() %> times</td>
	</tr>
	<tr>
		<td>File Group : </td>
		<td>
			<select name="filegroup">
				<%
					for(View v : views){
						if(v.equals(file.getTag_View()))
						{
							%>
							<option selected><%=v.getViewName() %></option>
							<%
						}else{
							%>
							<option><%=v.getViewName() %></option>
							<%
						}
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
						if(ft.equals(file.getType_File()))
						{
							%>
							<option selected><%=ft %></option>
							<%
						}else{
							%>
							<option><%=ft %></option>
							<%
						}
					}
				%>
			</select>
		</td>
	</tr>
</table>
<input type="hidden" name="fileid" value="<%=fileid %>">
<input type="button" value=" < Return " onclick="backToFileList()">
<input type="submit" value="Update">
</form>
</body>
</html>