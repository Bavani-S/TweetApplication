package com.tweetapp.tweetservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.tweetapp.tweetservice.controller.TweetServiceController;
import com.tweetapp.tweetservice.service.TweetMessagingService;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class TweetServiceApplication {
	
	@Autowired
	TweetMessagingService kafkaSender;

	private static final Logger LOGGER = LoggerFactory.getLogger(TweetServiceController.class);

	public static void main(String[] args) { 
		SpringApplication.run( TweetServiceApplication.class, args);
		LOGGER.info("Tweet microservice started....");
	}
	
	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Tweet Service - TweetApp")
	              .description("This is an microservice for Tweeter application in order to handle all the tweet related operations")
	              .version("v0.0.1"));
	  }
	/*
	 * Kafka producer
	 */
	 /*
	@Bean
	public void Messaging() {
		kafkaSender.send("Tweet Microservice started");
	} */
}
