<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SafeMoneyCorp - New User Signup</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<link href="css/jquery-ui.css" rel="stylesheet">

<link href="css/jquery-ui.min.css" rel="stylesheet">

<link href="css/jquery-ui.theme.css" rel="stylesheet">

<link href="css/jquery-ui.theme.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="<%=request.getContextPath() %>/js/jquery-1.11.0.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
	
	<style type="text/css">
	#signUpForm label.error {
		font-weight: 500;
		color:red;
	}
	
	</style>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<div>
					<br> <br> <br>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title">
							<strong>New User - Sign Up</strong>
						</h2>
					</div>


					<div class="panel-body">
					<form:form id="signUpForm" role="form" method="POST" commandName="signUpForm" action="userSignUp">
						<fieldset>
						<div class="row">
									<div class="col-md-10 col-md-offset-1">
										<c:if test="${nameOrEmail == 'true' }">
											<div style="color:red">
												Please Enter a different UserName or Email ID.
											</div>
										</c:if>
											<div>
												<div class="radio">
													<label> <input type="radio" name="userType"
														id="userType" value="indCust" checked autofocus tabindex="1">Individual
														Customer
													</label>&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
														type="radio" name="userType" id="userType"
														value="merchantOrg" tabindex="2">Merchant Org
													</label>
												</div>
												<div>
													<br>
													<h4>Personal Information</h4>
													<br>
												</div>
												
										<div class="col-md-5">
												<div class="form-group" style="color:red">
													<input class="form-control" placeholder="First Name" maxlength="25" id="firstName"
														name="firstName" tabindex="3" value="${signUpForm.firstName }" />
														<form:errors path="firstName" />
												</div>




												
												<div class="form-group">
													<input id="contactNo" class="form-control"
														placeholder="Contact No" name="contactNo" tabindex="5" maxlength="10" value="${signUpForm.contactNo }" >
												</div>
												<div class="form-group">
													<input id="address1" class="form-control"
														placeholder="Address Line 1" name="address1" tabindex="7" value="${signUpForm.address1 }" >
												</div>
												<div class="form-group">
													<input id="city" class="form-control"
														placeholder="City" name="city" tabindex="9" value="${signUpForm.city }" >
												</div>
												<div class="form-group">
												
													
													<input id="dateOfBirth" class="form-control datepicker"
														placeholder="Date of Birth" name="dateOfBirth" tabindex="12" >
														<script type="text/javascript">
															$('.datepicker').datepicker({
	 														autoclose: true,
	    													format: 'mm/dd/yyyy',
	    													minDate: "-50Y",
	    													changeYear: true,
	   														maxDate: "0D",
	   													 yearRange:'-30:+0'
															});
 														</script>
												</div>
												<div class="form-group">
													<input id="ssn" class="form-control"
														placeholder="Social Security" name="ssn" maxlength="9" tabindex="14" value="${signUpForm.ssn }" >
												</div>
												<div>
													<br>
												</div>
												<div>
													<br>
												</div>
												
												
										</div>
										<div class="col-md-5 col-md-offset-1">
											
											<div class="form-group">
												<input class="form-control" placeholder="Last Name" id="lastName"
													name="lastName" tabindex="4" value="${signUpForm.lastName }" >
											</div>
											<div class="form-group">
													<input id="emailId" class="form-control" 
														placeholder="Email ID" name="emailId" tabindex="6" value="${signUpForm.emailId }" >
											</div>
											<div class="form-group">
													<input id="address2" class="form-control"
														placeholder="Address Line 2" name="address2" tabindex="8" value="${signUpForm.address2}">
											</div>
											<div class="col-md-5 col-md-offset-0">
												<div class="form-group">
													<input id="state" class="form-control"
														placeholder="State" name="state" maxlength="2" tabindex="10" value="${signUpForm.state }" >
												</div>
											</div>
											<div class="col-md-5 col-md-offset-0">
												<div class="form-group">
													<input id="zip" class="form-control"
														placeholder="Zip" name="zip" maxlength="5" tabindex="11" value="${signUpForm.zip }" >
												</div>
												<br>
											</div>
											
											<div class="form-group">
											
													<input id="age" class="form-control"
														placeholder="Age" name="age" maxlength="3" tabindex="13" value="${signUpForm.age }">
											</div>
											<div>
											<br>
											
											</div>
											

										</div>
									</div>
									</div>
							
							
						</div>
						<div class="row">
							
									<div class="col-md-10 col-md-offset-1">
										
											<div>
												
												<div>
													<br>
													<h4><strong>Login Information</strong></h4>
													<br>
												</div>
												
												<div class="form-group">
													<input class="form-control" placeholder="User Name" style="width:25%" id="userName"
														name="userName" tabindex="15" value="${signUpForm.userName }">
												</div>
											<div class="col-md-5">
												<div class="form-group">
													<input id="password" class="form-control" type="password"
														placeholder="Password" name="password" tabindex="16">
												</div>
												<div class="form-group">
													<input id="secQuestion1" class="form-control"
														placeholder="Security Question 1" name="secQuestion1" value="${signUpForm.secQuestion1 }" tabindex="18">
												</div>
												<div class="form-group">
													<input id="secQuestion2" class="form-control"
														placeholder="Security Question 2" name="secQuestion2" value="${signUpForm.secQuestion2 }" tabindex="20">
												</div>
												<div class="form-group">
												
													<input id="secQuestion3" class="form-control"
														placeholder="Security Question 3" name="secQuestion3" value="${signUpForm.secQuestion3 }" tabindex="22">
												</div>
												<div class="form-group">
													<input id="siteKey" class="form-control"
														placeholder="NetBanking SiteKey" name="siteKey" value="${signUpForm.siteKey }" tabindex="24">
												</div>
												<div>
													<br>
												</div>
												<div>
													<br>
												</div>
												
												<div>

												<!-- Change this to a button or input when using this as a form -->
												<input type="submit"
													class="btn btn-lg btn-success btn-block" tabindex="25" value="Sign Up"/>

											</div>
										</div>
										<div class="col-md-5 col-md-offset-1 ">
											
											
											<div class="form-group">
											
													<input id="passwordConfirm" class="form-control" type="password"
														placeholder="Confirm Password" name="passwordConfirm" tabindex="17">
														
											</div>
										
											
											
											<div class="form-group">
													<input id="secAnswer1" class="form-control"
														placeholder="Security Answer 1" name="secAnswer1" value="${signUpForm.secAnswer1}" tabindex="19">
												</div>
												<div class="form-group">
													<input id="secAnswer2" class="form-control"
														placeholder="Security Answer 2" name="secAnswer2" value="${signUpForm.secAnswer2}" tabindex="21">
												</div>
												<div class="form-group">
												
													<input id="secAnswer3" class="form-control"
														placeholder="Security Answer 3" name="secAnswer3" value="${signUpForm.secAnswer3}" tabindex="23">
												</div>
											<div>
											<br>
											
											</div>
											

										</div>
									</div>
									</div>
						</div>
						</fieldset>
						</form:form>
					</div>
								
				</div>
			</div>
		</div>
		
	</div> 
	
	<script type="text/javascript">

	jQuery.validator.addMethod( 'passwordMatch', function(value, element) 
			{
	    
			    var password = $("#password").val();
			    var passwordConfirm = $("#passwordConfirm").val();
			 
			    if (password != passwordConfirm ) 
			    {
			        return false;
			    } 
			    else 
			    {
			        return true;
			    }	
	    	}, "Your Passwords Must Match");

	$.validator.addMethod('alphabetsOnly', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type alphabets only");
	$.validator.addMethod('addressField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9,. _;-]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [,][.][_][;][-]");
	
	$.validator.addMethod('userNameField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9._]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [.][_]");
		
	$.validator.addMethod('passwordField', function( val, element ) {
	    var regexp = new RegExp("/^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please make your password strong by having atleast 8 characters<br>"+
		"[atleast 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character in [@#$%&]]");
	
	$.validator.addMethod('questionField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9,._;-?]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [,][.][_][;][-][?]");
	
	$.validator.addMethod('numbersOnly', function( val, element ) {
	    var regexp = new RegExp("[1-9][0-9]*+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type numbers only");
	
	$("#signUpForm").validate({
		rules: {
			emailId: {
				required: true,
				email: true
			},
			firstName: {
				required: true,
				alphabetsOnly: true,
				maxlength: 25
			},
			lastName: {
				required: true,
				alphabetsOnly: true,
				maxlength: 25
			},
			contactNo: {
				required: true,
				phoneUS: true
			},
			address1: {
				required: true,
				addressField: true,
				maxlength: 50
			},
			address2: {
				addressField: true,
				maxlength: 50
			},
			city: {
				required: true,
				alphabetsOnly: true,
				maxlength: 15
			},
			state: {
				required: true,
				alphabetsOnly: true,
				minlength: 2,
				maxlength: 2
			},
			zip: {
				required: true,
				digits: true,
		   		minlength: 5,
		    	maxlength: 5
			},
			dateOfBirth: {
				required: true,
				date: true
			},
			age: {
				required: true,
				digits: true,
				minlength: 2,
				maxlength: 3
			},
			
			ssn:{
				required: true,
				numbersOnly: true,
				maxlength:10
				
			}, 
			userName: {
				required: true,
				userNameField: true,
				minlength: 5,
				maxlength: 15
			},
			password: {
				required: true,
				maxlength: 15
			},
			passwordConfirm: {
				required: true,
				maxlength: 15,
				passwordMatch:true
			},
			secQuestion1: {
				required: true,
				maxlength: 200,
				questionField: true
			},
			secQuestion2: {
				required: true,
				maxlength: 200,
				questionField: true
			},
			secQuestion3: {
				required: true,
				maxlength: 200,
				questionField: true
			},
			secAnswer1: {
				required: true,
				maxlength: 25,
				questionField: true
			},
			secAnswer2: {
				required: true,
				maxlength: 25,
				questionField: true
			},
			secAnswer3: {
				required: true,
				maxlength: 25,
				questionField: true
			},
			siteKey: {
				required: true,
				minlength: 5,
				maxlength: 20
			},
		messages: {
			emailId: "Please Enter a valid Email ID",
			firstName: "Please Enter your First Name (must be less than 25 characters)",
			lastName: "Please Enter your Last Name (must be less than 25 characters)",
			contactNo: "Please Enter valid contact number",
			address1: "Please Enter your address (must be less than 50 characters)",
			address2: "Please Enter less than 50 characters",
			city: "Please Enter your city (must be less than 12 characters)",
			state: "Please Enter a valid state (must be 2 charcters)",
			zip: "Please Enter a valid 5 digit ZIP",
			dateOfBirth: "Please Enter a valid date in (MM/DD/YYYY) format",
			age: "Please Enter a valid date (maximum of 3 digits)",
			userName: "Please Enter a vlaid user name (character range [5-15])",
/* 			password: "Please Enter a vlaid password (character range [8-15])",
			passwordConfirm: "Passwords doesn't match", */
			secQuestion1: "Please Enter a valid Security question (character range [1-200])",
			secQuestion2: "Please Enter a valid Security question (character range [1-200])",
			secQuestion3: "Please Enter a valid Security question (character range [1-200])",
			secAnswer1: "Please Enter a valid Security answer (character range [1-25])",
			secAnswer2: "Please Enter a valid Security answer (character range [1-25])",
			secAnswer3: "Please Enter a valid Security answer (character range [1-25])",
			siteKey: "Please Enter a valid SiteKey (charcter range [5-20])",
		        password: {
		            required: "Please Enter a vlaid password (character range [8-15])",
		            minlength: "Please Enter a vlaid password (character range [8-15])"
		        },
		        passwordConfirm: {
		            required: "Please Enter a vlaid password (character range [8-15])",
		            minlength: "Please Enter a vlaid password (character range [8-15])",
		            passwordMatch: "Your Passwords Must Match"
		        }
		}
			
		}
	});
	</script>
	
	<!-- jQuery Version 1.11.0 -->
	
	
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>

</html>
