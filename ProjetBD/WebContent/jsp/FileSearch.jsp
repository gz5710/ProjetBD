<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.DB.control.*" %>
<%@ page import="com.DB.dao.*" %>
<%@ page import="com.DB.model.*" %>
<%@ page import="com.DB.dao.SQLServer.*" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="curUser" class="com.DB.model.User" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search the files</title>
	<!-- link calendar resources -->
	<link rel="stylesheet" type="text/css" href="css/tcal.css" />
	<script type="text/javascript" src="js/tcal.js"></script>
</head>
<body>
<%
	FileDao fileDao = new FileDaoImpl(Util.adminLogin);
	List<String> fileTypes = fileDao.getAllFileTypes();
	List<String> authors = fileDao.getAllAuthors();
	//get result
	List<File> fileList = (List<File>)session.getAttribute("fileList");
	MultiCritere mc = (MultiCritere)session.getAttribute("MultiCritere");
%>
Hello, <%=curUser.getTitre() + " " + curUser.getUsername() %><br><br>

<%
	if(fileList==null && mc==null){//before searching
		%>
		<form action="/ProjetBD/SearchFile" method="post">
		<table>
			<tr>
				<td>File Type : 
				<select name="filetype">
					<option>ALL</option>
					<%
						for(String ft : fileTypes){
							%>
							<option><%=ft %></option>
							<%
						}
					%>
				</select>
				</td>
				<td>Key Word(s) : 
				<input type="text" name="keywords" placeholder="Please split by ',' ">
				</td>
			</tr>
			<tr>
				<td>Published : 
				<input type="text" name="fromdatepub" class="tcal" placeholder="From" readonly>
				</td>
				<td>
				<input type="text" name="todatepub" class="tcal" placeholder="To" readonly>
				</td>
			</tr>
			<tr>
				<td>Author    : 
				<select name="author">
					<option>ALL</option>
					<%
						for(String au : authors){
							%>
							<option><%=au %></option>
							<%
						}
					%>
				</select>
				</td>
				<td>
				<input type="submit" value="Search">
				</td>
			</tr>
		</table>
		</form>		
		<%
	}else{//after searching
		%>
		<form action="/ProjetBD/SearchFile" method="post">
		<table>
			<tr>
				<td>File Type : 
				<select name="filetype">
					<option>ALL</option>
					<%
						for(String ft : fileTypes){
							if(mc.getType_File().equals(ft)){
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
				<td>Key Word(s) : 
				<input type="text" name="keywords" placeholder="Please split by ',' " value=<%=mc.getKeyWords() %>>
				</td>
			</tr>
			<tr>
				<td>Published : 
				<input type="text" name="fromdatepub" class="tcal" placeholder="From" readonly
				<%
					if(mc.getFrom_Date_Pub() != null){
						%>
						value=<%=mc.getFrom_Date_Pub().toString() %>
						<%
					}
				%>
				>
				</td>
				<td>
				<input type="text" name="todatepub" class="tcal" placeholder="To" readonly
				<%
					if(mc.getTo_Date_Pub() != null){
						%>
						value=<%=mc.getTo_Date_Pub().toString() %>
						<%
					}
				%>
				>
				</td>
			</tr>
			<tr>
				<td>Author    : 
				<select name="author">
					<option>ALL</option>
					<%
						for(String au : authors){
							if(mc.getAuthor().equals(au)){
								%>
								<option selected><%=au %></option>
								<%
							}else{
								%>
								<option><%=au %></option>
								<%
							}
						}
					%>
				</select>
				</td>
				<td>
				<input type="submit" value="Search">
				</td>
			</tr>
		</table>
		</form>
			<%
				for(File f : fileList)
				{
					%>
				<table>
					<tr>
						<td>
						<a href="/ProjetBD/DisplayFile?fileid=<%=f.getId() %>">
						<%=f.getNom() %>
						</a>
						</td>
						<td><%=f.getType_File() %></td>
					</tr>
					<tr>
						<td><%=f.getAutheur() %></td>
						<td><%=f.getDate_Pub().toString() %></td>
					</tr>
					<tr>
						<td colspan="2">
						<%
							if(f.getDescription().length() > 100){
								%>
								<%=f.getDescription().substring(0, 100) + " ..." %>
								<%
							}else{
								%>
								<%=f.getDescription() + " ..." %>
								<%
							}
						%>
						</td>
					</tr>
				</table>
					<%
				}
			%>
		<%
	}
%>
</body>
</html>