package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class InitiatePaymentModel {
	
	
	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Nonnegative
	@Min(1)
	private long toMerchantAccountNumber;
	
	@NotNull
	@Digits(fraction =2, integer =6)
	@Nonnegative
	@Min(1)
	private double amount;
	
	@Nonnull
	@Pattern(regexp = "[a-z-A-Z-0-9 ]*", message="Invalid description")
	private String description;

	public long getToMerchantAccountNumber() {
		return toMerchantAccountNumber;
	}

	public void setToMerchantAccountNumber(long toMerchantAccountNumber) {
		this.toMerchantAccountNumber = toMerchantAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
