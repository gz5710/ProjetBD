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
<title>Group of files</title>
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

<table>
	<thead>
		<h1>File Groups</h1>
	</thead>
	<tr>
		<td>Name</td>
		<td>Delete</td>
	</tr>
<%
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<View> views = viewDao.getAllViews();
	for(View v : views)
	{
		%>
		<tr>
			<td>
				<a href="FileGroupView.jsp?viewname=<%=v.getViewName()%>">
					<%=v.getViewName() %>
				</a>
			</td>
			<form action="/ProjetBD/DeleteFileGroup" method="post">
				<td>
					<input type="hidden" name="viewname" value="<%=v.getViewName() %>">
					<input type="submit" value="Delete">
				</td>
			</form>
		</tr>
		<%
	}
%>
</table>
<form action="/ProjetBD/NewFileGroup" method="post">
	<input type="text" name="viewname" placeholder="New File Group Name">
	<input type="submit" value="Add">
</form>
</body>
</html>