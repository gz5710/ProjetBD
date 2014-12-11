<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.DB.dao.*" %>
<%@page import="java.io.File"%>
<%@ page import="com.DB.dao.SQLServer.*" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="curUser" class="com.DB.model.User" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF</title>
</head>
<body>
<%
	String path = request.getParameter("path");
	File file = new File(path);
	//path = path.replace("\\", "/");
%>
<embed src="../../dossier/<%=file.getName() %>" Style="width:100%; height: 600px">
</body>
</html>