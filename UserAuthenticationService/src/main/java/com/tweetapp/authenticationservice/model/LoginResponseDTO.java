package com.tweetapp.authenticationservice.model;

public class LoginResponseDTO {

	private String userName;
	
	private String emailId;

	private ErrorMessage errorMessage;

	private String token;

	public LoginResponseDTO() {
		super();
	}

	public LoginResponseDTO(String userName, String emailId, ErrorMessage errorMessage, String token) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.errorMessage = errorMessage;
		this.token = token;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginResponseDTO [userName=" + userName + ", emailId=" + emailId + ", errorMessage=" + errorMessage
				+ ", token=" + token + "]";
	}

}
