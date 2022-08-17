package com.tweetapp.tweetservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tweetapp.tweetservice.model.AuthenticationResponse;
import com.tweetapp.tweetservice.model.ResponseDO;


@FeignClient(name = "auth-ms", url = "${tweet-ms.feign.url.auth-ms}")
public interface AuthFeignClient {


	@GetMapping("/validateToken")
	public AuthenticationResponse tokenValidation(@RequestHeader("Authorization") String token);

	@GetMapping("/getName/{userId}")
	public ResponseEntity<ResponseDO> getName(@RequestHeader("Authorization") String token, @PathVariable String userId);

	//@GetMapping("/role/{userName}")
	//public String getRole(@PathVariable("id") String id);

}