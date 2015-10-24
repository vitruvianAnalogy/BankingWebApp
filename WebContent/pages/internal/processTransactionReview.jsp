<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transaction Review</title>
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
				<h1 class="page-header">Process Customer Transactional Review Requests</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading">Reviews from Customers</div>
				<!-- /.panel-heading -->
				<div class="panel-body">



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
											<strong>Transactional Review Requests</strong>
										</div>

										<div class="panel-body">
											
											<div class="table-responsive">
												<table class="table" style="width: 120%">
													<thead>
														<tr>
															<th>Transaction Request ID</th>
															<th>Transaction ID</th>
															<th>Customer Member Id</th>
															<th>Transaction Type</th>
															<th>Request Date</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty reviewList}">
															<c:forEach var="review" items="${reviewList}"
																varStatus="status">
																<tr>
																	
																	<td>${review.transactionReviewId }</td>
																	<td>${review.transactionId }</td>
																	<td>${review.custMemberId.memberId }</td>
																	<td>${review.transactionType }</td>
																	<td>${review.requestDate }</td>

																	
																		<td><button id="processButton${review.transactionId}"
																				class="btn btn-success" data-toggle="modal"
																				data-target="#processReview">Process</button></td>
																		<script type="text/javascript">
																			$(
																					'#processButton${review.transactionId}')
																					.click(
																							function() {
																								var transactionReviewId = '${review.transactionReviewId}';
																								var transactionType = '${review.transactionType}';
																								var custMemberId= '${review.custMemberId.memberId}';
																								var amount = '${review.amount}';
																								var fromAccount = '${review.fromAccount}';
																								var toAccount = '${review.toAccount}';
																								var transactionId = '${review.transactionId}';
																								
																						
																								$(
																								'#transactionReviewId')
																								.val(
																										transactionReviewId);
																								$(
																										'#transactionType')
																										.text(
																												transactionType);
																								
																								$(
																										'#custMemberId')
																										.text(
																												custMemberId);
																								
																								
																								$(
																								'#amount')
																								.text(
																										amount);
																								$('#fromAccount').text(fromAccount);
																								$('#toAccount').text(toAccount);
																								$('#transactionId').text(transactionId);
																								
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


						<div class="modal fade" id="processReview" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Transaction Review Details</h4>
									</div>

									<form:form id="processTransReview" role="form" method="POST" commandName="processTransReview"
										action="processTransReview">
										<input type="hidden" id="transactionReviewId" name="transactionReviewId" /> 
										<div class="modal-body">
											<table class="table" style="width: 40%">
												<tbody>
													<tr>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td><label>Customer Member ID</label></td>
														<td id="custMemberId"></td>
													</tr>
													<tr>
														<td><label>Transaction ID</label></td>
														<td id="transactionId"></td>
													</tr>
													<tr>
														<td><label>Transaction Type</label></td>
														<td id="transactionType"></td>
													</tr>
													<tr>
														<td><label>Source Account</label></td>
														<td id="fromAccount"></td>
													</tr>
													<tr>
														<td><label>Destination Account</label></td>
														<td id="toAccount"></td>
													</tr>
													<tr>
														<td><label>Amount</label></td>
														<td id="amount"></td>
													</tr>
													
													
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary" name ="processTxnReview" value="approved">Approve</button>
											<button type="submit" class="btn btn-default" name = "processTxnReview" value = "declined">Decline</button>


										</div>
									</form:form>
								</div>
							</div>
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

									<form id="processPaymentRequest" role="form" method="POST"
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
									</form>
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