package com.tweetapp.userservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.userservice.model.UserAccount;

@Repository
public class UserRepository {
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	
	public UserAccount findByEmailId(String emailId) {
		List<UserAccount> result = dynamoDBMapper.scan(UserAccount.class, new DynamoDBScanExpression());
		List<UserAccount> resultFilter = result.stream().filter(user -> user.getEmailId().contains(emailId)).collect(Collectors.toList());
		if(resultFilter.size() == 0) {
			return null;
		} else {
			return resultFilter.get(0);
		}
	}
	
	public List<UserAccount> findAll() {
		List<UserAccount> result = dynamoDBMapper.scan(UserAccount.class, new DynamoDBScanExpression());
		return result;
	}
	
	public UserAccount save(UserAccount user) {
		dynamoDBMapper.save(user);
		return user;
	}

	public UserAccount findByUserId(String userId) {
		List<UserAccount> result = dynamoDBMapper.scan(UserAccount.class, new DynamoDBScanExpression());
		List<UserAccount> resultFilter = result.stream().filter(user -> user.getUserName().equals(userId)).collect(Collectors.toList());
		if(resultFilter.size() == 0) {
			return null;
		} else {
			return resultFilter.get(0);
		}
	}

	public void deleteByUserName(String userName) {
		 dynamoDBMapper.delete(dynamoDBMapper.load(UserAccount.class, userName));
		System.out.println("deleted");
	}

}
