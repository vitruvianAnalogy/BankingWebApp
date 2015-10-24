package edu.asu.safemoney.service;

import java.math.BigInteger;
import java.util.List;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;

public interface EmployeeUserService {
	
	public boolean sendExtUserViewRequests(int customerId, int employeeId);
	
	public boolean sendExtUserTransactionViewRequests(int customerId, int employeeId);
	
	public boolean authorizeCreditTransaction(long requestId);
	
	public boolean authorizePaymentTransaction(long requestId);
	
	public boolean getViewRequestList(long requestId);
	

	public List<PaymentRequestDTO> getPaymentRequest();
	public List<TransactionDTO> getTransactionRequest();

	
	public String updatePaymentRequest(long paymentRequestId,String status);
	public String updateTransactionRequest(long transactionRequestId, String status);
	public boolean makeCredit(int memberID, double amount);
	public boolean makeDebit(int memberID, double amount);
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId);
	public TransactionDTO getTransactionDTOById(long transactionRequestId);
	
	public int getMemberIdByAccount(long accountNumber);

	public List<RequestDTO> getRequestList(int memberId);
	
	public List<RequestDTO> getTransactionList(int memberId);
	
	public int getCustomerId (long requestId);
	
	public long getAccountNo(int memberId);
	
	public List<RequestDTO> getTransactionRequestList(int memberId);
	
	public List<TransactionDTO> getAllTransactions(int memberId);

	public List<TransactionReviewDTO> getTransactionReviewList();
	
	public boolean approveTransactionReview(long transactionReviewId, int memberId);
	
	public boolean declineTransactionReview(long transactionReviewId, int memberId);
	
	public List<UserDTO> getInternalUserListForAdmin();

}
