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

    <!-- The styles -->
    <link href="../css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="../css/charisma-app.css" rel="stylesheet">
    <link href='../bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='../bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='../bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='../bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='../bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='../bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='../css/jquery.noty.css' rel='stylesheet'>
    <link href='../css/noty_theme_default.css' rel='stylesheet'>
    <link href='../css/elfinder.min.css' rel='stylesheet'>
    <link href='../css/elfinder.theme.css' rel='stylesheet'>
    <link href='../css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='../css/uploadify.css' rel='stylesheet'>
    <link href='../css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="../bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="../img/favicon.ico">
</head>
<body>


    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"> <img alt="Tmax Logo" src="../img/logo20.png" class="hidden-xs"/>
                <span>BRUCE</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> admin</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <!-- <li><a href="#">Profile</a></li>
                    <li class="divider"></li> -->
                    <li><a href="../Login.jsp">Logout</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">MENU</li>
                        <li><a class="ajax-link" href="New users.jsp"><i class="glyphicon glyphicon-bell"></i><span> New Users</span></a>
                        </li>
                        <li><a class="ajax-link" href="Users.jsp"><i class="glyphicon glyphicon-user"></i><span> User List</span></a>
                        </li>
                        <li><a class="ajax-link" href="Group of users.jsp"><i
                                    class="glyphicon glyphicon-comment"></i><span> User Group</span></a></li>
                        <li><a class="ajax-link" href="Files.jsp"><i class="glyphicon glyphicon-list-alt"></i><span> File List</span></a>
                        </li>
                        <li><a class="ajax-link" href="Group of files.jsp"><i class="glyphicon glyphicon-briefcase"></i><span> File Group</span></a>
                        </li>                        
                    </ul>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->

<%
	String viewName = request.getParameter("viewname");
	View view = new View(viewName);
	//get all files in this view
	FileDao fileDao = new FileDaoImpl(curUser.getLogin());
	List<File> filesInView = fileDao.getAllFilesInView(view);
	//get all roles with this view
	ViewDao viewDao = new ViewDaoImpl(curUser.getLogin());
	List<Role> rolesWithView = viewDao.getRolesWithView(view);
%>
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">Admin</a>
        </li>
        <li>
            <a href="#">File Group</a>
        </li>
        <li>
            <a href="#"><%=viewName %></a>
        </li>
    </ul>
</div>

	<button type="submit" class="btn btn-primary" onclick="backToGroupList()">
	    <i class="glyphicon glyphicon-arrow-left icon-white"></i> Return 
	</button>
<div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-list-alt"></i> Files</h2>

        <div class="box-icon">
            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
    <div class="box-content">
	<%
	if(filesInView.size()!=0)
	{
	%>
    <div class="alert alert-info">Following are the files included in this group.</div>
<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
	<thead>
    <tr>
        <th>Name</th>
		<th>Author</th>
		<th>Date of Publish</th>
		<th>File Type</th>
		<th>Views</th>
    </tr>
    </thead>
    <tbody>
<%
	for(File f : filesInView)
	{
		%>
		<tr>
	        <td class="center"><%=f.getNom() %></td>
			<td class="center"><%=f.getAutheur() %></td>
			<td class="center"><%=f.getDate_Pub() %></td>
			<%-- <td class="center"><%=f.getTag_View().getViewName() %></td> --%>
			<td class="center"><%=f.getType_File() %></td>
			<td class="center"><%=f.getVu() %></td>
	    </tr>
		<%
	}
%>
	</tbody>
</table>
		<%
	}else{
		%>
		<div class="alert alert-info">None of file in this group.</div>
		<%
	}
%> 
                </div>
            </div>
        </div>
        <!--/span-->   
<!-- </div> -->

<!-- <div class="row"> -->
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-comment"></i> User Group Related</h2>

        <div class="box-icon">
            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
    <div class="box-content">
<%
	if(rolesWithView.size()!=0)
	{
%>
    <div class="alert alert-info">Following are the file group(s) could be accessed by this user group.</div>
<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
	<thead>
    <tr>
        <th>File Group Name</th>
    </tr>
    </thead>
    <tbody>
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
	</tbody>
</table>
		<%
	}else{
		%>
		<div class="alert alert-info">This group can't be accessed by any user group.</div>
		<%
	}
%>   
                </div>
            </div>
        </div>
        <!--/span--> 
</div>

<div>
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

<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="../js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='../bower_components/moment/min/moment.min.js'></script>
<script src='../bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='../js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="../bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="../bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="../js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="../bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="../bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="../js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="../js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="../js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="../js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="../js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="../js/charisma.js"></script>


</body>
</html>