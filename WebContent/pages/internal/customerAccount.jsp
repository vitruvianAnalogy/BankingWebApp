<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Customer Account</title>
</head>
<body>
	
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	
	<div id="page-wrapper">
		
		<!-- HEADING1 -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Account Details</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		
		<!-- Body of the page -->
		 <div class="col-lg-10" >

			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
				<div class="table-responsive row" style="margin:5%">
							<table class="table" style="width: 120%">
								<tbody>

											<tr>
												<td>First Name</td>
												<td>${customerInfo.firstName}</td>
											</tr>
											<tr>
												<td>Last Name</td>
												<td>${customerInfo.lastName}</td>
											</tr>
											<tr>
												<td>AccountNo</td>
												<td>${accountNo}</td>
											</tr>
											<tr>
												<td>Type</td>
												<c:if test="${customerInfo.isCustomer == 'true'}">
												<td>Customer</td>
												</c:if>
												<c:if test="${customerInfo.isCustomer != 'true' && customerInfo.isEmployee == false}">
												<td>Merchant</td>
												</c:if>
											</tr>
											<tr>
												<td>Email ID</td>
												<td>${customerInfo.emailId}</td>
											</tr>
											<tr>
												<td>Contact No</td>
												<td>${customerInfo.contactNo}</td>
											</tr>
											<tr>
												<td>Address</td>
												<td>${customerInfo.address1},&nbsp;${customerInfo.address2},&nbsp;${customerInfo.city},&nbsp;${customerInfo.state},&nbsp;${customerInfo.zip}</td>
											</tr>

								</tbody>
							</table>
						</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>

</html>
