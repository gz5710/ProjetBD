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
<title>User Group View</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/UserGroup.js"></script>

</head>
<body>
<%
	//the only parameter "rolename"
	String roleName = request.getParameter("rolename");
	Role role = new Role(roleName);
	RoleDao roleDao = new RoleDaoImpl(curUser.getLogin());
	//get list of users
	List<User> usersInRole = roleDao.getUsersInRole(role);
	//get list of views
	List<View> viewsWithRole = roleDao.getViewsWithRole(role);
%>
<table>
	<thead>
		<h1>Users in the group <%=role.getRole() %></h1>
	</thead>
	<tr>
		<td>Name</td>
		<td>Date of inscription</td>
		<td>Last login</td>
	</tr>
	<%
		for(User user : usersInRole)
		{
			%>
			<tr>
				<td><%=user.getUsername() %></td>
				<td><%=user.getDate_Inscription() %></td>
				<td><%=user.getDate_Dernier_Acces() %></td>
			</tr>
			<%
		}
	%>
</table>
<table>
	<thead>
		<h1>List of file group can be accessed</h1>
	</thead>
	<%
		for(View v : viewsWithRole)
		{
			%>
			<tr>
				<td><%=v.getViewName() %></td>
			</tr>
			<%
		}
	%>
</table>
<input type="button" value=" < Return " onclick="backToGroupList()">
</body>
</html>