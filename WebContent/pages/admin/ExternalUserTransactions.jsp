
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - External User Transactions</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Lookup Transactions</h1>
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
							<form:form id ="sendRequestForm" commandName="sendRequestForm" name="sendRequestForm" target="_self" method="post" action="getTransactionHistoryForAdmin" style="margin:5%" class="form-inline">
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
										<th>Transaction ID</th>
										<th>From Account</th>
										<th>To Account</th>
										<th>Date</th>
										<th>Amount</th>
										<th>Status</th>
										<th>Transaction Type</th>
										<th>Processed Date</th>
										<th>Authorized</th>
										<th>Critical</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty transactionInfo}">
											<c:forEach var="request" items="${transactionInfo}" varStatus="status">
												<tr>
													
													<td>${request.transactionId }</td>
													<td>${request.fromAccount }</td>
													<td>${request.toAccount }</td>
													<td><strong>${request.date }</strong></td>
													<td><strong>${request.amount }</strong></td>
													<td><strong>${request.status }</strong></td>
													<td><strong>${request.transactionType }</strong></td>
													<td><strong>${request.processedDate }</strong></td>
													<c:if test="${request.isAuthorized == true }">
													<td>Yes</td>
													</c:if>
													<c:if test="${request.isAuthorized == false }">
													<td>No</td>
													</c:if>
													<c:if test="${request.isCritical == true }">
													<td>Yes</td>
													</c:if>
													<c:if test="${request.isCritical == false }">
													<td>No</td>
													</c:if>
	
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
