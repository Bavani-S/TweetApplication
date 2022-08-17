package com.tweetapp.userservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tweetapp.userservice.model.AccountCreationStatus;
import com.tweetapp.userservice.model.AllTweetsResponseDTO;
import com.tweetapp.userservice.model.UserAccount;
import com.tweetapp.userservice.model.UserTweet;

@FeignClient(name = "tweet-ms", url = "${feign.url-tweet-service}")
public interface TweetFeign {

	@PostMapping("/{userName}/add")
	public AccountCreationStatus createTweet(@RequestHeader("Authorization") String token,
			@PathVariable String userName, @RequestBody UserTweet tweet);

	@GetMapping("/all/{userName}")
	public List<UserAccount> getTweets(@RequestHeader("Authorization") String token,
			@PathVariable String userName);
	@GetMapping("/{userName}")
	public AllTweetsResponseDTO getUserTweets(@RequestHeader("Authorization") String token, String userName);

	@DeleteMapping("deleteUser/{userName}")
	public void deleteUserTweets(@RequestHeader("Authorization") String token, String userName);

}
