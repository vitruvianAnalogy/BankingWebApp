package edu.asu.safemoney.service.impln;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.AdminUserDAO;
import edu.asu.safemoney.dao.EmployeeUserDAO;
import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dao.RequestDAO;
import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	@Autowired LoginDAO loginDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private ManageExternalUserAccountDAO extUserAccountDAO;
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Transactional
	@Override
	public List<RequestDTO> getExterUserAccountRequests() {
		// TODO Auto-generated method stub
		
		List<RequestDTO> requestList = requestDAO.getRequestsForAdminUser();
		for(RequestDTO rDTO : requestList)
		{
			System.out.println("Request Name: " + rDTO.getRequestType());
		}
		return requestList;
	}

	@Transactional
	@Override
	public boolean generateBankAccount(int memberId) {
		// TODO Auto-generated method stub
		UserDTO userDTO = extUserAccountDAO.displayUserAccountDAO(memberId);
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNo(ExternalUserHelper.generateAccountNumber(memberId));
		accountDTO.setAmount(0.00);
		accountDTO.setIsActive("true");
		accountDTO.setMemberId(userDTO);
		boolean isAccountCreated = extUserAccountDAO.createAccount(accountDTO);
		boolean isEnabledUpdated = extUserAccountDAO.updateIsEnabled(memberId, true);
		if(isAccountCreated && isEnabledUpdated)
		{
			String catalinaPath = System.getProperty("catalina.base") + File.separator + "UserCertificates" + File.separator + userDTO.getLoginDTO().getUserName() + ".cer";
			//String catalinaPath = System.getProperty("catalina.base") + File.separator + "UserCertificates" + File.separator + "text.txt";
			System.out.println("PATH = " + catalinaPath);
			sendWithAttachment(userDTO.getLoginDTO().getUserName(), catalinaPath);
			return true;
		}
		return false;
	}
	
@Transactional
public String sendWithAttachment(String userName, String path) {
		
		String subject = "Private Key and Certificate";
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");

		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");

		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			 String toEmail = loginDAO.getEmail(userName);
			//default mail session
			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(true);

			// Construct the mail message
			MimeMessage message = new MimeMessage(session);
			// message.setText(Text);
			message.setSubject("Certificate");
			message.setFrom(new InternetAddress("donotreplysbsbank@gmail.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
		
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText("Data");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = path;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("DigitalCertificate.cer");
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// Use Transport to deliver the message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "donotreplysbsbank@gmail.com", "obuli123");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		}  catch (MessagingException ex) {
            ex.printStackTrace();
        }

			return "123456";
	}
	
	
	@Transactional
	public boolean deleteExtUserAccount(int memberId)
	{
		boolean isUserDeleted = extUserAccountDAO.deleteExtUserAccount(memberId);
		if(isUserDeleted)
		{
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean approveExtUserRequest(long requestId) {
		// TODO Auto-generated method stub
		RequestDTO requestDTO = requestDAO.getRequestByRequestId(requestId);
		if(requestDTO != null && requestDTO.getStatus().equals("NEW"))
		{
			if(requestDTO.getRequestType().equals("CREATE_ACCOUNT"))
			{
				boolean isAccountGenerated = generateBankAccount(requestDTO.getMemberId().getMemberId());
				if(isAccountGenerated)
				{
					requestDTO.setStatus("APPROVED");
					requestDTO.setProcessedDate(new Date());
					boolean isRequestUpdated = requestDAO.updateRequest(requestDTO);
					if(isRequestUpdated)
					{
						return true;
					}
				}
			}
			else if(requestDTO.getRequestType().equals("DELETE_ACCOUNT"))
			{
				boolean isDeleted = deleteExtUserAccount(requestDTO.getMemberId().getMemberId());
				if(isDeleted)
				{
					requestDTO.setStatus("APPROVED");
					requestDTO.setProcessedDate(new Date());
					boolean isRequestUpdated = requestDAO.updateRequest(requestDTO);
					if(isRequestUpdated)
					{
						return true;
					}
				}
			}
			
		}
		return false;
	}

	@Transactional
	@Override
	public boolean declineExtUserRequest(long requestId) {
		// TODO Auto-generated method stub
		RequestDTO requestDTO = requestDAO.getRequestByRequestId(requestId);
		if(requestDTO != null && requestDTO.getStatus().equals("NEW"))
		{
			if(requestDTO.getRequestType().equals("CREATE_ACCOUNT") || requestDTO.getRequestType().equals("DELETE_ACCOUNT"))
			{
					requestDTO.setStatus("DECLINED");
					requestDTO.setProcessedDate(new Date());
					boolean isRequestUpdated = requestDAO.updateRequest(requestDTO);
					if(isRequestUpdated)
					{
						return true;
					}
			}
			
		}
		return false;
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
	
	private Date calcuateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 365);
		Date expiryDate = cal.getTime();
		return expiryDate;
	}
	
	@Transactional
	@Override
	public boolean createEmployee(UserModel userModel){
		System.out.println("entered service");
		if(validateUser(userModel)){
			System.out.println("returned false at service");
			return false;
		}
		else{
		userModel.setEmployee(true);
		userModel.setCreatedDate(new Date());
		userModel.setExpiryDate(calcuateExpiryDate());
		userModel.setIsActive("true");
		userModel.setCreatedBy("ADMIN");
		userModel.setCreatedDate(new Date());
		userModel.setIsCustomer("false");
		userModel.setUserType("INT_BANK_EMP");
		userModel.setUserTypeId(125);
		boolean created= loginDAO.createEmployee(userModel);
		System.out.println("returned true at service");
		return created;
		}
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
	@Override
	public ModifyUserModel getEmployee(int memberId){
		ModifyUserModel modifyUserModel= employeeUserDAO.getEmployeeDetails(memberId); 
		
		return modifyUserModel;
	}

	
}