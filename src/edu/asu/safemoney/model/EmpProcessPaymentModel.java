package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class EmpProcessPaymentModel {

	
	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Min(1)
	@Nonnegative
	private long paymentRequestId2;
	
	@Nonnull
	@Pattern(regexp = "[a-z-A-Z-0-9 ]*", message="Invalid description")
	private String managePaymentAction;
	
	
	public long getPaymentRequestId2() {
		return paymentRequestId2;
	}
	public void setPaymentRequestId2(long paymentRequestId2) {
		this.paymentRequestId2 = paymentRequestId2;
	}
	public String getManagePaymentAction() {
		return managePaymentAction;
	}
	public void setManagePaymentAction(String managePaymentAction) {
		this.managePaymentAction = managePaymentAction;
	}
	
	
}
