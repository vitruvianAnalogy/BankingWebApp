package edu.asu.safemoney.service.impln;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.controller.GlobalExceptionHandlerController;
import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.dto.UserTypeDTO;
import edu.asu.safemoney.model.SecurityQuestionsModel;
import edu.asu.safemoney.helper.PKICertificateHelper;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {


	public static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private RequestDAO requestDAO;
    
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = null;
	
		System.out.println("userName: " + userName);
		if (userName != null && !userName.isEmpty()) {
			LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
			System.out.println("loginDTO Password: " + loginDTO.getPassword());
			user = getUserFromLoginDTO(loginDTO);
		}
		return user;
	}

	@Transactional
	public User getUserFromLoginDTO(LoginDTO loginDTO) {
		User user = null;
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		UserDTO uDTO = loginDTO.getUserDTO();
		if (loginDTO != null) {
			String userName = loginDTO.getUserName();
			String password = loginDTO.getPassword();
			System.out.println("password" + password);
			boolean isEnabled = loginDTO.getIsEnabled();
			boolean isAcctNonExpired = true;
			boolean isCredentialsNonExpired = true;
			boolean isAcctNonLocked = loginDTO.getIsAccountNonLocked();
			if (uDTO != null) {
				System.out.println("not null");
				UserTypeDTO userTypeDTO = uDTO.getUserTypeId();
				System.out.println("getUserType"
						+ userTypeDTO.getUserType());
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						userTypeDTO.getUserType());
				authorityList.add(authority);
				user = new User(userName, password, isEnabled,
						isAcctNonExpired, isCredentialsNonExpired,
						isAcctNonLocked, authorityList);

			}
		}

		return user;
	}

	@Override
	@Transactional
	public String getSiteKeyForUserName(String userName) {
		// TODO Auto-generated method stub
		String siteKey = "";
		if (userName != null && !userName.isEmpty()) {
			siteKey = loginDAO.getSiteKey(userName);
			if(siteKey != null && !siteKey.isEmpty())
			{
				LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
				if(loginDTO != null)
				{
					//
				}
			}
		}
		return siteKey;
	}

	@Transactional
	public boolean createUser(UserModel userModel) {
		boolean isCreationSuccess= false;
		if(validateUser(userModel))
		{

			return false;
		}
		else
		{
			userModel.setCreatedBy("SYSTEM");
			userModel.setCreatedDate(new Date());
			userModel.setExpiryDate(calcuateExpiryDate());
			userModel.setIsActive("true");
			if (userModel.getUserType().equalsIgnoreCase("indCust")) {
				userModel.setUserType("Individual Customer");
				userModel.setUserTypeId(322);
				userModel.setIsCustomer("true");
			} else {
				userModel.setUserType("Merchant/Organization");
				userModel.setUserTypeId(366);
				userModel.setIsCustomer("false");
			}
			userModel.setEmployee(false);
			userModel.setDesignation(null);
			isCreationSuccess = loginDAO.createUser(userModel);
			if(isCreationSuccess)
			{

				/*int memberId = loginDAO.getMemberIdByUserName(userModel.getUserName());
				System.out.println("MemebrID: " + memberId);
				UserDTO userDTO = loginDAO.getUserByMemberId(memberId);
				System.out.println("UserDTO memberId " + userDTO.getMemberId());
				//UserDTO userDTO = loginDAO.copyToUserDTO(userModel);
				RequestDTO requestDTO = new RequestDTO();
				requestDTO.setMemberId(userDTO);
				requestDTO.setAuthorizingAuthority("ADMIN");
				requestDTO.setRequestType("CREATE_ACCOUNT");
				requestDTO.setStatus("NEW");
				boolean isRequestSent = requestDAO.generateRequest(requestDTO);
				if(isRequestSent)
				{
					return true;
				}
				else
				{
					return false;
				}*/
				PKICertificateHelper pkiHelper = new PKICertificateHelper();
				pkiHelper.getCertificateForUser(userModel.getUserName());
				return true;
			}
			else
			{
				return false;
			}
		}
		
	}

	private Date calcuateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 365);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
	
	@Transactional
	public int getMemberId(String userName)
	{
		int memberId = -1;
		LoginDTO loginDTO = loginDAO.getLoginDetails(userName);
		if(loginDTO != null)
		{
			memberId = loginDTO.getMemberId();
		}
		return memberId;
	}
	
	@Transactional
	public boolean validateUser(UserModel userModel){
		String siteKey = loginDAO.getSiteKey(userModel.getUserName());
		boolean emailExist= loginDAO.isEmailExists(userModel.getEmailId());
		if((siteKey != null && !siteKey.isEmpty() )|| emailExist)
		{

			return true;
		}
		else 
			return false;

	}
	
	@Transactional
	public SecurityQuestionsModel getSecurityQuestions(String userName)
	{
		return loginDAO.getSecurityQuestions(userName);
		
	}
	
	
	@Transactional
	public boolean changePassword(String userName, String password)
	{
		if(userName!=null && password!=null)
		{
			loginDAO.changePassword(userName,password);
			return true;
		}
		else return false;
	}
	
	@Transactional
	public boolean otpValidator(long userOtpCode, String userName)
	{
		long temp = loginDAO.getOtpCode(userName);
		System.out.println("user-code"+ userOtpCode);
		
		java.util.Date date= new java.util.Date();
		
		
		   timeDifference td = new timeDifference();
		   

		if(temp!=-1 && temp==userOtpCode )
		{
			System.out.println("inside if stmt");
			if(td.timeDiff(loginDAO.getOtpTime(userName), new Timestamp(date.getTime())))
				return true;
			else return false;
		}
		else return false;
	
	}

	@Transactional
	public boolean getSecurityAnswers(String userName, String userAnswer1, String userAnswer2, String userAnswer3)
	{
		int count = 0;
		SecurityQuestionsModel dbSecAnswers = loginDAO.getSecurityAnswers(userName);
		System.out.println("in service");
		System.out.println("user"+ userAnswer2);
		System.out.println("db"+ dbSecAnswers.getAnswer2());
		
		if(userAnswer1.equals(dbSecAnswers.getAnswer1()))
		{
			count++;
			System.out.println("Answer 1 correct");
		}
		
		if(userAnswer2.equals(dbSecAnswers.getAnswer2()))
		{
			count++;
			System.out.println("Answer 2 correct");
		}
		
		if(userAnswer3.equals(dbSecAnswers.getAnswer3()))
		{
			count++;
			System.out.println("Answer 3 correct");
		}
		
		if(count>=2)
		{
			System.out.println("in service");
			//SendMail sendOtpMail = new SendMail();
			System.out.println("imp"+ userName);
			String temp = send(userName);
			if(temp!=null)
			{
				Long optCode = Long.parseLong(temp);
				loginDAO.setOtpCode(userName, optCode);
			}
			
		
			return true;
		}
		
		
		else {
			 return false;
		}
		
		
	}
	
	@Transactional
	public String send(String userName) {
		System.out.println("s");
		Properties props = new Properties();
		System.out.println("Done1");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
			//System.out.println("Done");
		//LoginDTO trd = new LoginDTO();
		
				protected PasswordAuthentication getPasswordAuthentication() {
					//return new PasswordAuthentication("donotreplysbsbank@gmail.com","obuli123");
					return new PasswordAuthentication("donotreplysbsbank@gmail.com","obuli123");
				}	
			});
try {
			
			String e_mail = loginDAO.getEmail(userName);
			System.out.println("email in send mail via logindao"+ e_mail);
			Message message = new MimeMessage(session);
			System.out.println("Done2");
			message.setFrom(new InternetAddress("donotreplysbsbank@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(e_mail));
			OTPGenerator otp =new OTPGenerator();
			String otpCode = otp.generateOTP(6);
			message.setSubject("Your OTP Code");
			message.setText("Your OTP Code : "+ otpCode + "\nThank you for banking with us!: Your Security and Privacy is Our Priority");
 
			
			Transport.send(message);
			
			System.out.println("Done3");
			return otpCode;
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}