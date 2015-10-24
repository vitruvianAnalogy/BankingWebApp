package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TransactionReviewModel {
	
	@Nonnull
	@Min(100000)
	@Digits(integer = 12, fraction=0)
	long transactionReviewId; 
	
	@Nonnull
	@Pattern(message="Invalid Input", regexp = "[a-z-A-Z]*")
	String processTxnReview;

	public String getProcessTxnReview() {
		return processTxnReview;
	}

	public void setProcessTxnReview(String processTxnReview) {
		this.processTxnReview = processTxnReview;
	}

	public long getTransactionReviewId() {
		return transactionReviewId;
	}

	public void setTransactionReviewId(long transactionReviewId) {
		this.transactionReviewId = transactionReviewId;
	}

	

}
