package com.tweetapp.tweetservice.service;

import com.tweetapp.tweetservice.model.AllTweetsResponseDTO;
import com.tweetapp.tweetservice.model.AuthenticationResponse;
import com.tweetapp.tweetservice.model.ResponseDTO;

public interface TweetServiceInterface {
ResponseDTO postTweet(String userId, String tweet, String token) throws Exception;
	
	AllTweetsResponseDTO getAllTweets(String userId) throws Exception;
	
	ResponseDTO updateTweet(String id, String tweet) throws Exception;
	
	AllTweetsResponseDTO getUserTweets(String userId) throws Exception;

	ResponseDTO deleteTweet(String id) throws Exception;
	
	ResponseDTO likeTweet(String userId, String id, Boolean status) throws Exception;
	
	ResponseDTO replyTweet(String userId, String id, String reply, String token) throws Exception;

	AuthenticationResponse hasPermission(String token);


}
