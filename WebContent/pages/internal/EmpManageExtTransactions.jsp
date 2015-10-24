<<<<<<< HEAD
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
=======
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/obulikarthikeyan/SafeMoneyCorp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transactions</title>
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
				<h1 class="page-header">Manage Customer's Transactions and Payments</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading">Transaction Services</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<!-- Nav tabs -->
					<ul class="nav nav-pills">
									
						<li><a href="#manageTransaction" data-toggle="tab">Manage Transactions</a></li>
			
						
						
						<li><a href="#managePayment" data-toggle="tab">Manage Payment</a></li>
							
					</ul>


					<!-- Tab panes -->
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


						

						<div class="tab-pane fade" id="managePayment">
							<!--  div id="page-wrapper">-->
							
							<br>
							<div class="row">
								<div class="col-lg-12">
									<h1 class="page-header">Payment Request</h1>
								</div>
								<!-- /.col-lg-12 -->
							</div>

							<div class="col-lg-16">

								<div class="panel panel-default">
									<div class="panel-heading">
										<strong>Manage Payment Request</strong>
									</div>

									<div class="panel-body">
										
										<div class="table-responsive">
											<table class="table" style="width: 120%">
												<thead>
													<tr>
														<th>Payment Request ID</th>
														<th>Payment Description</th>
														<th>Authorizer Accnt</th>
														<th>Merchant Accnt</th>
														<th>Authorized Amount</th>
														<th>Quthorized Date</th>
														<th>Requesting Status</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty paymentRequestList}">
														<c:forEach var="request" items="${paymentRequestList}"
															varStatus="status">
															<tr>
																
																	<td>${request.paymentId }</td>
																	<td>${request.description }</td>
																	<td>${request.authorizerAccountId }
																	<td>${request.merchantAccountId }
																	<td>${request.amount }</td>
																	<td>${request.date }</td>
																	<td><strong>${request.status }</strong></td>


																	<td><button id="viewButton${request.paymentId}"
																			class="btn btn-success" data-toggle="modal"
																			data-target="#submitAuthorizedPayment">Submit</button></td>
																	<script type="text/javascript">
																		$(
																				'#viewButton${request.paymentId}')
																				.click(
																						function() {
																							var authorizerAccountId = '${request.authorizerAccountId}';
																							var authorizedAmount = '${request.amount}';
																							var authorizedDate = '${request.date}';
																							var paymentRequestId = '${request.paymentId}';
																							var description = '${request.description}';
																								

																							$(
																									'#authorizerAccountId')
																									.text(
																											authorizerAccountId);
																							$(
																									'#authorizedAmount')
																									.text(
																											authorizedAmount);
																							
																							$(
																									'#authorizedDate')
																									.text(
																											authorizedDate);
																							$(
																									'#paymentRequestId2')
																									.val(
																											paymentRequestId);
																							$(
																							'#authorizedDescription')
																							.val(
																									description);
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
						
									
						<div class="modal fade" id="submitAuthorizedPayment" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Request Details</h4>
									</div>


									<form:form id="processPaymentRequest" role="form" method="POST" commandName="processPaymentRequest"
										action="processPaymentRequest">
										<input type="hidden" id="paymentRequestId2" name="paymentRequestId2" /> 
										<div class="modal-body">
											<table class="table" style="width: 40%">
												<tbody>
													<tr>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td><label>Authorizer Account Number</label></td>
														<td id=authorizerAccountId></td>
													</tr>
													<tr>
														<td><label>Authorized Amount</label></td>
														<td id="authorizedAmount"></td>
													</tr>
													<tr>
														<td><label>Authorized Date</label></td>
														<td id="authorizedDate"></td>
													</tr>
													<tr>
														<td><label>Description</label></td>
														<td id="authorizedDescription"></td>
													</tr>
													
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary" name ="managePaymentAction" value="approved">Approve</button>
											<button type="submit" class="btn btn-default" name = "managePaymentAction" value = "declined">Decline</button>


										</div>
									</form:form>
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
	<script type="text/javascript">
	$.validator.addMethod('amount', function( val, element ) {
	    var regexp = new RegExp("^[1-9][0-9][.][0-9]*$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please Enter a valid Amount");
	$("#creditDebit").validate({
		rules: {
			creditDebitAmount: {
				required: true,
				amount: true
			}
		}
	}),
	$("#Transform").validate({
		rules: {
			
			transformAmount:{
				required: true,
				amount:true
			}
		}
	}),
	$("#initiatePayment").validate({
		rules: {
			amount:{
				required: true,
				amount:true
			}
		}
	})
	</script>
</body>
</html>
