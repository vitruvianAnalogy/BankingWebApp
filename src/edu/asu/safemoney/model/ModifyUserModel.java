package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ModifyUserModel {

	@Email
	String emailId;
	
	@Nonnull @Min(0) @Size(min=10)
	@Digits(integer = 10, fraction = 0)
	long contactNo;
	
	@Nonnull
	@Size(max=50)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Address has invalid characters")
	String address1;
	
	@Nonnull
	@Size(max=50)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Address has invalid characters")
	String address2;
	
	@Nonnull
	@Size(max=15)
	@Pattern(regexp = "[a-z-A-Z]*", message = "City has invalid characters")
	String city;
	
	@Nonnull
	@Size(min=2,max=2)
	@Pattern(regexp = "[a-z-A-Z]*", message = "State has invalid characters")
	String state;
	
	@Nonnull @Min(0) @Size(min=5)
	@Digits(integer = 5, fraction = 0)
	long zip;
	
	@Nonnull @Min(0)
	@Digits(integer = 11, fraction = 0)
	int memberId;
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}


}
