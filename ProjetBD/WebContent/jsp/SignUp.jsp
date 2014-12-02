<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
	<!-- link calendar resources -->
	<link rel="stylesheet" type="text/css" href="css/tcal.css" />
	<script type="text/javascript" src="js/tcal.js"></script> 
</head>
<body>
<form action="../Inscription" method="post">
Username: * <input type="text" name="Username"><br><br>
Password: * <input type="password" name="Password"><br><br>
Title: * <select name="Titre">
			<option value="Mr">Mr</option>
			<option value="Miss">Miss</option>
			<option value="Mrs">Mrs</option>
		</select><br><br>
Email: * <input type="text" name="Email"><br><br>
Date of birth: * <input type="text" name="DOB" class="tcal" readonly><br><br>

<input type="submit" value="Sign up">
</form><br>
Already signed up ? <a href="Login.jsp">Sign in</a>
</body>
</html>