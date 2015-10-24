package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestParam;

public class SubmitAuthorizedPaymentModel {

	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Min(1)
	@Nonnegative
	public long paymentRequestId2;

	public long getPaymentRequestId2() {
		return paymentRequestId2;
	}

	public void setPaymentRequestId2(long paymentRequestId2) {
		this.paymentRequestId2 = paymentRequestId2;
	}
	
}
