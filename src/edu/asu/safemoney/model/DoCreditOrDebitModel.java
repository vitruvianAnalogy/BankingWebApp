package edu.asu.safemoney.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class DoCreditOrDebitModel {
	
	
	
	@NotNull
	@Digits(fraction =2, integer =6)
	@Nonnegative
	@Min(1)
	private double creditDebitAmount;
	
	@NotNull
	@Min(1)
	@Max(2)
	private int optionsRadiosInline;
	
	
	public double getCreditDebitAmount() {
		return creditDebitAmount;
	}
	public void setCreditDebitAmount(double creditDebitAmount) {
		this.creditDebitAmount = creditDebitAmount;
	}
	public int getOptionsRadiosInline() {
		return optionsRadiosInline;
	}
	public void setOptionsRadiosInline(int optionsRadiosInline) {
		this.optionsRadiosInline = optionsRadiosInline;
	}
	

}
