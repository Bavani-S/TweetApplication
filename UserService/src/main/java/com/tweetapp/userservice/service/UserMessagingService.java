package com.tweetapp.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMessagingService {
		
		/*@Autowired
		private KafkaTemplate<String, String> kafkaTemplate;*/
		
		String kafkaTopic = "Tweetapp_topic";
		
		public void send(String message) {
		    
		    try {
				//kafkaTemplate.send(kafkaTopic, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * Kafka Listener method; Keep it commented unless Kafka server is running
		 */
		/*@KafkaListener(topics="Tweetapp_topic",groupId = "Tweetapp_topic")
		public void consumer(String data) throws Exception{
			log.info("Kafka Listener : "+data);
		}*/
			
		
	}
