package com.tweetapp.tweetservice.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserTweet")
public class UserTweet {

	@DynamoDBHashKey(attributeName="id")
	private String id;
	@DynamoDBAttribute(attributeName="userName")
	private String userName;
	@DynamoDBAttribute(attributeName="name")
	private String name;
	@DynamoDBAttribute(attributeName="tweet")
	private String tweet;
	@DynamoDBAttribute(attributeName="postedDate")
	private String postedDate;
	@DynamoDBAttribute(attributeName="replies")
	private List<UserTweet> replies;
	@DynamoDBAttribute(attributeName="likes")
	private int likes;
	public UserTweet() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserTweet(String id, String userName, String name, String tweet, String postedDate, List<UserTweet> replies,
			int likes) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.tweet = tweet;
		this.postedDate = postedDate;
		this.replies = replies;
		this.likes = likes;
	}

	public UserTweet( String userName, String name, String tweet, String postedDate, List<UserTweet> replies,
			int likes) {
		super();
		this.id = userName+postedDate;
		this.userName = userName;
		this.name = name;
		this.tweet = tweet;
		this.postedDate = postedDate;
		this.replies = replies;
		this.likes = likes;
	}
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public List<UserTweet> getReplies() {
		return replies;
	}
	public void setReplies(List<UserTweet> replies) {
		this.replies = replies;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	

}
