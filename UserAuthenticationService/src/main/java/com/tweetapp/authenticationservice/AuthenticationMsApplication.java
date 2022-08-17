package com.tweetapp.authenticationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.tweetapp.authenticationservice.service.AuthMessagingService;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * The AuthenticationMsApplication class to start the application
 *
 */

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AuthenticationMsApplication {
	
	@Autowired
	AuthMessagingService kafkaSender;
	
	/**
	 * The main method for app
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationMsApplication.class, args);
	}
	
	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Authorization Service - TweetApp")
	              .description("This is an microservice for Tweeter application in order to handle all the user authorization operations")
	              .version("v0.0.1"));
	  }
	/*
	 * Kafka producer
	 */
	 /*
	@Bean
	public void Messaging() {
		kafkaSender.send("Authorization Microservice started");
	} */

}
 