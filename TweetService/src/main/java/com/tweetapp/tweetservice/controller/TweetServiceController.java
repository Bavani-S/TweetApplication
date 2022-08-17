package com.tweetapp.tweetservice.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweetservice.model.AllTweetsResponseDTO;
import com.tweetapp.tweetservice.model.ResponseDTO;
import com.tweetapp.tweetservice.model.TweetRequestDTO;
import com.tweetapp.tweetservice.model.UserTweet;
import com.tweetapp.tweetservice.service.TweetServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins="*")
@RestController
public class TweetServiceController {
	@Autowired
	private TweetServiceImpl tweetService;
	
	@GetMapping("/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("Health Check for User Microservice");
		log.info("health checkup ----->{}","up");
		return new ResponseEntity<>("UP", HttpStatus.OK);
	}
	
	//Returning all the tweets present inside database
	@GetMapping("/all/{userId}")
	public AllTweetsResponseDTO getAllTweets(@RequestHeader("Authorization") String token,@PathVariable String userId) throws Exception {
		tweetService.hasPermission(token);
		AllTweetsResponseDTO tweets = tweetService.getAllTweets(userId);
		return tweets;
	}
	//Returning all tweets of the user
	@GetMapping("/{userId}")
		public AllTweetsResponseDTO getUserTweets(@RequestHeader("Authorization") String token,@PathVariable String userId) throws Exception {
		tweetService.hasPermission(token);
			AllTweetsResponseDTO tweets = tweetService.getUserTweets(userId);
			return tweets;
		}
	
	@PostMapping("/{userId}/add")
		public ResponseEntity<ResponseDTO> postTweet(@RequestHeader("Authorization") String token, @PathVariable String userId, @RequestBody TweetRequestDTO tweet) throws Exception {
		tweetService.hasPermission(token);	
		ResponseDTO response = tweetService.postTweet(userId, tweet.getTweet(),token);
			return ResponseEntity.ok().body(response);
		}
	@PutMapping("/{userId}/update/{id}")
		public ResponseEntity<ResponseDTO> updateTweet(@RequestHeader("Authorization") String token, @PathVariable String userId, @PathVariable String id, @RequestBody String tweet) throws Exception {
		tweetService.hasPermission(token);	
		ResponseDTO update = tweetService.updateTweet(id, tweet);
			
			return ResponseEntity.ok().body(update);
		}
	@DeleteMapping("/{userId}/delete/{id}")
		public ResponseEntity<ResponseDTO> deleteTweet(@RequestHeader("Authorization") String token, @PathVariable String userId, @PathVariable String id) throws Exception {
		tweetService.hasPermission(token);	
		ResponseDTO update = tweetService.deleteTweet(id);
			
			return ResponseEntity.ok().body(update);
		}
	@DeleteMapping("deleteUser/{userName}")
	public ResponseEntity<ResponseDTO> deleteUserTweets(@RequestHeader("Authorization") String token, String userName) {
		tweetService.hasPermission(token);	
		ResponseDTO delete = tweetService.deleteAllTweets(userName);
		return ResponseEntity.ok().body(delete);
	}
	@PostMapping("{userId}/reply/{id}")
		public ResponseEntity<ResponseDTO> replyTweet(@RequestHeader("Authorization") String token, @PathVariable String userId, @PathVariable String id, @RequestBody String replyTweet) throws Exception {
		tweetService.hasPermission(token);	
		ResponseDTO reply = tweetService.replyTweet(userId, id, replyTweet,token);
			return ResponseEntity.ok().body(reply);
		}
	@PutMapping("{userId}/like/{id}")
		public ResponseEntity<ResponseDTO> likeTweet(@RequestHeader("Authorization") String token, @PathVariable String userId, @PathVariable String id, @RequestBody Boolean status) throws Exception {
		tweetService.hasPermission(token);	
		ResponseDTO like = tweetService.likeTweet(userId, id, status);
			return ResponseEntity.ok().body(like);
		}
	@GetMapping("replies/{id}")
	public AllTweetsResponseDTO getReplies(@RequestHeader("Authorization") String token,@PathVariable String id) throws Exception {
		tweetService.hasPermission(token);
			AllTweetsResponseDTO tweets = tweetService.getReplies(id);
			return tweets;
		}
}
