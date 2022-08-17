package com.tweetapp.tweetservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@FeignClient(name = "user-ms", url = "${tweet-ms.feign.url.user-ms}")
public interface UserFeignProxy {


//	@GetMapping("/getUserDetails/{userName}")
//	public UserAccount getUserDetails(@PathVariable(name = "userName") String id);

}
