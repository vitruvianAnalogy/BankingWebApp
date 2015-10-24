
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Manage User Account</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Account</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->


		<div class="tab-pane fade in active in active" id="Summary">
			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Account Information</strong>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
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
						<table style="width: 70%; height: 60%">

							<tbody>
								
								<tr>
									<td><b>First Name</b></td>
									<td>${userDTO.firstName }</td>

								</tr>
								<tr>
									<td><b>Last Name</b></td>
									<td>${userDTO.lastName }</td>

								</tr>
								<tr>
									<td><b>Email ID</b></td>
									<td>${userDTO.emailId}</td>

								</tr>
								<tr>
									<td><b>Contact Number</b></td>
									<td>${userDTO.contactNo}</td>
								</tr>


								<tr>
									<td><b>Address1</b></td>
									<td>${userDTO.address1}</td>
								</tr>

								<tr>
									<td><b>Address2</b></td>
									<td>${userDTO.address2}</td>
								</tr>

								<tr>
									<td><b>City</b></td>
									<td>${userDTO.city}</td>
								</tr>

								<tr>
									<td><b>State</b></td>
									<td>${userDTO.state}</td>
								</tr>

								<tr>
									<td><b>Zip</b></td>
									<td>${userDTO.zip}</td>
								</tr>

								<tr>
									<td><b>Date of Birth</b></td>
									<td>${userDTO.dateOfBirth}</td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><button class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal" style="width: 50%">
											Update Account</button></td>
									<td>		
									<button  class="btn btn-lg btn-danger btn-block" data-toggle="modal" data-target="#myModalDelete" style="width: 50%">
											Delete Account</button></td>

								</tr>

							</tbody>

						</table>

						<!-- /.table-responsive -->
					</div>
					
					<div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog"
						aria-labelledby="myModalDelLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalDelLabel">Delete Account</h4>
								</div>
								
								
								<form id="deleteUser" role="form" method="POST"
									action="deleteExternalUserDetials">
									<div class="modal-body">
					
									<div>
									Do you really want to Delete the account???
									</div>
						
									<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">NO</button>
											<button type="submit" class="btn btn-primary">YES</button>
									</div>
									</div>
							 	</form>
							 </div>
						</div>
					</div>
								
									
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Update Account</h4>
								</div>
								
								<form id="updateUser" role="form" method="POST"
									action="updateExternalUserDetails">
									<div class="modal-body">
									
										<input class="form-control" type="hidden" style="width: 25%" id="memberId" name="memberId" placeholder="memberId"
											tabindex="25" value="${userDTO.memberId}">
										<br>	
										<label>email_Id</label>
										<input class="form-control" style="width: 25%" id="emailId" name="emailId" placeholder="emailId"
											tabindex="15" value="${userDTO.emailId}">
										<br>	
										<label>Contact_No</label>
										<input class="form-control" style="width: 25%" id="contactNo" name="contactNo"
											tabindex="10" maxlength="10" value="${userDTO.contactNo}">
										<br>	
										<label>Address_1</label>
										<input class="form-control" style="width: 35%" id="address1" name="address1"
											tabindex="50" value="${userDTO.address1}">
										<br>	
										<label>Address_2</label>
										<input class="form-control" style="width: 35%" id="address2" name="address2"
											tabindex="50" value="${userDTO.address2}">
										<br>	
										<label>City</label>	
										<input class="form-control" style="width: 25%" id="city" name="city"
											tabindex="15" value="${userDTO.city}">
										
										<br>	
										<label>State</label>
										<input class="form-control" style="width: 25%" id="state" name="state"
											tabindex="2" maxlength="2" value="${userDTO.state}">
										
										<br>	
										<label>Zip</label>
										<input class="form-control" style="width: 25%" id="zip" name="zip"
											tabindex="5" maxlength="5" value="${userDTO.zip}">
										<label>Upload Your Certificate</label>	
										<input type="file" name="certFile"/>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Save
											changes</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- /.panel-body -->



		
	<script type=text/javascript >
	$.validator.addMethod('addressField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9,. _;-]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [,][.][_][;][-]");
	
	$.validator.addMethod('alphabetsOnly', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type alphabets only");

	$.validator.addMethod('numbersOnly', function( val, element ) {
	    var regexp = new RegExp("^[0-9]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type numbers only");
	
	$('#updateUser').validate({
		rules:{
			emailId:{
				required: true,
				email: true,
				maxlength: 25
			},
			
			contactNo:{
				required: true,
				numbersOnly: true,
				maxlength: 10
			},
			
			address1:{
				required: true,
				addressField: true,
				maxlength: 50
			},
			
			address2:{
				required: true,
				addressField: true,
				maxlength: 50
			},
			
			city:{
				required: true,
				alphabetsOnly: true,
				maxlength: 15
			},
			state:{
				required: true,
				alphabetsOnly: true,
				maxlength: 2,
				minlength:2
			},
			
			zip:{
				required:true,
				numbersOnly: true,
				maxlength:5,
				minlength:5
			},
		messages:{
			emailId: "Please Enter a valid Email ID",
			contactNo: "Please Enter a valid contact no",
			address1: "Address1 should be less than 50 characters",
			address2: "Address2 should be less than 50 characters",
			city: "Please enter a valid city",
			state: "Please enter a valid state(Should be 2 characters)",
			zip: "The Zip code should be a 5 digit number"
			
		}
			
		}
	})
	</script>	
</body>
</html>
