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
	<!-- END link calendar resources -->
	
	<!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
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
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"> <img alt="Tmax Logo" src="img/logo20.png" class="hidden-xs"/>
                <span><%=curUser.getUsername() %></span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> <%=curUser.getUsername() %></span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <!-- <li><a href="#">Profile</a></li>
                    <li class="divider"></li> -->
                    <li><a href="Login.jsp">Logout</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>
	</div>
        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">Hello, <%=curUser.getTitre() + " " + curUser.getUsername() %></a>
        </li>
    </ul>
</div>

<div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-briefcase"></i> File Search</h2>

        <div class="box-icon">
            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
    <div class="box-content">
    
<%
	if(fileList==null && mc==null){//before searching
		%>
		<form action="/ProjetBD/SearchFile" method="post">
		<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
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
			</tr>
			<tr>
				<td>Published : 
				<input type="text" name="fromdatepub" class="tcal" placeholder="From" readonly>
				</td>
				<td>
				<input type="text" name="todatepub" class="tcal" placeholder="To" readonly>
				</td>
				<td>
				<button type="submit" class="btn btn-primary">
				    <i class="glyphicon glyphicon-search icon-white"></i> Search
				</button>
				</td>
			</tr>
		</table>
		</form>		
		<%
	}else{//after searching
		%>
		<form action="/ProjetBD/SearchFile" method="post">
		<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
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
				<input type="text" name="keywords" placeholder="Please split by ',' " value="<%=mc.getKeyWords() %>">
				</td>
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
				<td>
				<button type="submit" class="btn btn-primary">
				    <i class="glyphicon glyphicon-search icon-white"></i> Search
				</button>
				</td>
			</tr>
		</table>
		</form>
    <div class="alert alert-info"> About <%=fileList.size() %> result(s).</div>
			<%
				for(File f : fileList)
				{
					%>
				<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
					<tr>
						<td>
						<a href="/ProjetBD/DisplayFile?fileid=<%=f.getId() %>">
						<%=f.getNom() %>
						</a>
						</td>
						<td><%=f.getType_File() %></td>
						<td><%=f.getAutheur() %></td>
						<td><%=f.getDate_Pub().toString() %></td>
					</tr>
					<tr>
						<td colspan="4">
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
    
    
                </div>
            </div>
        </div>
        <!--/span-->

    <hr>

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://www.gongzhe.fr" target="_blank">Bruce GONG</a> 2012 - 2014</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://www.gongzhe.fr">Bruce GONG</a></p>
    </footer>

</div>
</div>
</div><!--/.fluid-container-->

<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>


</body>
</html>