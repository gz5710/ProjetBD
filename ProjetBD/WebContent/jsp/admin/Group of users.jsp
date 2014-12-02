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
<title>Group of users</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/UserGroup.js"></script>

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
		<h1>User Groups</h1>
	</thead>
	<tr>
		<td>Name</td>
		<td>Date of creation</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
<%
	RoleDao roleDao = new RoleDaoImpl(curUser.getLogin());
	List<Role> roles = roleDao.getAllRoles();
	for(Role r : roles)
	{
		%>
		<tr>
			<td>
				<a href="UserGroupView.jsp?rolename=<%=r.getRole()%>">
					<%=r.getRole() %>
				</a>
			</td>
			<td><%=r.getCreateTime() %></td>
				<td>
					<a href="UserGroupEdit.jsp?rolename=<%=r.getRole()%>">Edit</a>
				</td>
			<form action="/ProjetBD/DeleteUserGroup" method="post">
				<td>
					<input type="hidden" name="rolename" value="<%=r.getRole() %>">
					<input type="submit" value="Delete">
				</td>
			</form>
		</tr>
		<%
	}
%>
</table>
<input type="button" value="New User Group" onclick="goToCreateNewPage()">
</body>
</html>