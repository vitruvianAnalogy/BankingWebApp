
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Authorize Transactions</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Critical Transactions</h1>
			</div>
			<div class="tab-content">
						

						<div class="tab-pane fade in active" id=manageTransaction>
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
							<!--  div id="page-wrapper">-->
								<div class="row">
									<div class="col-lg-12">
										<h1 class="page-header">Transaction Requests</h1>
									</div>
									<!-- /.col-lg-12 -->
								</div> 

								<div class="col-lg-16">

									<div class="panel panel-default">
										<div class="panel-heading">
											<strong>REQUESTS FOR Transactions</strong>
										</div>

										<div class="panel-body">
											
											<div class="table-responsive">
												<table class="table" style="width: 120%">
													<thead>
														<tr>
															<th>Transaction ID</th>
															<th>Transaction Type</th>
															<th>Member Id</th>
															<th>From Accnt</th>
															<th>To Accnt</th>
															<th>Amount</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty transactionRequestList}">
															<c:forEach var="transactionRequest" items="${transactionRequestList}"
																varStatus="status">
																<tr>
																	
																	<td>${transactionRequest.transactionId }</td>
																	<td>${transactionRequest.transactionType }</td>
																	<td>${transactionRequest.memberId.memberId }</td>
																	<td>${transactionRequest.fromAccount }</td>
																	<td>${transactionRequest.toAccount }</td>
																	<td>${transactionRequest.amount }</td>

																	
																		<td><button id="processButton${transactionRequest.transactionId}"
																				class="btn btn-success" data-toggle="modal"
																				data-target="#manageTransactiondetail">Process</button></td>
																		<script type="text/javascript">
																			$(
																					'#processButton${transactionRequest.transactionId}')
																					.click(
																							function() {
																								var transactionRequestId = '${transactionRequest.transactionId}';
																								var transactionRequestType = '${transactionRequest.transactionType}';
																								var transactionRequestamount = '${transactionRequest.amount}';
																								
																						
																								$(
																								'#transactionRequestId')
																								.val(
																										transactionRequestId);
																								$(
																										'#transactionRequestType')
																										.text(
																												transactionRequestType);
																								
																								$(
																										'#transactionRequestamount')
																										.text(
																												transactionRequestamount);
																								
																								
																								$(
																								'#transactionRequestID')
																								.text(
																										transactionRequestId);
																								
																							});
																		</script>
																	
																</tr>
															</c:forEach>
															</c:if>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							<!-- /div> -->
						</div>


						<div class="modal fade" id="manageTransactiondetail" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Request Details</h4>
									</div>

									<form:form id="authorizePaymentRequest" role="form" method="POST" commandName="authorizePaymentRequest"
										action="authorizePaymentRequest">
										<input type="hidden" id="transactionRequestId" name="transactionRequestId" /> 
										<div class="modal-body">
											<table class="table" style="width: 40%">
												<tbody>
													<tr>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td><label>Transaction ID</label></td>
														<td id="transactionRequestID"></td>
													</tr>
													<tr>
														<td><label>Transaction Type</label></td>
														<td id="transactionRequestType"></td>
													</tr>
													<tr>
														<td><label>Amount</label></td>
														<td id="transactionRequestamount"></td>
													</tr>
													
													
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary" name ="manageTransactionAction" value="approved">Approve</button>
											<button type="submit" class="btn btn-default" name = "manageTransactionAction" value = "declined">Decline</button>


										</div>
									</form:form>
								</div>
							</div>
						</div>

				

					</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
</body>
</html>