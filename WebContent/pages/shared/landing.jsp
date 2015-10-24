
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - User Home</title>
</head>
<body>
<jsp:include page="/pages/sidebar.jsp"></jsp:include>
<div id="page-wrapper">
<h2>Welcome ${ sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username }</h2>
<br>
<br>
<h4>We appreciate your Business with us.</h4>
<br>
<p>Please use the Navigation Bar on the left to use our services.</p>
</div>
</body>
</html>
