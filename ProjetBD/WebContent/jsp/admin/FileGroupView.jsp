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
<title>File Group View</title>

<script type="text/javascript" src="/ProjetBD/jsp/js/FileGroup.js"></script>

</head>
<body>
<%
	String viewName = request.getParameter("viewname");
	View view = new View(viewName);
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<Role> rolesWithView = viewDao.getRolesWithView(view);
%>
<table>
	<thead>
		<h1>List of user groups who can access</h1>
	</thead>
	<%
		for(Role r : rolesWithView)
		{
			%>
			<tr>
				<td><%=r.getRole() %></td>
			</tr>
			<%
		}
	%>
</table>
<input type="button" value=" < Return " onclick="backToGroupList()">
</body>
</html>