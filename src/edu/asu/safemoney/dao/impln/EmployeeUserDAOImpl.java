
package edu.asu.safemoney.dao.impln;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.EmployeeUserDAO;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;

@Repository
public class EmployeeUserDAOImpl implements EmployeeUserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ManageExternalUserAccountService manageExternalUserAccountService;
	
	@Autowired
	ManageExternalUserAccountDAO manageExternalUserAccountDAO;
	
	@Transactional
	@Override
	public List<PaymentRequestDTO> getPaymentRequest() {
		// TODO Auto-generated method stub
		List<PaymentRequestDTO> paymentRequest = new ArrayList<PaymentRequestDTO>();

		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("PaymentRequestDTO.findByStatus")
				.setString("status", "PENDING_BANK");
		List requests = query.list();
		if (requests != null) {
			for (Object request : requests) {
				PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) request;
				paymentRequest.add(paymentRequestDTO);
			}
			System.out.println("Found some Payments");
		}

		return paymentRequest;

	}

	@Override
	public List<TransactionDTO> getTransactionRequest() {
		// TODO Auto-generated method stub
		List<TransactionDTO> transactionRequest = new ArrayList<TransactionDTO>();

		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("TransactionDTO.findByStatus")
				.setString("status", "PENDING_BANK");
		List requests = query.list();
		if (requests != null) {
			for (Object request : requests) {
				TransactionDTO transactionRequestDTO = (TransactionDTO) request;
				if(!transactionRequestDTO.getIsCritical())
				{
					transactionRequest.add(transactionRequestDTO);
				}
			}
			System.out.println("Found some transactions");
		}

		return transactionRequest;
	}

	@Override
	public PaymentRequestDTO getPaymentDTOById(long paymentRequestId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		BigInteger bi = BigInteger.valueOf(paymentRequestId);
		Query query = session.getNamedQuery("PaymentRequestDTO.findByPaymentId").setBigInteger("paymentId",bi );
		PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) query.uniqueResult();
		return paymentRequestDTO;
		
		
	}
	
	@Override
	public TransactionDTO getTransactionDTOById(long transactionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		BigInteger bi = BigInteger.valueOf(transactionId);
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setBigInteger("transactionId",bi );
		TransactionDTO transactionDTO = (TransactionDTO) query.uniqueResult();
		return transactionDTO;
		
	}

	@Override
	public boolean makeDebit(int memberID, double amount) {
		// TODO Auto-generated method stub
		double balance = manageExternalUserAccountService.getAccountBalance(memberID);
		balance -=amount;
		boolean result = manageExternalUserAccountDAO.updateAccountBalance(memberID, balance);
		return result;
	}

	@Override
	public boolean makeCredit(int memberID, double amount) {
		// TODO Auto-generated method stub
		double balance = manageExternalUserAccountService.getAccountBalance(memberID);
		balance +=amount;
		boolean result = manageExternalUserAccountDAO.updateAccountBalance(memberID, balance);
		return result;
	}

	@Override
	public int getMemberIdByAccount(long accountNumber) {
		// TODO Auto-generated method stub
		
		BigInteger bi = BigInteger.valueOf(accountNumber);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("AccountDTO.findByAccountNo").setBigInteger("accountNo", bi);
		AccountDTO accountDTO = (AccountDTO) query.uniqueResult();
		UserDTO user = accountDTO.getMemberId();
		int memberId=user.getMemberId();
		return memberId;
	}

	public List<RequestDTO> displayEmployeeUserTransactionDAO(int memberId){
		// query for user details using userName and save them in a list. 
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM RequestDTO r WHERE r.memberId = :memberId AND r.requestType = :requestType");
		query.setInteger("memberId", memberId);
		query.setString("requestType", "VIEW_TRANSACTION");
		List<RequestDTO> transactionList = (List<RequestDTO>)query.list();
		return transactionList;
	}
	
	public List<RequestDTO> displayEmployeeUserAccountDAO(int memberId){
		// query for user details using userName and save them in a list. 
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM RequestDTO r WHERE r.memberId = :memberId AND r.requestType = :requestType");
		query.setInteger("memberId", memberId);
		query.setString("requestType", "VIEW_ACCOUNT");
		List<RequestDTO> requestList = (List<RequestDTO>)query.list();
		return requestList;
	}
	
	public long returnCustomerAccountNo(int memberId){
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM AccountDTO r WHERE r.memberId = :memberId");
		query.setInteger("memberId", memberId);
		List<AccountDTO> requestList = (List<AccountDTO>)query.list();
		return requestList.get(0).getAccountNo();
	}
	
	public List<TransactionDTO> getTransactionListForCustomer(int memberId){
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM TransactionDTO r WHERE r.memberId = :memberId");
		query.setInteger("memberId", memberId);
		List<TransactionDTO> transactionList = (List<TransactionDTO>)query.list();
		return transactionList;
	}

	@Override
	public List<TransactionReviewDTO> getTransactionReviewRequests() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionReviewDTO.findByStatus").setString("status", "PENDING_BANK");
		List<TransactionReviewDTO> reviewList = (List<TransactionReviewDTO>) query.list();
		System.out.println("review" + reviewList.get(0).getAmount());
		return reviewList;
	}

	@Override
	public TransactionReviewDTO getTransactionReviewDTO(long transactionReviewId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionReviewDTO.findByTransactionReviewId").setLong("transactionReviewId", transactionReviewId);
		TransactionReviewDTO reviewDTO = (TransactionReviewDTO) query.uniqueResult();
		return reviewDTO;
	}

	@Override
	public boolean updateTransaction(TransactionDTO txnDTO) {
		// TODO Auto-generated method stub
		try
		{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(txnDTO);
		return true;
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

	@Override
	public boolean updateTransactionReviewDTO(TransactionReviewDTO reviewDTO) {
		// TODO Auto-generated method stub
		try
		{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reviewDTO);
		return true;
		}
		catch(Exception e)
		{
		}
		return false;
	}
	
	public ModifyUserModel getEmployeeDetails(int memberId){
	Session session= sessionFactory.getCurrentSession();
	Query query=session.createQuery("FROM UserDTO u WHERE u.memberId = :memberId");
	query.setInteger("memberId", memberId);
	UserDTO userDTO= (UserDTO) query.uniqueResult();
	ModifyUserModel modifyUserModel= new ModifyUserModel();
	modifyUserModel.setAddress1(userDTO.getAddress1());
	modifyUserModel.setAddress2(userDTO.getAddress2());
	modifyUserModel.setCity(userDTO.getCity());
	modifyUserModel.setContactNo(userDTO.getContactNo());
	modifyUserModel.setEmailId(userDTO.getEmailId());
	modifyUserModel.setMemberId(memberId);
	modifyUserModel.setState(userDTO.getState());
	modifyUserModel.setZip(userDTO.getZip());
	return modifyUserModel;
	}

	@Override
	public List<UserDTO> getInternalUsersListForDisplay() {
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM UserDTO r WHERE r.isEmployee = :isEmployee");
		query.setBoolean("isEmployee", true);
		List<UserDTO> memberList = (List<UserDTO>)query.list();
		return memberList;
	}

}
