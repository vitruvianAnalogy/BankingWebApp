package edu.asu.safemoney.model;

import javax.annotation.Nonnull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SecurityQuestionsModel {
	
	@Nonnull
	@Size(max=50)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")
	private String question1;
	
	@Nonnull
	@Size(max=50)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")
	private String question2;
	
	@Nonnull
	@Size(max=50)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")
	private String question3;
	
	@Nonnull
	@Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")
	private String answer1;
	
	@Nonnull
	@Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")
	private String answer2;
	
	@Nonnull
	@Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")
	private String answer3;

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

}
