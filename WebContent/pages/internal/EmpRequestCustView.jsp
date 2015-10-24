
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Emp Request View</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Send Request</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		
		 <div class="col-lg-10" >

			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="row">
						<div class="form-group" >
							<form:form id ="sendRequestForm" commandName="sendRequestForm" name="sendRequestForm" target="_self" method="post" action="requestTransactionAccess" style="margin:5%" class="form-inline">
								<ul class="list-unstyled">
									<li><br></li>
									<li>					
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
									</li>								
									<li><h4><label>Enter Member ID</label></h4></li>
																	
									<li><div class="form-group" style="color:red"><input id="memberId" name="memberId"  type="text" class="form-control" placeholder="Member ID" maxlength="11" /></div></li>
									<li><br></li>
									<li><input name="submitRequest" type="submit" value="Submit" class = "btn btn-primary" autofocus="autofocus"/></li>
									</ul>		
							</form:form>
						</div>
					</div>
							<div class="table-responsive row" style="margin:5%">
							<table class="table" style="width: 120%">
								<thead>
									<tr>
										<th>Request ID</th>
										<th>Authorizing Member ID</th>
										<th>Request Date</th>
										<th>Status</th>
										<th>Authorizing Authority</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty requestList}">
										<c:forEach var="request" items="${requestList}" varStatus="status">
											<tr>
												
												<td>${request.requestId }</td>
												<td>${request.authorizingMemberId }</td>
												<td>${request.requestDate }</td>
												<td><strong>${request.status }</strong></td>
												<c:if test="${request.authorizingAuthority == 'EXT_IND_CUST' }">
												<td>Customer</td>
												</c:if>
												<c:if test="${request.authorizingAuthority == 'EXT_MERCHANT' }">
												<td>Merchant</td>
												</c:if>
<%-- 												<c:if test="${request.status == 'NEW' }">
													<td><button id="viewButton${request.requestId}" class="btn btn-success" 
											data-toggle="modal" data-target="#viewUser">View</button></td>
												<script type="text/javascript">
													$('#viewButton${request.requestId}').click(function(){
													var firstName = '${request.memberId.firstName}';
													var lastName = '${request.memberId.lastName}';
													var contactNo = '${request.memberId.contactNo}';
													var emailId = '${request.memberId.emailId}';
													var isCustomer = '${request.memberId.isCustomer}';
													var requestId = '${request.requestId}';
													var requestType = '${request.requestType}';
													var type = 'Customer';
													if(isCustomer == 'false')
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
												</c:if> --%>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					
					</div>
				</div>
				<!-- /#page-wrapper -->
			</div>
		</div>
	
	<script type="text/javascript">
		$.validator.addMethod('numbersOnly', function( val, element ) {
		    var regexp = new RegExp("^[1-9][0-9]*$");
	
		    if (!regexp.test(val)) {
		       return false;
		    }
		    return true;
		}, "Please type numbers only");
		
		$("#sendRequestForm").validate({
			rules: {
				memberId: {
					required: true,
					numbersOnly: true,
					maxlength : 11
				}
		}			
		});
	</script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>
</html>
