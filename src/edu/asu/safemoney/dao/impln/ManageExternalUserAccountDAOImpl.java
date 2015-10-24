package edu.asu.safemoney.dao.impln;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;


@Repository
public class ManageExternalUserAccountDAOImpl implements ManageExternalUserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LoginDAOImpl loginDAOImpl;
	

	public boolean updateUser(ModifyUserModel modifyUserModel){
		
		UserDTO userDTO= copyToUserDTO(modifyUserModel);
		if(userDTO != null)
		{
			try
			{
				Session session= sessionFactory.getCurrentSession();
				session.saveOrUpdate(userDTO);
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	public boolean createTransactionRequest(TransactionReviewDTO transactionReviewDTO){
		try{
			Session session= sessionFactory.getCurrentSession();
			session.save(transactionReviewDTO);
			return true;
		}
		catch(Exception e){
			return false;	
		}
	}
	
	
	public UserDTO displayUserAccountDAO(int memberId){
		// query for user details using userName and save them in a list. 
		Session session= sessionFactory.getCurrentSession();
		Query query= session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO= (UserDTO)query.uniqueResult();
		return userDTO;
	}
	
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountNo(accountDTO.getAccountNo());
		accountModel.setAmount(accountDTO.getAmount());
		accountModel.setFirstName(userDTO.getFirstName());
		accountModel.setLastName(userDTO.getLastName());
		return accountModel;
	}
	
	
	public AccountDTO copyToAccountDTO(AccountModel account, int memberId)
	{
		
		
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		AccountDTO accountDTO = new AccountDTO();
		
		accountDTO.setAccountNo(account.getAccountNo());
		accountDTO.setAmount(account.getAmount());
		accountDTO.setMemberId(userDTO);
		accountDTO.setIsActive("active");//??what does active mean?
		//accountDTO.s
		
		return accountDTO;
		//accountDTO.setIsActive(account.ge); what is active?
		//accountDTO.setMemberId(account.ge); member ID.....
	}
	
	
	public boolean createTransaction(TransactionDTO txnDTO)
	{
		try
		{
			Session session= sessionFactory.getCurrentSession();
			session.save(txnDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateAccountBalance(int memberId, double amount) {
		// TODO Auto-generated method stu
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		if(userDTO == null)
		{
			return false;
		}else
		{
			AccountDTO accountDTO = userDTO.getAccountDTOList().get(0);
			if(accountDTO == null)
			{
				return false;
			}
			else
			{
				try
				{
					
					accountDTO.setAmount(amount);
					session.saveOrUpdate(accountDTO);
					return true;
				}
				catch(Exception e)
				{
					return false;
				}
			}
			
		}
		
	}

	@Override
	public boolean createAccount(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(accountDTO);
			return true;
		} catch(Exception e)
		{
			return false;
		}
	}
	

	public UserDTO copyToUserDTO(ModifyUserModel modifyUserModel)
	{
		UserDTO userDTO = displayUserAccountDAO(modifyUserModel.getMemberId());
		if(userDTO != null)
		{
			userDTO.setContactNo(modifyUserModel.getContactNo());
			userDTO.setEmailId(modifyUserModel.getEmailId());
			userDTO.setAddress1(modifyUserModel.getAddress1());
			userDTO.setAddress2(modifyUserModel.getAddress2());
			userDTO.setCity(modifyUserModel.getCity());
			userDTO.setState(modifyUserModel.getState());
			userDTO.setZip(modifyUserModel.getZip());
			return userDTO;
		}
		return null;
	}
		
	public int getMemberIdByAccount(long accountNumber)
	{
		BigInteger bi = BigInteger.valueOf(accountNumber);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("AccountDTO.findByAccountNo").setBigInteger("accountNo", bi);
		AccountDTO accountDTO = (AccountDTO) query.uniqueResult();
		UserDTO user = accountDTO.getMemberId();
		int memberId=user.getMemberId();
		return memberId;
	}
	
	@Override
	public boolean deleteExtUserAccount(int memberId) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("LoginDTO.findByMemberId")
					.setInteger("memberId", memberId);
			LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
			if (loginDTO != null && loginDTO.getIsEnabled()) {
				loginDTO.setIsEnabled(false);
				session.saveOrUpdate(loginDTO);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
	
	
	@Override
	public List<PaymentRequestDTO> getPaymentRequest(int memberId)
	{
		//different user type has thfferent precedure
		List<PaymentRequestDTO> paymentRequest = new ArrayList<PaymentRequestDTO>();
		
		
		Session session = sessionFactory.getCurrentSession();
		UserDTO initiater = displayUserAccountDAO(memberId);
		UserTypeDTO initiaterType = initiater.getUserTypeId();
		AccountModel initiaterAccountModel =  getAccountDetails(memberId);
		BigInteger bi = BigInteger.valueOf(initiaterAccountModel.getAccountNo());
		//initiater.geta

		if(initiaterType.getUserTypeId()==366)//Merchant
		{
			//find the account number
			
			Query query = session.getNamedQuery("PaymentRequestDTO.findByMerchantAccountId").setBigInteger("merchantAccountId", bi);
			List requests = query.list();
			if(requests != null)
			{
				for(Object request : requests)
				{	
					PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) request;
					paymentRequest.add(paymentRequestDTO);
				}
			}
			
			Query query2 = session.getNamedQuery("PaymentRequestDTO.findByAuthorizerMemberId").setInteger("authorizerMemberId", memberId);
			List requests2 = query2.list();
			if(requests2 != null)
			{
				for(Object request2 : requests2)
				{	
					PaymentRequestDTO paymentRequestDTO2 = (PaymentRequestDTO) request2;
					paymentRequest.add(paymentRequestDTO2);
				}
			}
			
			return paymentRequest;
		}
		else if(initiaterType.getUserTypeId()==322)
		{
			Query query = session.getNamedQuery("PaymentRequestDTO.findByAuthorizerMemberId").setInteger("authorizerMemberId", memberId);
			List requests = query.list();
			if(requests != null)
			{
				for(Object request : requests)
				{	
					PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) request;
					paymentRequest.add(paymentRequestDTO);
				}
			}
			
			return paymentRequest;
		}
		else
			return null;
		
		/*List<RequestDTO> requestList = new ArrayList<RequestDTO>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByAuthorityUserTypeId").setInteger("authorityUserTypeId", 123);
		List requests = query.list();
		if(requests != null)
		{
			for(Object request : requests)
			{	
				RequestDTO requestDTO = (RequestDTO) request;
				requestList.add(requestDTO);
			}
		}
		
		return requestList;*/
	}

	@Override
	public PaymentRequestDTO getPaymentRequestByPaymentId(long paymentId) {
		// TODO Auto-generated method stub
		
		//PaymentRequestDTO paymentRequestDTO =  
		BigInteger requestId = BigInteger.valueOf(paymentId);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("PaymentRequestDTO.findByPaymentId").setBigInteger("paymentId", requestId);
		PaymentRequestDTO paymentRequestDTO =   (PaymentRequestDTO) query.uniqueResult();
		return paymentRequestDTO;
		
		
	}
	
	@Override
	public TransactionDTO getTransactionByTransactionId(long transactionRequestId) {
		// TODO Auto-generated method stub
		BigInteger requestId = BigInteger.valueOf(transactionRequestId);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setBigInteger("transactionId", requestId);
		TransactionDTO transactionRequestDTO =   (TransactionDTO) query.uniqueResult();
		return transactionRequestDTO;
	}

	@Override
	public boolean updatePaymentRequest(PaymentRequestDTO paymentDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(paymentDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Override
	public boolean updateTransactionRequest(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(transactionDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean addPaymentRequest(PaymentRequestDTO paymentRequestDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(paymentRequestDTO);
			return true;
		} catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean findAccount(long accountNumber) {
		// TODO Auto-generated method stub
		BigInteger account = BigInteger.valueOf(accountNumber);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("AccountDTO.findByAccountNo").setBigInteger("accountNo", account);
		AccountDTO accountDTO =   (AccountDTO) query.uniqueResult();
		
		if(accountDTO==null)
			return false;
		else 
			return true;
	}
	
	@Override
	public boolean findIsEnabled(long accountNumber) {
		// TODO Auto-generated method stub
		
		BigInteger account = BigInteger.valueOf(accountNumber);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("AccountDTO.findByAccountNo").setBigInteger("accountNo", account);
		AccountDTO accountDTO =   (AccountDTO) query.uniqueResult();
		
		UserDTO userDTO = accountDTO.getMemberId();
		
		session = sessionFactory.getCurrentSession();
		query = session.getNamedQuery("LoginDTO.findByMemberId").setInteger("memberId", userDTO.getMemberId());
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		return loginDTO.getIsEnabled();
	}

	
	public boolean addTransactionReview(
			TransactionReviewDTO transactionReviewDTO) {
		// TODO Auto-generated method stub
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.save(transactionReviewDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateTransaction(long transactionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setLong("transactionId", transactionId);
		try
		{
			TransactionDTO transactionDTO = (TransactionDTO) query.uniqueResult();
			transactionDTO.setStatus("UNDER_REVIEW");
			session.saveOrUpdate(transactionDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TransactionDTO getTransactionDTO(long transactionId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setLong("transactionId", transactionId);
		TransactionDTO transactionDTO = null;
		try
		{
			transactionDTO = (TransactionDTO) query.uniqueResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return transactionDTO;
	}

	@Override
	public boolean deleteTransaction(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.delete(transactionDTO);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<UserDTO> getPIIAuthorizedUserAccountsDTO()
	{
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM UserDTO r WHERE r.isPIIAuthorized = :isPIIAuthorized AND r.isEmployee = :isEmployee");
		query.setBoolean("isPIIAuthorized", true);
		query.setBoolean("isEmployee", false);
		List<UserDTO> userDTOList = (List<UserDTO>)query.list();
		return userDTOList;
	}
	
	@Override
	public List<UserDTO> getMembersListForDisplay()
	{
		Session session= sessionFactory.getCurrentSession();
		Query query= session.createQuery("FROM UserDTO r WHERE r.isEmployee = :isEmployee");
		query.setBoolean("isEmployee", false);
		//query.setInteger("userTypeId",123);
		List<UserDTO> memberList = (List<UserDTO>)query.list();
		return memberList;
	}
	
	@Override
	public List<RequestDTO> getViewAccountRequestsForCustomer(int memberId){
		Session session= sessionFactory.getCurrentSession();		
		Query query= session.createQuery("FROM RequestDTO r WHERE r.authorizingMemberId = :memberId AND r.requestType = :requestType");
		query.setInteger("memberId", memberId);
		query.setString("requestType", "VIEW_ACCOUNT");
		List<RequestDTO> accountsRequests =(List<RequestDTO>)query.list();
		return accountsRequests;
	}
	
	@Override
	public List<RequestDTO> getViewTransactionsRequestsForCustomer(int memberId){
		Session session= sessionFactory.getCurrentSession();		
		Query query= session.createQuery("FROM RequestDTO r WHERE r.authorizingMemberId = :memberId AND r.requestType = :requestType");
		query.setInteger("memberId", memberId);
		query.setString("requestType", "VIEW_TRANSACTION");
		List<RequestDTO> accountsRequests =(List<RequestDTO>)query.list();
		return accountsRequests;
	}
	
	@Override
	public boolean authorizeViewAccountRequest(long requestId)
	{
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByRequestId").setLong("requestId", requestId);
		RequestDTO approveRequestDTO = (RequestDTO) query.uniqueResult();
		if(approveRequestDTO == null)
		{
			return false;
		}
		approveRequestDTO.setStatus("APPROVED");
		return true;
	}
	
	@Override
	public boolean authorizeViewTransactionsRequest(long requestId)
	{
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByRequestId").setLong("requestId", requestId);
		RequestDTO approveRequestDTO = (RequestDTO) query.uniqueResult();
		if(approveRequestDTO == null)
		{
			return false;
		}
		approveRequestDTO.setStatus("APPROVED");
		return true;
	}
	
	@Override
	public boolean declineViewAccountRequest(long requestId)
	{
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByRequestId").setLong("requestId", requestId);
		RequestDTO approveRequestDTO = (RequestDTO) query.uniqueResult();
		if(approveRequestDTO == null)
		{
			return false;
		}
		approveRequestDTO.setStatus("DECLINED");
		return true;
	}

	@Override
	public boolean isTransactionExists(long transactionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("TransactionDTO.findByTransactionId").setLong("transactionId", transactionId);
		try
		{
			TransactionDTO tDTO = (TransactionDTO) query.uniqueResult();
			if(tDTO != null)
			{
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean updateIsEnabled(int memberId, boolean isEnabled)
	{
		Session session = sessionFactory.getCurrentSession();
		try
		{
			Query query = session.getNamedQuery("LoginDTO.findByMemberId").setInteger("memberId", memberId);
			LoginDTO loginDTO = (LoginDTO)  query.uniqueResult();
			loginDTO.setIsEnabled(isEnabled);
			session.saveOrUpdate(loginDTO);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	

	@Override
	public boolean declineViewTransactionsRequest(long requestId)
	{
		Session session= sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("RequestDTO.findByRequestId").setLong("requestId", requestId);
		RequestDTO approveRequestDTO = (RequestDTO) query.uniqueResult();
		if(approveRequestDTO == null)
		{
			return false;
		}
		approveRequestDTO.setStatus("DECLINED");
		return true;
	}
}
