package edu.asu.safemoney.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.safemoney.dao.LoginDAO;
import edu.asu.safemoney.service.impln.LoginServiceImpl;

public class DefaultAuthenticationProvider extends DaoAuthenticationProvider  {
	
	@Autowired
	LoginDAO loginDAO;
	
	//LoginServiceImpl loginServiceImpl;
	
	public Authentication authenticate(Authentication authentication) 
	          throws AuthenticationException {
	 
		  try {
	 
			Authentication tempAuthentication = super.authenticate(authentication);
			System.out.println("Hello");
			//if reach here, means login success, else an exception will be thrown
			//reset the user_attempts
			//loginDAO.resetFailureAttempts(authentication.getName());
			
			return tempAuthentication;
	 
		  } catch (BadCredentialsException e) {	
	 
			//invalid login, update to user_attempts
			loginDAO.updateLoginFailureAttempts(authentication.getName());
			throw e;
	 
		  }catch (LockedException e){
			  
				//this user is locked!
				String error = "";
				int attemptCount = loginDAO.getFailureAttemptCount(authentication.getName());
				if(attemptCount == 3){
					error = "Maximum Login Attempts reached. <br>Your account has been locked! <br>Please use Forgot password to reset your account";
				}else{
					error = e.getMessage();
				}
		 
			  throw new LockedException(error);
	 
		}
	}
	
	public void setLoginServiceImpl(LoginServiceImpl loginServiceImpl) {
		super.setUserDetailsService(loginServiceImpl);
	}
	
}