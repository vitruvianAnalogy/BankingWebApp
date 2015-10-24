
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h1 class="page-header">Manage Transaction</h1>
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
						<li class="active"><a href="#Summary" data-toggle="tab">Account
								Summary</a></li>
						<li><a href="#Credit_Debit" data-toggle="tab">Credit/Debit</a>
						</li>
						<li><a href="#Transfer" data-toggle="tab">Transfer</a></li>				
						<li><a href="#Authorize" data-toggle="tab">Authorize
								Payment</a></li>
			
						<li><a href="#Initiate" data-toggle="tab">Initiate
								Payment</a></li>
						<sec:authorize access="hasRole('EXT_MERCHANT')">
						<li><a href="#Submit" data-toggle="tab">Submit Payment
								Payment</a></li>
								</sec:authorize>

					</ul>


					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade" id="Credit_Debit">
							<form:form id="creditDebit" role="form" method="POST" commandName="creditDebit"
								action="creditDebit">
								<div class="form-group">
									<br>
									
									<br> <label>Transaction Type:</label> <label
										class="radio-inline"> 
										<input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="1"
										checked>Credit
									</label> <label class="radio-inline"> 
									<input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="2">Debit
									</label>

								</div>

								<div class="form-group">

									<label>Enter Amount: $ </label> <label> 
									<input id="creditDebitAmount" name="creditDebitAmount" class="form-control" maxlength="8" placeholder="Amount">
									<%-- <form:errors path="creditDebitAmount" /> --%>

								</div>
								<p>
									<button type="submit" class="btn btn-success">Submit</button>

									<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
								</p>
							</form:form>


						</div>
						<div class="tab-pane fade" id="Transfer">
						<form:form id="Transform" role="form" method="POST" commandName="Transform" action="transfer">
							<br> <br> <label>To Account: </label> 
							<input id="toAccountNumber" name="toAccountNumber" class="form-control" placeholder="Account No.">
							<form:errors path="toAccountNumber" /> 
							<br>
							<br> <label>Enter Amount: </label> 
							<input id="transformAmount" name="transformAmount" class="form-control" placeholder="Amount">
							<form:errors path="transformAmount" />  
							<br>
							<p>

								<button type="submit" class="btn btn-success">Submit</button>
								<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
							</p>
						</form:form>
						</div>





						<div class="tab-pane fade" id="Authorize">
							<!--  div id="page-wrapper">-->
								<div class="row">
									<div class="col-lg-12">
										<h1 class="page-header">Payment Requests</h1>
									</div>
									<!-- /.col-lg-12 -->
								</div> 

								<div class="col-lg-16">

									<div class="panel panel-default">
										<div class="panel-heading">
											<strong>REQUESTS</strong>
										</div>

										<div class="panel-body">
											
											<div class="table-responsive">
												<table class="table" style="width: 120%">
													<thead>
														<tr>
															<th>Payment Request ID</th>
															<th>Payment Descriotion</th>
															<th>Requesting Merchant</th>
															<th>Requesting Amount</th>
															<th>Requesting Date</th>
															<th>Requesting Status</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty requestList}">
															<c:forEach var="request" items="${requestList}"
																varStatus="status">
																<tr>
																	<c:if test="${request.status == 'PENDING_AUTH' }">
																	<td>${request.paymentId }</td>
																	<td>${request.description }</td>
																	<td>${request.merchantLastName } ${request.merchantFirstName }</td>
																	<td>${request.amount }</td>
																	<td>${request.date }</td>
																	<td><strong>${request.status }</strong></td>

																	
																		<td><button id="viewButton${request.paymentId}"
																				class="btn btn-success" data-toggle="modal"
																				data-target="#authotizePayment">Authorize</button></td>
																		<script type="text/javascript">
																			$(
																					'#viewButton${request.paymentId}')
																					.click(
																							function() {
																								var requestingMerchantLastName = '${request.merchantLastName}';
																								var requestingMerchantFirstName = '${request.merchantFirstName}';
																								var requestingAmount = '${request.amount}';
																								var requestingDate = '${request.date}';
																								var paymentRequestId = '${request.paymentId}';
																								var paymentDescription = '${request.description}';
																						

																								$(
																										'#requestingMerchantLastName')
																										.text(
																												requestingMerchantLastName);
																								$(
																										'#requestingMerchantFirstName')
																										.text(
																												requestingMerchantFirstName);
																								$(
																										'#requestingAmount')
																										.text(
																												requestingAmount);
																								$(
																										'#requestingDate')
																										.text(
																												requestingDate);
																								$(
																										'#paymentRequestId')
																										.val(
																												paymentRequestId);
																								$(
																								'#requestingDescription')
																								.text(
																										paymentDescription);
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
							<!-- /div> -->
						</div>


						<div class="modal fade" id="authotizePayment" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Request Details</h4>
									</div>

									<form:form id="authorizePaymentRequest" role="form" method="POST" commandName="authorizePaymentRequest"
										action="authorizePaymentRequest" enctype="multipart/form-data">
										<input type="hidden" id="paymentRequestId" name="paymentRequestId" /> 
										<div class="modal-body">
											<table class="table" style="width: 40%">
												<tbody>
													<tr>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td><label>Merchant First Name</label></td>
														<td id="requestingMerchantFirstName"></td>
													</tr>
													<tr>
														<td><label>Merchant Last Name</label></td>
														<td id="requestingMerchantLastName"></td>
													</tr>
													<tr>
														<td><label>Request Amount</label></td>
														<td id="requestingAmount"></td>
													</tr>
													<tr>
														<td><label>Request Date</label></td>
														<td id="requestingDate"></td>
													</tr>
													<tr>
														<td><label>Description</label></td>
														<td id="requestingDescription"></td>
													</tr>
													<tr>
														<td><label>Select the certificate file to upload</label></td>
														<td><input type="file" name="certFile"/></td>
													</tr>
													
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary" name ="authorizeAction" value="authorized">Authorize</button>
											<button type="submit" class="btn btn-default" name = "authorizeAction" value = "declined">Declie</button>
												

										</div>
									</form:form>
								</div>
							</div>
						</div>


						<div class="tab-pane fade" id="Initiate">
							<form:form id="initiatePayment" role="form" method="POST" commandName="initiatePayment" action="initiatePayment">
								<br> <label>To Account Number: </label> 
								<input id="toMerchantAccountNumber" name="toMerchantAccountNumber" class="form-control" placeholder="Account No.">
								<form:errors path="toMerchantAccountNumber" /> 
								<br>
								<label>Amount: </label> 
								<input id="amount" name="amount" class="form-control" placeholder="Amount"> <br> 
								<form:errors path="amount" /> 
								<label>Description:
								</label>

								<textarea id="description" name="description" class="form-control" rows="3"></textarea>
								<form:errors path="description" /> 
								<br>
								<p>
									<button type="submit" class="btn btn-success">Initiate
										Payment Request</button>
									<button type="button" class="btn btn-default">Cancel</button>
									<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
								</p>
							</form:form>
						</div>

						<div class="tab-pane fade" id="Submit">
							<!--  div id="page-wrapper">-->
							<div class="row">
								<div class="col-lg-12">
									<h1 class="page-header">Authorized Payment Requests</h1>
								</div>
								<!-- /.col-lg-12 -->
							</div>

							<div class="col-lg-16">

								<div class="panel panel-default">
									<div class="panel-heading">
										<strong>Authorized Payment Requests</strong>
									</div>

									<div class="panel-body">
										
										<div class="table-responsive">
											<table class="table" style="width: 120%">
												<thead>
													<tr>
														<th>Payment Request ID</th>
														<th>Payment Description</th>
														<th>Authorizer Account Number</th>
														<th>Authorized Amount</th>
														<th>Quthorized Date</th>
														<th>Requesting Status</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty requestList}">
														<c:forEach var="request" items="${requestList}"
															varStatus="status">
															<tr>
																<c:if test="${request.status == 'AUTHORIZED' }">
																	<td>${request.paymentId }</td>
																	<td>${request.description }</td>
																	<td>${request.authorizerAccountId }
																	
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
																							var aaadescription =   '${request.description}';

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
																							.text(
																									aaadescription);
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
							<!-- /div> -->
						</div>
						
						<sec:authorize access="hasRole('EXT_MERCHANT')">						
						<div class="modal fade" id="submitAuthorizedPayment" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Request Details</h4>
									</div>

									<form:form id="submitAuthorizedPaymentRequest" role="form" method="POST" commandName="submitAuthorizedPaymentRequest"
										action="submitAuthorizedPaymentRequest">
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
											<button type="submit" class="btn btn-primary">Authorize</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>

										</div>
									</form:form>
								</div>
							</div>
						</div>
						</sec:authorize>
						
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
							<div class="panel panel-default">
								<div class="panel-heading">Account Information</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									
									
									
									<div class="table-responsive">
									
										<table style="width: 60%; height: 20%">

											<tbody>
												<tr>
													<td><b>Account Number</b></td>
													<td>${account.accountNo }</td>

												</tr>
												<tr>
													<td><b>First Name</b></td>
													<td>${account.firstName }</td>

												</tr>
												<tr>
													<td><b>Last Name</b></td>
													<td>${account.lastName }</td>

												</tr>
												<tr>
													<td><b>Balance</b></td>
													<td>$ ${account.amount }</td>

												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.panel-body -->
							</div>
						</div>

					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
	</div>
	<  script type="text/javascript">
	$.validator.addMethod('amount', function( val, element ) {
	    var regexp = new RegExp("^[1-9][0-9]*[.]?[0-9]*$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please Enter a valid Amount");
	
	$.validator.addMethod('account', function( val, element ) {
	    var regexp = new RegExp("^[0-9][0-9]*$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please Enter a valid Account Number");
	
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
			},
			toAccountNumber:
			{
				required: true,
				account:true
			}
		}
	}),
	$("#initiatePayment").validate({
		rules: {
			amount:{
				required: true,
				amount:true
			},
			description:
			{
				required: true
			},
			toMerchantAccountNumber:
			{
				required: true,
				account:true
			}
		}
	})
	</script>
</body>
</html>
