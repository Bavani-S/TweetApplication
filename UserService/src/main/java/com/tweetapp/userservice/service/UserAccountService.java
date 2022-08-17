package com.tweetapp.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.userservice.feign.AuthorizationFeign;
import com.tweetapp.userservice.feign.TweetFeign;
import com.tweetapp.userservice.model.AppUser;
import com.tweetapp.userservice.model.AuthenticationResponse;
import com.tweetapp.userservice.model.ErrorMessage;
import com.tweetapp.userservice.model.RegisterResponseDTO;
import com.tweetapp.userservice.model.ResponseDTO;
import com.tweetapp.userservice.model.UserAccount;
import com.tweetapp.userservice.model.UserResponseDTO;
import com.tweetapp.userservice.model.UserTweet;
import com.tweetapp.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAccountService implements UserServiceInterface{
	private static final String USER = "USER";
	@Autowired
	AuthorizationFeign authorizationFeign;

	@Autowired
	TweetFeign tweetFeign;

	@Autowired
	UserRepository userRepo;

	@Override
	public AuthenticationResponse hasPermission(String token) {
		return authorizationFeign.getValidity(token);
	}

	@Override
	public ResponseDTO createUser(String token, UserAccount user) {
		boolean userNameExists = checkUserNameExists(token, user.getUserName()); 
		if (userNameExists) {
			AppUser appuser = new AppUser(user.getUserName(), user.getEmailId(), user.getPassword(), null,
					USER);
			authorizationFeign.createUser(appuser);
		}

		for (UserTweet tweet : user.getTweets()) {
			tweetFeign.createTweet(token, user.getUserName(), tweet);
		}

		userRepo.save(user);
		log.info("User details saved.");
		ResponseDTO response = new ResponseDTO();
		return response;
	}
	
	//@Override
	public RegisterResponseDTO registerUser(UserAccount useraccount) throws Exception {
		RegisterResponseDTO registerResponse = new RegisterResponseDTO();
		boolean status = true;
		//try {
			if (userRepo.findByEmailId(useraccount.getEmailId()) != null) {
				status = false;
				ErrorMessage error = new ErrorMessage(101, "Email Id already exists!");
				registerResponse.setErrorMessage(error);
			}
			if (userRepo.findByUserId(useraccount.getUserName()) != null) {
				status = false;
				if (registerResponse.getErrorMessage() != null) {
					ErrorMessage error = new ErrorMessage(103, "Email Id and User Id already exists!");
					registerResponse.setErrorMessage(error);
				} else {
					ErrorMessage error = new ErrorMessage(102, "User Id already exists!");
					registerResponse.setErrorMessage(error);
				}
			}
			if (status) {
				userRepo.save(useraccount);
				AppUser appuser = new AppUser(useraccount.getUserName(), useraccount.getFirstName()+" "+useraccount.getLastName(), useraccount.getEmailId(), useraccount.getPassword(), null,
						USER);
				authorizationFeign.createUser(appuser);
				log.info("User details saved.");
				registerResponse.setErrorMessage(null);
			}

	//	} catch (Exception e) {
	//		status = false;
	//		throw new Exception("Registration unsuccessful registerUser");
	//	}
		registerResponse.setStatus(status);
		return registerResponse;
	}


	private boolean checkUserNameExists(String token, String userName) {
		boolean flag=false;
		if(userRepo.findByUserId(userName)==null)
			flag=true;
		else
			flag=false;
		return flag;
	}

	@Override
	public UserAccount getUserDetails(String token, String userName) {
		UserAccount user = findByUserName(userName);
		List<UserTweet> list=null;
		if (user==null)
			return null;
		log.info("User details fetched.");
		try {
		 list = tweetFeign.getUserTweets(token,userName).getAllTweets();
		}catch(Exception e) {
			System.out.println("Tweet feign error "+e);
		}
		user.setTweets(list);
		return user;
	}
	
	public UserAccount findByUserName(String userName) {
		UserAccount appuser = new UserAccount();
		List<UserAccount> appUserList= userRepo.findAll();
		System.out.println(appUserList.toString());
		appUserList.forEach(appUser->System.out.println(appUser.toString()));
		for(UserAccount user : appUserList) {
			if(user.getUserName().equalsIgnoreCase(userName)) {
				appuser=user;
				break;
			}
		}
		if(appuser==null)
			System.out.println("appuser value null");
		else
			System.out.println(appuser.toString());
		return appuser;
	}


	@Override
	public boolean deleteUser(String token,String userName) {
		UserAccount user = findByUserName(userName);
		if (user != null) {
			userRepo.deleteByUserName(userName);
		   // authorizationFeign.deleteUser(token,userName);
		   // tweetFeign.deleteUserTweets(token,userName);
		}
		else
			return false;
		log.info("user details deleted.");
		return true;
	}

	@Override
	public UserAccount saveUser(String token, UserAccount user) {
		UserAccount checkUserExists = getUserDetails(token, user.getUserName());
		if (checkUserExists == null) {
			AppUser appuser = new AppUser(user.getUserName(), user.getEmailId(), user.getPassword(), null,
					USER);
			authorizationFeign.createUser(appuser);
		}
		return userRepo.save(user);
	}

	@Override
	public UserAccount updateUser(String token, UserAccount user) {
		UserAccount toUpdate = userRepo.findByUserId(user.getUserName());
		toUpdate.setTweets(user.getTweets());
		for (UserTweet acc : user.getTweets()) {
			tweetFeign.createTweet(token, user.getUserName(), acc);
		}
		return userRepo.save(toUpdate);
	}
	
	public UserResponseDTO getAllUsers() throws Exception {
		UserResponseDTO userResponse = new UserResponseDTO();
		List<UserAccount> users = new ArrayList<UserAccount>();
		try {
			List<UserAccount> allUsers = userRepo.findAll();
			allUsers.stream().forEach((user) -> {
				UserAccount userAccount = new UserAccount(user.getUserName(),user.getEmailId(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getContactNumber(),user.getDob(),user.getTweets());
				users.add(userAccount);
			});
			userResponse.setUsers(users);
			userResponse.setError(null);
		} catch (Exception e) {
			userResponse.setUsers(null);
			userResponse.setError(new ErrorMessage(107, "Error fetching all users"));
			throw new Exception("Error fetching all users");
		}
		return userResponse;
	}
	
	public RegisterResponseDTO checkUser(String userId) throws Exception {
		RegisterResponseDTO check = new RegisterResponseDTO();
		Boolean status =false;
		try {
			if(userRepo.findByUserId(userId) != null) {
				status = true;
			} else {
				check.setErrorMessage(new ErrorMessage(102, "No user found"));
			}
		} catch (Exception exception) {
			check.setErrorMessage(new ErrorMessage(102, "Error connecting with the database"));
		}
		check.setStatus(status);
		return check;
	}

}
