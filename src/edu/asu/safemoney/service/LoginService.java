package edu.asu.safemoney.service;

import org.springframework.security.core.userdetails.User;

import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.model.SecurityQuestionsModel;
import edu.asu.safemoney.model.UserModel;

public interface LoginService {
	
	public String getSiteKeyForUserName(String userName);
	
	public boolean createUser(UserModel user);
	
	public int getMemberId(String userName);

	public SecurityQuestionsModel getSecurityQuestions(String userName);
	
	public boolean getSecurityAnswers(String userName, String userAnswer1, String userAnswer2, String userAnswer3);

	public boolean otpValidator(long userOtpCode, String userName);
	public String send(String userName);
	
	public boolean changePassword(String userName, String password);

}