package edu.asu.safemoney.service.impln;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.safemoney.service.AdminUserService;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dao.*;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;

@Service
public class EmployeeUserServiceImpl implements EmployeeUserService{
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Autowired
	private RequestDAO requestDAO;	
	
	@Autowired
	private ManageExternalUserAccountDAO manageExternalUserAccountDAO;	
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private ManageExternalUserAccountService extUserService;
	
	
	@Transactional
	@Override
	public List<RequestDTO> getRequestList(int memberId){
		
		List<RequestDTO> requestList = employeeUserDAO.displayEmployeeUserAccountDAO(memberId);
		return requestList;
	}
	
	@Transactional
	@Override
	public boolean sendExtUserViewRequests(int customerId, int employeeId){
		
		UserDTO empUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(employeeId);
		UserDTO custUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(customerId);
		
		if(empUserDTO==null)
		{
			return false;
		}
		
		if(custUserDTO==null)
		{
			return false;
		}
		
		if(custUserDTO.getUserTypeId().getUserTypeId()!=322 && custUserDTO.getUserTypeId().getUserTypeId()!=366)
		{
			return false;
		}
		
		List<RequestDTO> requestList= empUserDTO.getRequestDTOList();
		
		RequestDTO requestDTO = new RequestDTO();
		UserTypeDTO authorityUserTypeId = custUserDTO.getUserTypeId();
		
		requestDTO.setMemberId(empUserDTO);
		requestDTO.setRequestType("VIEW_ACCOUNT");
		requestDTO.setStatus("NEW");
		requestDTO.setRequestDate(new Date());
		requestDTO.setProcessedDate(null);
		requestDTO.setAuthorizingMemberId(customerId);		
		requestDTO.setAuthorityUserTypeId(authorityUserTypeId.getUserTypeId());		
		requestDTO.setAuthorizingAuthority(authorityUserTypeId.getUserType());
		requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
		if(requestList!=null)
		{
			for (RequestDTO req : requestList)
			{
				if(req.getRequestType().equals("VIEW_ACCOUNT") && req.getAuthorizingMemberId()==customerId)
				{
					return false;
				}
				
			}
			requestList.add(requestDTO);

		}
		else
		{
			requestList= new ArrayList<RequestDTO>();
			requestList.add(requestDTO);
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean sendExtUserTransactionViewRequests(int customerId, int employeeId){
		
		UserDTO empUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(employeeId);
		UserDTO custUserDTO = manageExternalUserAccountDAO.displayUserAccountDAO(customerId);
		
		if(empUserDTO==null)
		{
			return false;
		}
		
		if(custUserDTO==null)
		{
			return false;
		}
		
		if(custUserDTO.getUserTypeId().getUserTypeId()!=322 && custUserDTO.getUserTypeId().getUserTypeId()!=366)
		{
			return false;
		}
		
		List<RequestDTO> requestList= empUserDTO.getRequestDTOList();
		
		RequestDTO requestDTO = new RequestDTO();
		UserTypeDTO authorityUserTypeId = custUserDTO.getUserTypeId();
		
		requestDTO.setMemberId(empUserDTO);
		requestDTO.setRequestType("VIEW_TRANSACTION");
		requestDTO.setStatus("NEW");
		requestDTO.setRequestDate(new Date());
		requestDTO.setProcessedDate(null);
		requestDTO.setAuthorizingMemberId(customerId);		
		requestDTO.setAuthorityUserTypeId(authorityUserTypeId.getUserTypeId());		
		requestDTO.setAuthorizingAuthority(authorityUserTypeId.getUserType());
		requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
		if(requestList!=null)
		{
			for (RequestDTO req : requestList)
			{
				if(req.getRequestType().equals("VIEW_TRANSACTION") && req.getAuthorizingMemberId()==customerId)
				{
					return false;
				}
				
			}
			requestList.add(requestDTO);

		}
		else
		{
			requestList= new ArrayList<RequestDTO>();
			requestList.add(requestDTO);
		}
		return true;
	}

	
	@Transactional
	@Override
	public List<RequestDTO> getTransactionRequestList(int memberId){
		
		List<RequestDTO> transactionRequestList = employeeUserDAO.displayEmployeeUserTransactionDAO(memberId);
		return transactionRequestList;
	}
	
	
	@Transactional
	@Override
	public List<TransactionDTO> getAllTransactions(int memberId){
		
		List<TransactionDTO> transactionRequestList = employeeUserDAO.getTransactionListForCustomer(memberId);
		return transactionRequestList;
	}
	

	@Transactional
	@Override
	public List<RequestDTO> getTransactionList(int memberId){
		
		List<RequestDTO> transactionList = employeeUserDAO.displayEmployeeUserTransactionDAO(memberId);
		return transactionList ;
	}
	
	
	@Transactional
	@Override
	public boolean getViewRequestList(long requestId)
	{
		return false;

	}
	
	@Transactional
	@Override
	public boolean authorizeCreditTransaction(long requestId){
		return false;
	}
	
	@Transactional
	@Override
	public boolean authorizePaymentTransaction(long requsetId){
		return false;
	}

	@Transactional
	@Override
	public List<PaymentRequestDTO> getPaymentRequest() {
		// TODO Auto-generated method stub
		List<PaymentRequestDTO> requestList = employeeUserDAO.getPaymentRequest();
		/*for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}*/
		return requestList;
	}

	@Transactional
	@Override
	public List<TransactionDTO> getTransactionRequest() {
		// TODO Auto-generated method stub
		List<TransactionDTO> requestList = employeeUserDAO.getTransactionRequest();
		/*for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}*/
		return requestList;
	}

	@Transactional
	@Override
	public String updatePaymentRequest(long paymentRequestId, String status) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentDTO = manageExternalUserAccountDAO
				.getPaymentRequestByPaymentId(paymentRequestId);
		if(paymentDTO==null)
			return "NOTFOUND";
		paymentDTO.setStatus(status);
		if (manageExternalUserAccountDAO.updatePaymentRequest(paymentDTO))
			return "success";
		else
			return "fail";
	}
	
	
	@Transactional
	@Override
	public String updateTransactionRequest(long transactionRequestId,String status) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = manageExternalUserAccountDAO.getTransactionByTransactionId(transactionRequestId);
		if(transactionDTO==null)
		{
			return "NOTFOUND";
		}
		transactionDTO.setIsAuthorized(true);

		transactionDTO.setStatus(status);
		transactionDTO.setProcessedDate(new Date());
		if (manageExternalUserAccountDAO.updateTransactionRequest(transactionDTO))
			return "success";
		else
			return "fail";
	}
	
