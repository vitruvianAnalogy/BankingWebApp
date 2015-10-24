package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountModel {
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)
	private long accountNo;
	
	@Nonnull
	@Size(max=25)
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String firstName;
	
	@Nonnull
	@Size(max=25)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	private String lastName;
	
	@Nonnull @Min(1)
	@Digits(integer = 11, fraction = 0)
	private double amount;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
