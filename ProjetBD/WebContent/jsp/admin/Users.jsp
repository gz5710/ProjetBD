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
<title>Users</title>
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
<!-- delete user / change user group -->
<table>
	<thead>
		<h1>Users</h1>
	</thead>
	<tr>
		<td>Name</td>
		<td>Date of inscription</td>
		<td>Edit group</td>
		<td>Delete</td>
	</tr>
<%
	UserDao userDao = new UserDaoImpl(curUser.getLogin());
	List<User> users = userDao.getValidUsers();
	for(User user : users)
	{
		%>
		<tr>
			<td>
				<a href="UserView.jsp?username=<%=user.getUsername()%>">
				<%=user.getUsername() %>
				</a>
			</td>
			<td><%=user.getDate_Inscription() %></td>
				<td>
					<a href="UserChangeGroup.jsp?username=<%=user.getUsername()%>">Edit Group</a>
				</td>
			<form action="/ProjetBD/DeleteUser" method="post">
				<td>
					<input type="hidden" name="Id" value="<%=user.getId() %>">
					<input type="hidden" name="Username" value="<%=user.getUsername() %>">
					<input type="submit" value="Delete">
				</td>
			</form>
		</tr>
		<%
	}
%>
</table>
</body>
</html>