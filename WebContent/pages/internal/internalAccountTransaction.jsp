<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Account List</title>
</head>
<body>
	
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	
	<div id="page-wrapper">
		
		<!-- HEADING1 -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Account List</h1>
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
												<c:if test="${request.status == 'APPROVED' }">
													<td><form:form id="viewsUserTransactions" commandName="viewsUserTransactions" method="POST" action="viewUserTransactions">
													<input type="hidden" value="${request.requestId }" name="requestId">
													<button id="viewButton${request.requestId}" class="btn btn-success" data-toggle="modal" data-target="#viewUser" type="submit">View</button>
													</form:form></td>													
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

