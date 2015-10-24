
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Customer Transaction Account</title>
</head>
<body>
	
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	
	<div id="page-wrapper">
		
		<!-- HEADING1 -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Transaction List</h1>
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

