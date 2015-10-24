package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.ModifyUserModel;

public interface EmployeeUserDAO {
	public List<PaymentRequestDTO> getPaymentRequest();
	public List<TransactionDTO> getTransactionRequest();
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId);
	public TransactionDTO getTransactionDTOById(long transactionId);
	public boolean makeDebit(int memberID, double amount);
	public boolean makeCredit(int memberID, double amount);
	public int getMemberIdByAccount(long accountNumber);
	public ModifyUserModel getEmployeeDetails(int memberID);

	public List<RequestDTO> displayEmployeeUserAccountDAO(int memberId);
	public long returnCustomerAccountNo(int memberId);
	public List<RequestDTO> displayEmployeeUserTransactionDAO(int memberId);
	public List<TransactionDTO> getTransactionListForCustomer(int memberId);
	
	public List<TransactionReviewDTO> getTransactionReviewRequests();
	
	public TransactionReviewDTO getTransactionReviewDTO(long transactionReviewId);
	
	public boolean updateTransaction(TransactionDTO txnDTO);
	
	public boolean updateTransactionReviewDTO(TransactionReviewDTO reviewDTO);

	public List<UserDTO> getInternalUsersListForDisplay();


}
