package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class AdminAuthorizePaymentRequest {

	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Min(1)
	@Nonnegative
	private long transactionRequestId;
	
	@Nonnull	
	@Pattern(regexp = "[a-z-A-Z-0-9 ]*", message="Invalid description")
	private String manageTransactionAction;
	
	
	public long getTransactionRequestId() {
		return transactionRequestId;
	}
	public void setTransactionRequestId(long transactionRequestId) {
		this.transactionRequestId = transactionRequestId;
	}
	public String getManageTransactionAction() {
		return manageTransactionAction;
	}
	public void setManageTransactionAction(String manageTransactionAction) {
		this.manageTransactionAction = manageTransactionAction;
	}
	
	
}
