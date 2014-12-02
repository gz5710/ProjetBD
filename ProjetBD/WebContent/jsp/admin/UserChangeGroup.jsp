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
<title>Change Group</title>
</head>
<body>
<% 
	String username = request.getParameter("username"); 
	RoleDao roleDao = new RoleDaoImpl(curUser.getLogin());
	Role role = roleDao.getRole(username);
	List<Role> roles = roleDao.getAllRoles();
	if(role == null)
	{
		role = new Role("NONE");
	}
%>
Current Group : <%=role.getRole() %>
<form action="/ProjetBD/ChangeUserGroup" method="post">
<table>
	<tr>
		<td>Select group : </td>
		<td>
		<select name="ToGroup">
		<%
			for(Role r : roles)
			{
				if(r.equals(role))
				{
					%>
					<option selected value=<%=r.getRole() %> >
						<%=r.getRole() %>
					</option>
					<%
				}else{
					%>
					<option value=<%=r.getRole() %> >
						<%=r.getRole() %>
					</option>
					<%
				}
			}
		%>
		</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
				<input type="hidden" name="UserName" value="<%=username %>">
				<input type="hidden" name="FromGroup" value="<%=role.getRole() %>">
				<input type="submit" value="OK">
		</td>
	</tr>
</table>
</form>
</body>
</html>