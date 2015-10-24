<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoney Corporation - Home</title>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">  

<style>
    body {
        margin-top: 50px;
        position: relative;
    }
</style>
    
</head>
<body style="background:url('http://localhost:8080/SafeMoneyCorp/images/background.jpg')">

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
</nav>

                <h1 style="position:absolute;left:365px; width:800px; z-index:0; color:#FFF">Welcome To SafeMoney Corporation</h1>
                <hr style="position:absolute;left:320px; top:55px; width:680px; height:30px;border-color:#7C7C7C">
                <p style="position:absolute;left:400px; top:100px; color:#FFF; font-size:24px" class="label label-success">Logging in as <i><strong> ${userName }</strong></i></p>
				<h4 style="position:absolute;left:400px; top:180px; color:#FFF">Your SiteKey: <i><strong class="label label-info" style="font-size:18px">${siteKey}</strong></i></h4>
				
				
				<form class="form-signin" name="loginForm" action="<c:url value='/j_spring_security_check' />" method='POST'>
				
				<h4 style="position:absolute;left:400px;top:250px;color:#FFF">Please Enter your Password</h4>
				<input type="hidden" id="j_username" name="j_username" value="${userName}">
				<input id="j_password" name="j_password"  type="password" class="form-control" style="position:absolute;width:200px;left:400px;top:300px;z-index:2" placeholder="Password" maxlength="50" autofocus="autofocus"/>
				
				<input name="login" type="submit" value="Log In" style="position:absolute;left:630px;top:300px;z-index:4;" class = "btn btn-primary"/>
				<h5 style="position:absolute;left:400px;top:340px;color:#FFF"><a href="<%=request.getContextPath() %>/shared/forgetpassword"><strong>Forgot Password?</strong></a></h5>
				
				<a href="<%= request.getContextPath() %>" style="position:absolute;width:300px;left:400px;top:400px;z-index:5" class = "btn btn-primary">Login with a different UserName?</a>
				<span style="position:absolute;width:300px;left:505px;top:565px;">&nbsp;</span>
				</form>
		
				
				
		


<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>
