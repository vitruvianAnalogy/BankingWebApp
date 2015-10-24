package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DoTransferModel {
	
	
	
	@NotNull
	@Digits(fraction =0, integer =12)
	@Min(1)
	@Nonnegative
	private long toAccountNumber;
	
	
	@NotNull
	@Digits(fraction =2, integer =6)
	@Nonnegative
	@Min(1)
	private double transformAmount;
	
	
	public long getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public double getTransformAmount() {
		return transformAmount;
	}
	public void setTransformAmount(double transformAmount) {
		this.transformAmount = transformAmount;
	}
	
	
}
