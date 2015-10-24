<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Forget Password</title>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">  

<style>
    body {
        margin-top: 50px;
        position: relative;
    }
</style>
    
</head>



<body>
<jsp:include page="/pages/sidebar.jsp"></jsp:include>
<div id="page-wrapper">

<br>
<br><h4>Change Password</h4>
<br>

<form id="changePasswordForm" role="form"  class="form-horizontal" action="changePassword" method="post"  style="padding-left: 2%; padding-top: 2%;"> 

  
   
  <div class="form-group">
    <label for="inputPassword1c" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" id="changePassword" class="form-control" placeholder="Password"  name="changePassword">
    </div>
    <br>
    <br>
    <br>
     <label for="inputPassword2c" class="col-sm-2 control-label"> Confirm Password</label>
    <div class="col-sm-10">
      <input type="password" id="checkPassword" class="form-control" placeholder="Confirm Password" name="checkPassword">
    </div>
 	<br>
    <br>
    <br>
  <div class="form-group">
    
    <input type="submit" class="btn btn-lg btn-success btn-block" value="Change Your Password"/>
   
  </div>
  <%
				if(request.getAttribute("cpError") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${cpError} *</p>	
				<%}%>
  
  </div>
</form>
</div>
<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>


<script type="text/javascript">
$.validator.addMethod('passwordField', function( val, element ) {
    var regexp = new RegExp("/^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;");

    if (!regexp.test(val)) {
       return false;
    }
    return true;
}, "Please make your password strong by having atleast 8 characters<br>"+
	"[atleast 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character in [@#$%&]]");
$("#changePasswordForm").validate({
	rules: {
		changePassword: {
		required: true,
		maxlength: 15,
	},
	checkPassword: {
		required: true,
		maxlength: 15,
		
	},
	messages: {
		changePassword: "Please Enter a valid password (character range [8-15])",
		checkPassword: "Passwords doesn't match"	
			}
	}
	});
</script>

</body>
</html>