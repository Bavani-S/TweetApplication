package com.tweetapp.authenticationservice.model;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "AppUser")
public class AppUser {
	
	@NotNull
	@DynamoDBHashKey(attributeName="userName")
	private String userName;
	@DynamoDBAttribute(attributeName="name")
	private String name;
	@DynamoDBAttribute(attributeName="emailId")
	private String emailId;
	@DynamoDBAttribute(attributeName="password")
	private String password;
	@DynamoDBAttribute(attributeName="authToken")
	private String authToken;
	@DynamoDBAttribute(attributeName="role")
	private String role;
	public AppUser() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AppUser(String userName, String name, String emailId, String password, String authToken, String role) {
		super();
		this.userName = userName;
		this.name=name;
		this.emailId = emailId;
		this.password = password;
		this.authToken = authToken;
		this.role = role;
	}


	/*public AppUser(String userName, String emailId, String password, String authToken) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.authToken = authToken;
		this.role="USER";
	}*/
	public AppUser(String userName, String name, String emailId, String password, String role) {
		super();
		this.userName = userName;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
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


	@Override
	public String toString() {
		return "AppUser [userName=" + userName + ",name=" +name+" emailId=" + emailId + ", password=" + password + ", authToken="
				+ authToken + ", role=" + role + "]";
	}


	//public String getUsername() {
		// TODO Auto-generated method stub
	//	return userName;
	//}
	
	
}