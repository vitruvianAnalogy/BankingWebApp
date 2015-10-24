
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Users Log</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">User List</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		
		 <div class="col-lg-10" >

			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
						<div class="table-responsive row" style="margin:5%">
						<table class="table" style="width: 120%">
							<thead>
								<tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Member Id</th>
									<th>User Role</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty externalUserList}">
										<c:forEach var="request" items="${externalUserList}" varStatus="status">
											<tr>
												<c:if test="${request.userTypeId.userTypeId != 123 }">												
													<td>${request.firstName }</td>
													<td>${request.lastName }</td>
													<td>${request.memberId }</td>
													<c:if test="${request.isCustomer == true }">
													<td>Customer</td>
													</c:if>
													<c:if test="${request.isCustomer == false }">
													<td>Merchant</td>
													</c:if>
												</c:if>																						
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${not empty internalUserList}">
										<c:forEach var="request" items="${internalUserList}" varStatus="status">
											<tr>
												<c:if test="${request.userTypeId.userTypeId != 123 }">
													<td>${request.firstName }</td>
													<td>${request.lastName }</td>
													<td>${request.memberId }</td>
													<td>Employee</td>
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
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>
</html>