	@Transactional
	@Override
	public int getCustomerId (long requestId){
		RequestDTO myRequest = requestDAO.getRequestByRequestId(requestId);
		int customerId = myRequest.getAuthorizingMemberId();
		return customerId;

	}
	
	@Transactional
	@Override
	public boolean makeCredit(int memberID, double amount) {
		// TODO Auto-generated method stub
		
		return employeeUserDAO.makeCredit(memberID, amount);
	}

	@Transactional
	@Override
	public boolean makeDebit(int memberID, double amount) {
		// TODO Auto-generated method stub
		
		
		return employeeUserDAO.makeDebit(memberID, amount);
	}

	@Override
	@Transactional
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId) {
		// TODO Auto-generated method stub
		PaymentRequestDTO paymentRequestDTO = employeeUserDAO.getPaymentDTOById(paymentRequestId);
		
		return paymentRequestDTO;
	}

	@Override
	@Transactional
	public TransactionDTO getTransactionDTOById(long transactionRequestId) {
		// TODO Auto-generated method stub
		TransactionDTO transactionDTO = employeeUserDAO.getTransactionDTOById(transactionRequestId);
		return transactionDTO;
	}

	@Override
	@Transactional
	public int getMemberIdByAccount(long accountNumber) {
		// TODO Auto-generated method stub
		int foundAccountNumber = employeeUserDAO.getMemberIdByAccount(accountNumber);
		return foundAccountNumber;
	
		
	}

	@Override
	@Transactional	
	public long getAccountNo(int memberId)
	{
		long accountNo = employeeUserDAO.returnCustomerAccountNo(memberId);
		return accountNo;
	}

	@Override
	@Transactional
	public List<TransactionReviewDTO> getTransactionReviewList() {
		// TODO Auto-generated method stub
		List<TransactionReviewDTO> reviewList = employeeUserDAO.getTransactionReviewRequests();
		List<TransactionReviewDTO> revList = new ArrayList<TransactionReviewDTO>();
		if(reviewList != null)
		{
			for(TransactionReviewDTO review : reviewList)
			{
				if(review.getTransactionType().equals("transfer"))
				{
					revList.add(review);
				}
			}
		}
		return revList;
	}
	
	@Transactional
	public boolean approveTransactionReview(long transactionReviewId, int memberId)
	{
		double oldAmount;
		double newAmount;
		TransactionReviewDTO reviewDTO = employeeUserDAO.getTransactionReviewDTO(transactionReviewId);
		if(reviewDTO != null)
		{
			TransactionDTO txnDTO = manageExternalUserAccountDAO.getTransactionDTO(reviewDTO.getTransactionId());
			if(txnDTO != null)
			{
				oldAmount = txnDTO.getAmount();
				newAmount = reviewDTO.getAmount();
				if(oldAmount < newAmount)
				{
					String result = extUserService.makeDebitTransaction(reviewDTO.getCustMemberId().getMemberId(), newAmount-oldAmount, reviewDTO.getCustMemberId().getMemberId(), "Debit");
					if(result.equals("success"))
					{
						txnDTO.setAmount(newAmount);
						txnDTO.setStatus("PENDING_BANK");
						reviewDTO.setStatus("APPROVED");
						reviewDTO.setAuthorizingMemberId(memberId);
						reviewDTO.setProcessedDate(new Date());
						boolean isUpadted = employeeUserDAO.updateTransaction(txnDTO);
						boolean isChanged = employeeUserDAO.updateTransactionReviewDTO(reviewDTO);
						if(isChanged && isUpadted)
						{
							return true;
						}
					}
					
				}
			}
		}
		return false;
	}

	@Override
	@Transactional
	public boolean declineTransactionReview(long transactionReviewId,
			int memberId) {
		// TODO Auto-generated method stub
		TransactionReviewDTO reviewDTO = employeeUserDAO.getTransactionReviewDTO(transactionReviewId);
		if(reviewDTO != null)
		{
			System.out.println("New ");
			reviewDTO.setStatus("DECLINED");
			reviewDTO.setProcessedDate(new Date());
			reviewDTO.setAuthorizingMemberId(memberId);
			boolean isDeclined = employeeUserDAO.updateTransactionReviewDTO(reviewDTO);
			if(isDeclined)
			{
				System.out.println("Declined");
				return true;
			}
		}
		return false;
	}


	@Override
	@Transactional	
	public List<UserDTO> getInternalUserListForAdmin() {
		List<UserDTO> memberList = employeeUserDAO.getInternalUsersListForDisplay();
		return memberList;
	}

}
