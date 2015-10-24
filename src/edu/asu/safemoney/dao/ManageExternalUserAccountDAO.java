package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;


public interface ManageExternalUserAccountDAO {

	public boolean updateUser(ModifyUserModel modifyUserModel);
	public UserDTO displayUserAccountDAO(int memberId);
	public AccountModel getAccountDetails(int memberId);
	public boolean updateAccountBalance(int memberId, double amount);
	public boolean createTransaction(TransactionDTO transactionDTO);
	
	public boolean createAccount(AccountDTO accountDTO);
	public int getMemberIdByAccount(long accountNumber);
	public boolean deleteExtUserAccount(int memberId);
	
	public List<PaymentRequestDTO> getPaymentRequest(int memberId);
	
	public PaymentRequestDTO getPaymentRequestByPaymentId(long paymentId);
	public TransactionDTO getTransactionByTransactionId(long transactionRequestId);//getTransactionByTransactionId
	public boolean updatePaymentRequest(PaymentRequestDTO paymentDTO);
	public boolean updateTransactionRequest(TransactionDTO transactionDTO);
	public boolean addPaymentRequest(PaymentRequestDTO paymentRequestDTO);

	public boolean findAccount(long accountNumber);
	public boolean findIsEnabled(long accountNumber);
	
	
	public boolean addTransactionReview(TransactionReviewDTO transactionReviewDTO);
	
	public boolean updateTransaction(long transactionId);
	
	public TransactionDTO getTransactionDTO(long transactionId);
	
	public boolean deleteTransaction(TransactionDTO transactionDTO);
	
	public boolean createTransactionRequest(TransactionReviewDTO transactionReviewDTO);

	public List<UserDTO> getPIIAuthorizedUserAccountsDTO();
	
	public List<UserDTO> getMembersListForDisplay();
	
	public List<RequestDTO> getViewAccountRequestsForCustomer(int memberId);
	
	public List<RequestDTO> getViewTransactionsRequestsForCustomer(int memberId);
	
	public boolean authorizeViewAccountRequest(long requestId);
	
	public boolean authorizeViewTransactionsRequest(long requestId);
	
	public boolean declineViewAccountRequest(long requestId);
	public boolean declineViewTransactionsRequest(long requestId);
	
	public boolean isTransactionExists(long transactionId);
	
	public boolean updateIsEnabled(int memberId, boolean isEnabled);
	
}
