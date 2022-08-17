package com.tweetapp.userservice.model;

public class AppUser {
	private String userName;
	private String name;
	private String emailId;
	private String password;
	private String authToken;
	private String role;
	public AppUser() {
		// TODO Auto-generated constructor stub
	}
	public AppUser(String userName, String name, String emailId, String password, String authToken, String role) {
		super();
		this.userName = userName;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.authToken = authToken;
		this.role = role;
	}
	public AppUser(String userName, String name, String emailId, String password, String authToken) {
		super();
		this.userName = userName;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.authToken = authToken;
		this.role="USER";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthToken() {
		return authToken;
	}public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}