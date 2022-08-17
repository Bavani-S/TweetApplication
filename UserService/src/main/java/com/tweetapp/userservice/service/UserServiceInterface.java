package com.tweetapp.userservice.service;

import com.tweetapp.userservice.model.AuthenticationResponse;
import com.tweetapp.userservice.model.ResponseDTO;
import com.tweetapp.userservice.model.UserAccount;

public interface UserServiceInterface {

	public ResponseDTO createUser(String token, UserAccount user);

	public UserAccount getUserDetails(String token, String userName);

	//public AuthenticationResponse hasEmployeePermission(String token);

	public boolean deleteUser(String token, String userName);

	//AuthenticationResponse hasCustomerPermission(String token);

	public AuthenticationResponse hasPermission(String token);

	public UserAccount saveUser(String token, UserAccount user);

	public UserAccount updateUser(String token, UserAccount user);
}