<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transaction Review</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper" style="width:105%">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Transactional Review</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->


				<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading">Transaction Services</div>
				<!-- /.panel-heading -->
				
				
				<ul class="nav nav-pills">
						<li class="active"><a href="#transactionalReview" data-toggle="tab">Transaction History</a></li>
						<li><a href="#createTransactionRequest" data-toggle="tab">Create Transaction Request</a>
						</li>
						

					</ul>
					
					<div class="tab-content">
						<div class="tab-pane fade in active in active" id="transactionalReview">
							
							<div class="col-lg-12">
			<br>
			<br>
			<div class="panel panel-default" style="width:100%">
			<br>
				<div class="panel-heading"><strong>Transactional Review Requests to Bank</strong></div>
			<br>
					<div class="panel-body">
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
									<%
									if (request.getAttribute("submitError") != null) {
									%>
									<p class="label label-warning" style="font-size:13px">${submitError }</p>
									<br>
									<%
										}
									%>
						<div class="table-responsive">
							<table class="table" style="width: 120%">
								<thead>
									<tr>
										<th>Transaction ID</th>
										<th>Transaction Type</th>
										<th>Source Account</th>										
										<th>Destination Account</th>
										<th>Amount</th>
										<th>Status</th>
										<th>Transaction Date</th>
										<th>Processed Date</th>
										<th>Action</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty transactionList}">
										<c:forEach var="transaction" items="${transactionList}" varStatus="status">
											<tr>
												
												<td>${transaction.transactionId }</td>
												<td><strong>${transaction.transactionType }</strong></td>
												<td>${transaction.fromAccount}</td>
												<td>${transaction.toAccount }</td>
												<td><strong>${transaction.amount }</strong></td>
												<td>${transaction.status }</td>
												<td>${transaction.date }</td>
												<td>${transaction.processedDate }</td>
											<c:if test="${transaction.status != 'UNDER_REVIEW' }">
											<td><button id="modifyButton${transaction.transactionId}" class="btn btn-success" 
											data-toggle="modal" data-target="#modify">Modify</button></td>
											<td><button id="deleteButton${transaction.transactionId}" class="btn btn-warning" 
											data-toggle="modal" data-target="#delete">Delete</button></td>
											</c:if>
											</tr>
											<script type="text/javascript">
													$('#modifyButton${transaction.transactionId}').click(function(){
													var transactionId = '${transaction.transactionId}';
													var transactionType = '${transaction.transactionType}';
													var fromAccount = '${transaction.fromAccount}';
													var toAccount = '${transaction.toAccount}';
													var amount = '${transaction.amount}';
														
												   	 $('#transactionId').val(transactionId);
												   	 $('#transactionType').val(transactionType);
												   	 $('#fromAccount').val(fromAccount);
												   	 $('#toAccount').val(toAccount);
												   	 $('#amount').val(amount);
												   	
													});
													$('#deleteButton${transaction.transactionId}').click(function(){
														var transactionId = '${transaction.transactionId}';
														 $('#transactionID').val(transactionId);
													});
												</script>
										</c:forEach>
										</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

						</div>
		
				

					<div class="modal fade" id="modify" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Modal title</h4>
								</div>
								
								
								<form id="modifyTransactionForm" role="form" method="POST"
									action="modifyTransaction">
									<div class="modal-body">
									
										<input class="form-control" type="hidden" style="width: 25%" id=transactionId name="transactionId"
											tabindex="1">
										<br>	
										<label>Transaction Type</label>
										<select class="form-control" style="width: 25%" id="transactionType" name="transactionType" placeholder="Transaction Type"
											tabindex="2">
											<option value="debit" selected>Debit</option>
											<option value="credit">Credit</option>
											<option value="transfer">Transfer</option>
										</select>
										<script type="text/javascript">
										$( "#transactionType" )
										  .change(function () 
												  {
													if($("#transactionType").val() == "transfer")
													{
														$("#destAccount").show();
														$("#toAccount").show();
													}
													else
													{
														$("#destAccount").hide();
														$("#toAccount").hide();
													}
												  });
										</script>
										<br>	
										<label>Source Account</label>
										<input class="form-control" style="width: 25%" id="fromAccount" name="fromAccount"
											tabindex="3" maxlength="10">
										<br>	
										<label id="destAccount">Destination Account</label>
										<input class="form-control" style="width: 35%" id="toAccount" name="toAccount"
											tabindex="4" maxlength="10">
										<br>	
										<label>Amount</label>
										<input class="form-control" style="width: 35%" id="amount" name="amount"
											tabindex="5">
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Submit</button>
									</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="modal fade" id="delete" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Transactional Review - Delete Transaction</h4>
								</div>
								
								
								<form id="deleteTransactionForm" role="form" method="POST"
									action="deleteTransaction">
									<div class="modal-body">
									
										<input class="form-control" type="hidden" style="width: 25%" id="transactionID" name="transactionID"
											tabindex="1">
										<br>	
										<label>Do You Want to Delete the Transaction?</label>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">No</button>
										<button type="submit" class="btn btn-primary">Yes</button>
									</div>
									</div>
								</form>
							</div>
						</div>
						</div>
						
												<div class="tab-pane fade" id="createTransactionRequest">
							
							<div class="col-lg-12">

			<div class="panel panel-default" style="width:120%">
			<br>
				<div class="panel-heading"><strong>Create New Transaction Request</strong></div>
			<br>
					<div class="panel-body">
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
						<div class="form-responsive">
							<form action="createNewReq" id="createRequest" method="post">
							<br>
							<br>
							<label>Transaction Type </label> <br> <input id="transactionType" name="transactionType"  placeholder="Transaction Type"> <br><br>
							<label>Amount</label> <br><input id="transactionAmount" name="transactionAmount"  placeholder="Amount"> <br> <br>
							<label>Source Account </label> <br> <input id="fromAccount" name="fromAccount"  placeholder="Source Account"> <br><br>
							<label>Destination Account </label> <br><input id="toAccound" name="toAccound"  placeholder="Destination Account"> <br><br>
							<button type="submit"> Create </button>
							</form>
						</div>
					</div>
				</div>
			

						</div>
					</div>
						
						</div>
						</div>
						</div>
	</div>

	<!-- /.panel-body -->



		
	<script type=text/javascript >
	$.validator.addMethod('addressField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9,. _;-]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [,][.][_][;][-]");
	
	$.validator.addMethod('amount', function( val, element ) {
	    var regexp = new RegExp("^[1-9][0-9]*[.][0-9]*$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Invalid Amount. Please type numbers or . only");

	$.validator.addMethod('numbersOnly', function( val, element ) {
	    var regexp = new RegExp("^[0-9]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type numbers only");
	
	$('#updateUser').validate({
		rules:{
			fromAccount:{
				required: true,
				numbersOnly: true,
				maxlength: 10
			},
			
			toAccount:{
				required: true,
				numbersOnly: true,
				maxlength: 10
			},
			
			amount:{
				required: true,
				numbersOnly: true,
				maxlength: 10
			}
			},
		messages:{
			fromAccount: "This field is required",
			toAccount: "This field is required",
			amount: "Invalid Amount"
			
		}
	});
	</script>	
</body>
</html>

