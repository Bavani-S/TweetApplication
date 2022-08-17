package com.tweetapp.tweetservice.model;

public class TweetRequestDTO {
	
	private String tweet;

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public TweetRequestDTO(String tweet) {
		super();
		this.tweet = tweet;
	}

	public TweetRequestDTO() {
		super();
	}

}
