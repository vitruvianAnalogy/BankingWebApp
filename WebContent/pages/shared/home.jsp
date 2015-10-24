
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				<img alt="SafeMoneyVault" src="./images/vault.jpg" style="position:absolute;left:355px; top:100px" width="600px" height="230px">
				
				<form:form id ="loginform" name="loginForm" target="_self" method="post" commandName="loginform" action="userNameLogin" style="margin:0px" class="form-inline">
				
				<h4 style="position:absolute;left:505px;top:375px;color:#FFF">Please Enter your UserName</h4>
				<input name="userName" autofocus="autofocus" type="text" class="form-control" style="position:absolute;width:200px;left:505px;top:415px;z-index:2" placeholder="Username" maxlength="30"/>
				
				<%
				if (request.getAttribute("message") != null) {
				%>
				<p class="label label-success" style="font-size:13px">${message }</p>
				<br>
				<%
				}
				%>
				<%
				if (request.getAttribute("error") != null) {
				%>
				<p class="label label-warning" style="font-size:13px">${error }</p>
				<br>
				<%
				}
				%>
				<input name="login" type="submit" value="Log In" style="position:absolute;left:725px;top:415px;z-index:4;" class = "btn btn-primary" autofocus="autofocus"/>
				
				<span style="position:absolute;width:300px;left:505px;top:545px;">&nbsp;</span>
				<h5 style="position:absolute;left:505px;top:500px;color:#FFF">Don't have an account?</h5>
				
				</form:form>
				
				<form method="post" action="signUp">
					<input name="signUp" type="submit" value="Sign Up" style="position:absolute;width:300px;left:505px;top:527px;z-index:5;" class = "btn btn-primary"/>
					<span style="position:absolute;width:300px;left:505px;top:565px;">&nbsp;</span>
				</form>
			<%
				if(request.getAttribute("InvalidUserName") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${InvalidUserName} *</p>	
				<%}%>
				<%
				if(request.getAttribute("authError") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${authError} *</p>	
				<%}%>
				<%
				if(request.getAttribute("error") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${error} *</p>	
				<%}%>
				
				
		


<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>