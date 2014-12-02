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
<title>New User Group</title>
</head>
<body>
<%
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<View> views = viewDao.getAllViews();
%>
<form action="/ProjetBD/NewUserGroup" method="post">
<table>
	<tr>
		<td>Please input new group's name : </td>
		<td>
			<input type="text" name="rolename" placeholder="Not repeat names used">
		</td>
	</tr>
	<tr>
		<td>Please select the file <br>group(s) to attach : </td>
		<td>
		<%
			for(View v : views)
			{
				%>
				<input type="checkbox" name="views" value=<%=v.getViewName() %>>
				<%=v.getViewName() %><br>
				<%
			}
		%>
		</td>
	</tr>
</table>
<input type="submit" value="Add">
</form>
</body>
</html>