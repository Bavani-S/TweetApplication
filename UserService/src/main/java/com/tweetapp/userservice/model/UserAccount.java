package com.tweetapp.userservice.model;



import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserAccount")
public class UserAccount {
	@DynamoDBHashKey(attributeName="userName")
	private String userName;
	@DynamoDBAttribute(attributeName="emailId")
	private String emailId;
	@DynamoDBAttribute(attributeName="firstName")
	private String firstName;
	@DynamoDBAttribute(attributeName="lastName")
	private String lastName;
	@DynamoDBAttribute(attributeName="password")
	private String password;
	@DynamoDBAttribute(attributeName="contactNumber")
	private String contactNumber;
	@DynamoDBAttribute(attributeName="dob")
	private String dob;
	@DynamoDBAttribute(attributeName="tweets")
	private List<UserTweet> tweets;
	public UserAccount() {
		// TODO Auto-generated constructor stub
	}
	public UserAccount(String userName, String emailId, String firstName, String lastName, String password,
			String contactNumber, String dob, List<UserTweet> tweets) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.contactNumber = contactNumber;
		this.dob = dob;
		this.tweets = tweets;
	}
	public UserAccount(String userName, String emailId, String firstName, String lastName, String password,
			String contactNumber, String dob) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.contactNumber = contactNumber;
		this.dob = dob;
		this.tweets=null;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public List<UserTweet> getTweets() {
		return tweets;
	}
	public void setTweets(List<UserTweet> tweets) {
		this.tweets = tweets;
	}
	
}
