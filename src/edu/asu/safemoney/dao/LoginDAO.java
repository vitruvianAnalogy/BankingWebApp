package edu.asu.safemoney.dao;

import edu.asu.safemoney.dto.LoginDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.SecurityQuestionsModel;
import edu.asu.safemoney.model.UserModel;

public interface LoginDAO {

	public String getSiteKey(String userName);
	
	public UserDTO getUserByMemberId(int memberId);
	
	public int getMemberIdByUserName(String userName);
	
	public LoginDTO getLoginDetails(String userName);
	
	public boolean createUser(UserModel user);
	
	public boolean createEmployee(UserModel userModel);
	
	public boolean isEmailExists(String emailId);
	
	public UserDTO copyToUserDTO(UserModel user);
	
	public boolean resetFailureAttempts(String userName);
	
	public boolean updateLoginFailureAttempts(String userName);
	
	public int getFailureAttemptCount(String userName);
	
	public SecurityQuestionsModel getSecurityAnswers(String userName);
	
	public SecurityQuestionsModel getSecurityQuestions(String userName);
	
	public Long getOtpCode(String userName);
	
	public boolean setOtpCode(String userName, Long optCode);
	public void changePassword(String userName, String password);
	
	public String getOtpTime(String userName);
	public String getEmail(String userName);
	
	
	//public String getUserTypeId(int memberId);
	
}
