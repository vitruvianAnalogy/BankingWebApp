package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class UserNameValidationModel {
	
	
	@Nonnull
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z-0-9._]*", message="Invalid characters in username")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
