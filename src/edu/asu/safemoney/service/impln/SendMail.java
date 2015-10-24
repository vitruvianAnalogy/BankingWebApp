package edu.asu.safemoney.service.impln;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.dao.impln.LoginDAOImpl;
import edu.asu.safemoney.dto.LoginDTO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

public class SendMail  {

	
	@Autowired
	private LoginDAO loginDAO;
	
	
	

}
