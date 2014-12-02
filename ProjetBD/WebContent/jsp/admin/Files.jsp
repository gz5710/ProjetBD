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
<title>Files</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/File.js"></script>

</head>
<body>
<table>
	<tr>
		<td><a href="Files.jsp">Files</a></td>
		<td><a href="Users.jsp">Users</a></td>
		<td><a href="Group of users.jsp">Group of users</a></td>
		<td><a href="Group of files.jsp">Group of files</a></td>
		<td><a href="New users.jsp">New users</a></td>
	</tr>
</table>
<%
	FileDao fileDao = new FileDaoImpl(curUser.getLogin());
	List<File> files = fileDao.getAllFiles();
%>
<table>
	<thead>
		<h1>File List</h1>
	</thead>
	<tr>
		<td>Name</td>
		<td>Author</td>
		<td>Date of Publish</td>
		<td>File Group</td>
		<td>File Type</td>
		<td>Views</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%
		for(File f : files)
		{
			%>
			<tr>
				<td>
					<a href="FileView.jsp?fileid=<%=f.getId()%>">
					<%=f.getNom() %>
					</a>
				</td>
				<td><%=f.getAutheur() %></td>
				<td><%=f.getDate_Pub() %></td>
				<td><%=f.getTag_View().getViewName() %></td>
				<td><%=f.getType_File() %></td>
				<td><%=f.getVu() %></td>
				<td>
					<a href="FileEdit.jsp?fileid=<%=f.getId()%>">
						Edit
					</a>
				</td>
				<form action="/ProjetBD/DeleteFile" method="post">
					<td>
						<input type="hidden" name="fileid" value="<%=f.getId() %>">
						<input type="hidden" name="chemin" value="<%=f.getChemin() %>">
						<input type="submit" value="Delete">
					</td>
				</form>
			</tr>
			<%
		}
	%>
</table>
<input type="button" value="New File" onclick="goToCreateNewPage()">
</body>
</html>