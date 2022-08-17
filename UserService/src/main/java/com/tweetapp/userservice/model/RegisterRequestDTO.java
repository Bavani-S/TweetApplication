package com.tweetapp.userservice.model;

public class RegisterRequestDTO {

	String firstName;
	String lastName;
	String password;
	String emailId;
	String userId;
	String contactNumber;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public RegisterRequestDTO(String firstName, String lastName, String password, String emailId, String userId,
			String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "RegisterRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", emailId=" + emailId + ", userId=" + userId + ", contactNumber=" + contactNumber + "]";
	}

}
