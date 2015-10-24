package edu.asu.safemoney.model;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionModel {
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)	
	private long transactionId;
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)	
	private long fromAccount;
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)	
	private long toAccount;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull 
	private Date transactionDate;

	@Nonnull @Min(1)
	@Digits(integer = 11, fraction = 0)	
	private double transactionAmount;
	
	private String transactionStatus;
	private String transactionType;
	private boolean isCritical;
	private boolean isAuthorized;

	@Nonnull @Min(1)
	@Digits(integer = 11, fraction = 2)	
	private double amount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public boolean isCritical() {
		return isCritical;
	}
	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}
	public boolean isAuthorized() {
		return isAuthorized;
	}
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	
	
	
	
	
}
