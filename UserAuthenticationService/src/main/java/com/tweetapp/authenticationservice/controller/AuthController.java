package com.tweetapp.authenticationservice.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.authenticationservice.model.AppUser;
import com.tweetapp.authenticationservice.model.AuthenticationResponse;
import com.tweetapp.authenticationservice.model.ForgotPasswordRequestDTO;
import com.tweetapp.authenticationservice.model.LoginRequestDTO;
import com.tweetapp.authenticationservice.model.LoginResponseDTO;
import com.tweetapp.authenticationservice.model.RegisterResponseDTO;
import com.tweetapp.authenticationservice.model.ResponseDO;
import com.tweetapp.authenticationservice.repository.LoginRepository;
import com.tweetapp.authenticationservice.service.AppUserDetailsService;
import com.tweetapp.authenticationservice.service.LoginService;
import com.tweetapp.authenticationservice.service.Validationservice;

import lombok.extern.slf4j.Slf4j;


/**
 * The AuthController class for request controller
 *
 */
@Slf4j
@CrossOrigin(origins="*")
@RestController
public class AuthController {

	// Users Repository
	@Autowired
	private LoginRepository userRepository;
	
	//Service class login
	@Autowired
	private LoginService loginService;
	
	//Service class for login
	@Autowired
	private Validationservice validationService;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	/**
	 * The health method to check app
	 *
	 */
	@GetMapping("/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("Health Check for Authentication Microservice");
		log.info("health checkup ----->{}","up");
		return new ResponseEntity<>("UP", HttpStatus.OK);
	}

	/**
	 * The login method with post request
	 *
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
		LoginResponseDTO loginResponseDTO = loginService.userLogin(loginRequest);
		log.info("Logged in ->"+loginRequest.getUserName());
		return ResponseEntity.ok().body(loginResponseDTO);
	}
	@PutMapping("/forgot/{userId}/{emailId}")
	public ResponseEntity<RegisterResponseDTO> forgotPassword(@PathVariable("userId") String userId, @PathVariable("emailId") String emailId,
			@RequestBody String password) throws Exception {
		RegisterResponseDTO forgot = loginService.forgotPassword(userId,password,emailId);
		return ResponseEntity.ok().body(forgot);
		
	}
	
	/**
	 * The token validation method
	 *
	 */
	@GetMapping("/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {
		log.info("Token Validation ----->{}",token);
		return validationService.validate(token);
	}
	
	/**
	 * The user is created with login credentials
	 *
	 */
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody AppUser appUserCredentials)
	{
		AppUser createduser = null;
		try {
			createduser = userRepository.save(appUserCredentials);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Not created" , HttpStatus.NOT_ACCEPTABLE);
		}
		log.info("user creation---->{}",createduser);
		return new ResponseEntity<>(createduser,HttpStatus.CREATED);
		
	}
	
	
	/**
	 * The find users method to find all users
	 *
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AppUser>> findUsers(@RequestHeader("Authorization") final String token)
	{
		List<AppUser> createduser =new ArrayList<>() ;
		List<AppUser> findAll = userRepository.findAll();
		findAll.forEach(emp->createduser.add(emp));
		log.info("All Users  ----->{}",findAll);
		return new ResponseEntity<>(createduser,HttpStatus.CREATED);
		
	}
	@GetMapping("/findUser/{userName}")
	public ResponseEntity<AppUser> findUser(@RequestHeader("Authorization") final String token, @PathVariable("userName") String userName)
	{
		//List<AppUser> createduser =new ArrayList<>() ;
		AppUser findAll = userRepository.findByUserId(userName);
		log.info("User  ----->{}",findAll);
		return new ResponseEntity<>(findAll,HttpStatus.CREATED);
		
	}
	

	@GetMapping("/getName/{userId}")
	public ResponseEntity<ResponseDO> getName(@RequestHeader("Authorization") final String token, @PathVariable String userId) {
		
		String name = userRepository.findByUserId(userId).getName();
		System.out.println(name);
		ResponseDO nameResponse = new ResponseDO(name);
		return new ResponseEntity<>(nameResponse, HttpStatus.OK);
	}
	@DeleteMapping("/deleteUser/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token, @PathVariable String id) {

		System.out.println("Starting deletion of-->" + id);
		appUserDetailsService.deleteUser(id);
		System.out.println("Deleted");
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}
	
	
}