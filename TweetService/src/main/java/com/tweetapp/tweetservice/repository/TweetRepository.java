package com.tweetapp.tweetservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweetservice.model.UserTweet;

@Repository
public class TweetRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	
	public List<UserTweet> findAllTweetsOfUser(String userName) {
		List<UserTweet> result = dynamoDBMapper.scan(UserTweet.class, new DynamoDBScanExpression());
		List<UserTweet> resultFilter = result.stream().filter(tweet -> tweet.getUserName().contains(userName)).collect(Collectors.toList());
		if(resultFilter.size() == 0) {
			return null;
		} else {
			return resultFilter;
		}
	}
	
	public List<UserTweet> findAll() {
		List<UserTweet> result = dynamoDBMapper.scan(UserTweet.class, new DynamoDBScanExpression());
		return result;
	}
	
	public UserTweet save(UserTweet tweet) {
		dynamoDBMapper.save(tweet);
		return tweet;
	}

	public UserTweet findById(String id) {
		return dynamoDBMapper.load(UserTweet.class, id);
	}

	public void deleteById(String id) {
		 dynamoDBMapper.delete(dynamoDBMapper.load(UserTweet.class, id));
		System.out.println("deleted");
	}
	
	void deleteByUserName(String userName) {
		List<UserTweet> tweetList=findAllTweetsOfUser(userName);
		for(UserTweet tweet : tweetList) {
			deleteById(tweet.getId());
		}
	}
	

	
	

}
