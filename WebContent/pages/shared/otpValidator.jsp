<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - OTP Validator</title>
<!-- jQuery & jQuery UI + theme (required) -->
	<link href="http://code.jquery.com/ui/1.9.0/themes/ui-darkness/jquery-ui.css" rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.js"></script>
	<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.min.js"></script>

	<!-- keyboard widget css & script (required) -->
	<link  href="/css/keyboard.css" rel="stylesheet">
		<!-- <script type="text/javascript" src="/js/jquery.keyboard.js"></script> -->

	
<!-- <link href="css/bootstrap.css" rel="stylesheet" media="screen">  -->

<!-- <style>
    body {
        margin-top: 50px;
        position: relative;
    }
</style> -->
    <script type="text/javascript">
		$(function(){
		<%System.out.println("inside js"); %>
			$('#otpValidatorText').keyboard();
		});
	</script>
  
</head>



<body>
<jsp:include page="/pages/sidebar.jsp"></jsp:include>
<div id="page-wrapper">

<br>
<br><h4>Forget Password</h4>
<br>

<form class="form-horizontal" role="form" action="otpValidator" method="post"  style="padding-left: 2%; padding-top: 2%;"> 
	
  <div class="form-group">
    <label for="otpValidatorLabel" class="col-sm-2 control-label">Your OTP Code</label>
    <div class="col-sm-10">
    <div id="wrap"> <!-- wrapper only needed to center the input -->

		<!-- keyboard input -->
		<input id="otpValidatorText" type="text" name="otpValidatorText">

	</div> <!-- End wrapper -->
    
     
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Submit OTP Code	</button>
    </div>
    <br>
    <br>
    <br>
    
     <%
				if(request.getAttribute("otpError") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${otpError} *</p>	
				<%}%>

</div>
<script src="${pageContext.request.contextPath}/js/jquery.keyboard.js"></script>
</body>
</html>