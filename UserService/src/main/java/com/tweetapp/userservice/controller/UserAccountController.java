package com.tweetapp.userservice.controller;


//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.userservice.feign.AuthorizationFeign;
import com.tweetapp.userservice.model.RegisterResponseDTO;
import com.tweetapp.userservice.model.UserAccount;
import com.tweetapp.userservice.model.UserResponseDTO;
import com.tweetapp.userservice.service.UserMessagingService;
import com.tweetapp.userservice.service.UserAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins="*")
@RestController
public class UserAccountController {

	@Autowired
	private UserAccountService userService;

	@Autowired
	AuthorizationFeign authorizationFeign;
	
	@Autowired
	UserMessagingService kafkaSender;
	
	@GetMapping("/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("Health Check for User Microservice");
		log.info("health checkup ----->{}","up");
		kafkaSender.send("Health Check: User Microservice is UP");
		return new ResponseEntity<>("UP", HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(//@RequestHeader("Authorization") String token,
			@RequestBody UserAccount user) throws Exception {
		//userService.hasPermission(token);
		RegisterResponseDTO response = userService.registerUser(user);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/users/all")
	public ResponseEntity<UserResponseDTO> getAllUsers(@RequestHeader("Authorization") String token) throws Exception {
		userService.hasPermission(token);
		UserResponseDTO users = userService.getAllUsers();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<RegisterResponseDTO> checkUser(@RequestHeader("Authorization") String token, @PathVariable String userId) throws Exception {
		userService.hasPermission(token);
		RegisterResponseDTO user = userService.checkUser(userId);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/saveUser")
	public UserAccount saveUser(@RequestHeader("Authorization") String token,
			@Valid @RequestBody UserAccount user) {
		userService.hasPermission(token);
		UserAccount userAccount = userService.saveUser(token, user);
		if (userAccount != null)
			return userAccount;
		else
			return null;
	}

	@PostMapping("/updateUser")
	public UserAccount updateUser(@RequestHeader("Authorization") String token,
			@Valid @RequestBody UserAccount user) {
		userService.hasPermission(token);
		return userService.updateUser(token, user);
	}

	@GetMapping("/getUserDetails/{userName}")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token, @PathVariable String userName) {
		userService.hasPermission(token);
		UserAccount toReturnUserDetails = userService.getUserDetails(token, userName);
		if (toReturnUserDetails == null)
			return new ResponseEntity<>("Username " + userName + " doesn't exist!", HttpStatus.NOT_ACCEPTABLE);
		toReturnUserDetails.setPassword(null);
		return new ResponseEntity<>(toReturnUserDetails, HttpStatus.OK);
	}

	@DeleteMapping("deleteUser/{userName}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String userName) {

		userService.hasPermission(token);

		UserAccount checkUserNameExists = null;
		checkUserNameExists = userService.getUserDetails(token, userName);
		if (checkUserNameExists == null) {
			return new ResponseEntity<>("Username doesn't exist!", HttpStatus.NOT_ACCEPTABLE);
		}

		System.out.println("Starting deletion of-->" + userName);
		userService.deleteUser(token,userName);
		System.out.println("Deleted");
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/check")
	public String checkAccessWWithoutValidation(@RequestHeader("Authorization") String token) {
		userService.hasPermission(token);
		return "Your Token is valid";
	}

}
