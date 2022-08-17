package com.tweetapp.userservice.model;

import java.util.List;

public class AllTweetsResponseDTO {
	
	private List<UserTweet> allTweets;
	private ErrorMessage error;
	public AllTweetsResponseDTO(List<UserTweet> allTweets, ErrorMessage error) {
		super();
		this.allTweets = allTweets;
		this.error = error;
	}
	public List<UserTweet> getAllTweets() {
		return allTweets;
	}
	public void setAllTweets(List<UserTweet> allTweets) {
		this.allTweets = allTweets;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "allTweets=" + allTweets + ", error=" + error + "]";
	}
	public AllTweetsResponseDTO() {
		super();
	}

}
