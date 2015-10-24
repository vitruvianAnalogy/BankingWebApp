<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Manage Internal User </title>
<style type="text/css">
	#creditDebit label.error {
		font-weight: 500;
		color:red;
	}
</style>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Users</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading">Internal User Account services</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<!-- Nav tabs -->
					<ul class="nav nav-pills">
						<li class="active"><a href="#Create" data-toggle="tab">Create Account</a></li>
						<li class ="update"><a href="#update" data-toggle="tab">View</a>
						</li>			
					</ul>


					<!-- Tab panes -->
					<div class="tab-content">
						<div>
						
						</div>
						<div class="tab-pane fade" id="update">
							<form id="searchUser" role="form" method="POST" action="getEmp">
							<div class="col-md-10 col-md-offset-1">
								<label>Enter Employee Id</label>
								<input class="form-control" type="text" id="memberId" name="memberId" placeholder="Member ID" value="${memberId}">
							</div>
							
							<div>
							<button type="submit" class="btn btn-primary" value="getDetails"> Get details </button>
							</div>
							
							</form>
							<table style="width: 70%; height: 60%">

							<tbody>
								
								<tr>
									<td><b>Email ID</b></td>
									<td>${employeeDetails.emailId}</td>

								</tr>
								<tr>
									<td><b>Contact Number</b></td>
									<td>${employeeDetails.contactNo}</td>
								</tr>


								<tr>
									<td><b>Address1</b></td>
									<td>${employeeDetails.address1}</td>
								</tr>

								<tr>
									<td><b>Address2</b></td>
									<td>${employeeDetails.address2}</td>
								</tr>

								<tr>
									<td><b>City</b></td>
									<td>${employeeDetails.city}</td>
								</tr>

								<tr>
									<td><b>State</b></td>
									<td>${employeeDetails.state}</td>
								</tr>

								<tr>
									<td><b>Zip</b></td>
									<td>${employeeDetails.zip}</td>
								</tr>
							</tbody>
							</table>
							<div>
							
							
						</div>
						
						<div class="tab-pane fade in active in active" id="Create">
							<form id="signUpForm" role="form" method="POST" action="createEmployee">
						<fieldset>
						<div class="row">
									<div class="col-md-10 col-md-offset-1">
										<c:if test="${signUpForm != null }">
											<div style="color:red">
												Please Enter a different Employee Name or Email ID.
											</div>
										</c:if>
											<div>
												<div>
													<br>
													<h4>Personal Information</h4>
													<br>
												</div>
												
										<div class="col-md-5">
												<div class="form-group" style="color:red">
													<input class="form-control" placeholder="First Name" maxlength="25" id="firstName"
														name="firstName" tabindex="3" value="${signUpForm.firstName }">
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
											
											<div class="form-group">
												<input id="designation" class="form-control" 
													placeholder="Designation" name="designation" maxlength="25" tabindex="14" value="${signUpForm.designation}">
											
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
													class="btn btn-lg btn-success btn-block" tabindex="25" value="Sign Up"  	>

											</div>
										</div>
										<div class="col-md-5 col-md-offset-1 ">
											
											
											<div class="form-group">
											
													<input id="passwordConfirm" class="form-control" type="password"
														placeholder="Confirm Password" name="password1" tabindex="17">
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
						</form>							

						</div>
						
												<div class="tab-pane fade in active in active" id="Summary">
							<br>
							<br>
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
							<br>
							
						</div>
						
						
						<div class="tab-pane fade" id="update">
							
							<div class="col-lg-12">

						<div class="table-responsive">
							<table class="table" style="width: 120%">
								<tbody>
									<c:if test="${not empty requestList}">
										<c:forEach var="request" items="${requestList}" varStatus="status">
											<tr>
												<td>Member ID</td>
												<td>${request.requestId }</td>
											</tr>
											<tr>
												<td>Name</td>
												<td>${request.memberId.memberId }</td>
											</tr>
											<tr>
												<td>Designation</td>
												<td>${request.requestType }</td>
											</tr>
											<tr>
												<td>Email ID</td>
												<td>${request.requestDate }</td>
											</tr>
											<tr>
												
												<c:if test="${request.status == 'NEW' }">
													<td><button id="viewButton${request.requestId}" class="btn btn-success" 
											data-toggle="modal" data-target="#viewUser">View</button></td>
												<script type="text/javascript">
													$('#viewButton${request.requestId}').click(function(){
													var firstName = '${request.memberId.firstName}';
													var lastName = '${request.memberId.lastName}';
													var contactNo = '${request.memberId.contactNo}';
													var emailId = '${request.memberId.emailId}';
													var isCustomer = '${request.memberId.isCustomer}';
													var isEmployee = '${request.memberId.isEmployee}';
													var requestId = '${request.requestId}';
													var requestType = '${request.requestType}';
													var type = 'Customer';
													if(isCustomer == 'false' && isEmployee == false)
														type = 'Merchant';
														
												   	 $('#firstName').text(firstName);
												   	 $('#lastName').text(lastName);
												   	 $('#contactNo').text(contactNo);
												   	 $('#emailId').text(emailId);
												   	 $('#type').text(type);
												   	 $('#requestId').val(requestId);
												   	 $('#requestType1').text(requestType);
												   	 $('#requestType').val(requestType);
													});
												</script>
												</c:if>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
									

						</div>
					</div>
						

					</div>
				</div>
				<!-- /.panel-body -->
				
			</div>
			<!-- /.panel -->
		</div>
	</div>	
	
</body>
</html>
