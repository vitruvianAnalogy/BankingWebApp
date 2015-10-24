
package edu.asu.safemoney.dao.impln;
import org.springframework.security.crypto.bcrypt.BCrypt;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.SecurityQuestionsModel;
import edu.asu.safemoney.model.UserModel;

@Repository
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public String getSiteKey(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = ((LoginDTO) query.uniqueResult());
		String siteKey = null;
		if(loginDTO != null)
		{
			siteKey = loginDTO.getSiteKey();
		}
		System.out.println("SiteKey = " + siteKey);
		return siteKey;
	}

	@Override
	public UserDTO getUserByMemberId(int memberId) 
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM UserDTO u WHERE u.memberId = :memberId";
		Query query = session.createQuery(queryString);
		query.setParameter("memberId", memberId);
		UserDTO userDTO = ((UserDTO) query.uniqueResult());
		return userDTO;
		
	}
	
	@Override
	public LoginDTO getLoginDetails(String userName) 
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = ((LoginDTO) query.uniqueResult());
		return loginDTO;
		
	}
	
	public int getMemberIdByUserName(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM LoginDTO l WHERE l.userName = :userName";
		Query query = session.createQuery(queryString);
		query.setParameter("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			return loginDTO.getUserDTO().getMemberId();
		}
		return -1;
	}
	
	public boolean createUser(UserModel user)
	{
		UserDTO userDTO = copyToUserDTO(user);
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(userDTO);
			
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUserName(user.getUserName());
			String plainPassword = user.getPassword();
			//password encoding
			String pw_hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
			loginDTO.setPassword(pw_hash);
			loginDTO.setSiteKey(user.getSiteKey());
			loginDTO.setUserDTO(userDTO);
			loginDTO.setMemberId(userDTO.getMemberId());
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			loginDTO.setIsEnabled(false);
			userDTO.setLoginDTO(loginDTO);
			
			session.save(userDTO);
			
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public UserDTO copyToUserDTO(UserModel user)
	{
		
		UserDTO userDTO = new UserDTO();
		userDTO.setMemberId((int) ExternalUserHelper.generateRandomNumber());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setContactNo(user.getContactNo());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setAddress1(user.getAddress1());
		userDTO.setAddress2(user.getAddress2());
		userDTO.setCity(user.getCity());
		userDTO.setState(user.getState());
		userDTO.setZip(user.getZip());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setAge(user.getAge());
		userDTO.setSsn(user.getSsn());
		userDTO.setIsCustomer(user.getIsCustomer());
		userDTO.setSecQuestion1(user.getSecQuestion1());
		userDTO.setSecAnswer1(user.getSecAnswer1());
		userDTO.setSecQuestion2(user.getSecQuestion2());
		userDTO.setSecAnswer2(user.getSecAnswer2());
		userDTO.setSecQuestion3(user.getSecQuestion3());
		userDTO.setSecAnswer3(user.getSecAnswer3());
		userDTO.setCreatedBy(user.getCreatedBy());
		userDTO.setCreatedDate(user.getCreatedDate());
		userDTO.setExpiryDate(user.getExpiryDate());
		userDTO.setIsActive("true");
		userDTO.setIsEmployee(user.isEmployee());
		userDTO.setDesignation(user.getDesignation());
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserTypeDTO.findByUserTypeId").setInteger("userTypeId", user.getUserTypeId());
		UserTypeDTO userTypeDTO = (UserTypeDTO) query.uniqueResult();
		
		userDTO.setUserTypeId(userTypeDTO);
		
		RequestDTO requestDTO = new RequestDTO();
		requestDTO.setMemberId(userDTO);
		requestDTO.setAuthorizingAuthority("ADMIN");
		requestDTO.setRequestType("CREATE_ACCOUNT");
		requestDTO.setAuthorityUserTypeId(123);
		requestDTO.setStatus("NEW");
		requestDTO.setRequestDate(new Date());
		requestDTO.setRequestId(ExternalUserHelper.generateRandomNumber());
		
		List<RequestDTO> requestList = new ArrayList<RequestDTO>();
		requestList.add(requestDTO);
		
		userDTO.setRequestDTOList(requestList);
		
		
		System.out.println("fisrtNAme = " + user.getFirstName() + " lastName = "  + user.getFirstName());
		return userDTO;
	}
	
	public boolean isEmailExists(String emailId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UserDTO.findByEmailId").setString("emailId", emailId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		if(userDTO != null)
		{
			return true;
		} else {
			return false;
		}
	}
	
	
	public String getEmail(String userName)
	{
		System.out.println("inEmail");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		System.out.println(loginDTO.getMemberId());
		Query query1 = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", loginDTO.getMemberId());
		UserDTO userDTO = (UserDTO) query1.uniqueResult();
		System.out.println("e-mail dao"+ userDTO.getEmailId());
		return userDTO.getEmailId();
	}
	
	public boolean setOtpCode(String userName, Long optCode)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		
		
		loginDTO.setOtp(optCode);
		
		//Setting Date and Time
		java.util.Date date= new java.util.Date();
		loginDTO.setOtpDate(new Timestamp(date.getTime()));
		System.out.println("dao"+ optCode);
		session.saveOrUpdate(loginDTO);
		return true;
	
		
	}
	
	public SecurityQuestionsModel getSecurityQuestions(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		int memberId = loginDTO.getMemberId();
		query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		SecurityQuestionsModel secModel  = new SecurityQuestionsModel();
		secModel.setQuestion1(userDTO.getSecQuestion1());
		secModel.setQuestion2(userDTO.getSecQuestion2());
		secModel.setQuestion3(userDTO.getSecQuestion3());
		return secModel;
		
	}
	
	public Long getOtpCode(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		
		
		if(loginDTO.getOtp()!=-1)
			return loginDTO.getOtp();
		
		else return (long) -1;
	}
	
	public String getOtpTime(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		String temp = loginDTO.getOtpDate().toString();
		if(temp!=null)
			return temp;
		else
			return null;
	}
	
	public SecurityQuestionsModel getSecurityAnswers(String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		int memberId = loginDTO.getMemberId();
		query = session.getNamedQuery("UserDTO.findByMemberId").setInteger("memberId", memberId);
		UserDTO userDTO = (UserDTO) query.uniqueResult();
		SecurityQuestionsModel secModel  = new SecurityQuestionsModel();
		secModel.setAnswer1(userDTO.getSecAnswer1());
		secModel.setAnswer2(userDTO.getSecAnswer2());
		secModel.setAnswer3(userDTO.getSecAnswer3());
		return secModel;
		
	}
	public void changePassword(String userName, String password)
	{
		
	
		Session session = sessionFactory.getCurrentSession();
		//password encoding
		String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
		//loginDTO.setPassword(pw_hash);
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		loginDTO.setPassword(pw_hash);
		loginDTO.setIsAccountNonLocked(true);
		session.saveOrUpdate(loginDTO);
	}
		

		
	public boolean updateLoginFailureAttempts(String userName)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			int failedCount = loginDTO.getFailedAttemptCount();
			if(failedCount >= 3)
			{
				loginDTO.setIsAccountNonLocked(false);
			}
			else
			{
				failedCount += 1;
				loginDTO.setFailedAttemptCount(failedCount);
			}
			session.saveOrUpdate(loginDTO);
			tx.commit();
			session.close();
			return true;
		}
		return false;
		
	} 
	
	public boolean resetFailureAttempts(String userName)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			session.saveOrUpdate(loginDTO);
			session.close();
			tx.commit();
			return true;
		}
		return false;
	}
	
	
	public boolean setLoginDate(String userName)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		if(loginDTO != null)
		{
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			session.saveOrUpdate(loginDTO);
			session.close();
			tx.commit();
			return true;
		}
		return false;
	}
	
	
	public int getFailureAttemptCount(String userName)
	{
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("LoginDTO.findByUserName").setString("userName", userName);
		LoginDTO loginDTO = (LoginDTO) query.uniqueResult();
		session.close();
		if(loginDTO != null)
		{
			return loginDTO.getFailedAttemptCount();
		}
		return -1;
	}
	
	public boolean createEmployee(UserModel userModel){
		UserDTO userDTO = copyToUserDTO(userModel);
		System.out.println("entered dao");
		try
		{
			Session session = sessionFactory.getCurrentSession();
			session.persist(userDTO);
			
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUserName(userModel.getUserName());
			// loginDTO.setPassword(userModel.getPassword());
			String plainPassword = userModel.getPassword();
			//password encoding
			String pw_hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
			loginDTO.setPassword(pw_hash);
			
			loginDTO.setSiteKey(userModel.getSiteKey());
			loginDTO.setUserDTO(userDTO);
			loginDTO.setMemberId(userDTO.getMemberId());
			loginDTO.setFailedAttemptCount(0);
			loginDTO.setIsAccountNonLocked(true);
			loginDTO.setIsEnabled(true);
			userDTO.setLoginDTO(loginDTO);
			
			session.save(userDTO);
			System.out.println("returned true @ dao");	
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("returned false @ dao");
			return false;
		}
		
	}
	

}

