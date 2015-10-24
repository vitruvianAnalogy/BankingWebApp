<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - User Default</title>
<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="alert alert-danger">
<h2>Sorry! Something went wrong during the transaction.</h2>
</div>
<br>
<br>
<div align="center">
<h3>Sorry for any inconvenience caused.</h3>
<br>
<h3>Please <i><a href="<c:url value="/j_spring_security_logout" />"><strong>Logout</strong></a></i> and try again.</h3>
</div>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.0.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/js/plugins/metisMenu/metisMenu.min.js"></script>



	<!-- Custom Theme JavaScript -->
	<script src="<%=request.getContextPath()%>/js/sb-admin-2.js"></script>
</body>
</html>