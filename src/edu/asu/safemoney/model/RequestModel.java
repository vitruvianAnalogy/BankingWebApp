package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class RequestModel {
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)
	private int memberId;
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0) 
	private long requestId;

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}
