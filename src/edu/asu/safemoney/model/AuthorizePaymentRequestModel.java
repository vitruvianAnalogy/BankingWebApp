package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class AuthorizePaymentRequestModel {

	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Min(1)
	@Nonnegative
	public long paymentRequestId;
	
	@Nonnull
	@Pattern(regexp = "[a-z-A-Z-0-9 ]*", message="Invalid description")
	public String authorizeAction;
	
	public long getPaymentRequestId() {
		return paymentRequestId;
	}
	public void setPaymentRequestId(long paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}
	public String getAuthorizeAction() {
		return authorizeAction;
	}
	public void setAuthorizeAction(String authorizeAction) {
		this.authorizeAction = authorizeAction;
	} 
	
}
