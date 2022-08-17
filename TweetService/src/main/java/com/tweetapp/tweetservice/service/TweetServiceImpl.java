package com.tweetapp.tweetservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tweetapp.tweetservice.feignclient.AuthFeignClient;
import com.tweetapp.tweetservice.model.AllTweetsResponseDTO;
import com.tweetapp.tweetservice.model.AuthenticationResponse;
import com.tweetapp.tweetservice.model.ErrorMessage;
import com.tweetapp.tweetservice.model.ResponseDO;
import com.tweetapp.tweetservice.model.ResponseDTO;
import com.tweetapp.tweetservice.model.UserTweet;
import com.tweetapp.tweetservice.repository.TweetRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TweetServiceImpl implements TweetServiceInterface {
	
		@Autowired
		TweetRepository tweetsRepository;

		@Autowired
		private AuthFeignClient authFeignClient;
		
		@Override
		public AuthenticationResponse hasPermission(String token) {
			return authFeignClient.tokenValidation(token);
		}


		public ResponseDTO postTweet(String userId, String tweet, String token) throws Exception {
			Boolean status = false;
			ResponseDTO tweetResponse = new ResponseDTO();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd(HH:mm:ss)");
			LocalDateTime now = LocalDateTime.now();
			String postedDate=dtf.format(now);
			DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String posteddate=dtfdate.format(now);
			DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
			String postedtime=dtftime.format(now);
			ResponseEntity<ResponseDO> nameResponse = authFeignClient.getName(token,userId);
			String name = nameResponse.getBody().getName();
			UserTweet usertweet = new UserTweet(userId+postedDate,userId,name,tweet,posteddate+" at "+postedtime,null,0);
			tweetsRepository.save(usertweet);
			status=true;
			tweetResponse.setError(null);
			log.info("Tweet posted successfully");
			tweetResponse.setStatus(status);
			return tweetResponse;
		}

		public AllTweetsResponseDTO getAllTweets(String userId) throws Exception {
			AllTweetsResponseDTO allTweetsResponse = new AllTweetsResponseDTO();
			
			List<UserTweet> tweets;
			tweets = tweetsRepository.findAll();
			if (tweets != null) {
				allTweetsResponse.setAllTweets(tweets);
				allTweetsResponse.setError(null);
				log.info("All tweets fetched successfully");

			} else {
				allTweetsResponse.setError(new ErrorMessage(103, "No tweets to be fetched"));
			}
			return allTweetsResponse;
		}

		public AllTweetsResponseDTO getUserTweets(String userId) throws Exception {
			AllTweetsResponseDTO allTweetsResponse = new AllTweetsResponseDTO();
			List<UserTweet> tweets=findAllTweetsOfUser(userId);
			if (tweets != null) {
				allTweetsResponse.setAllTweets(tweets);
				allTweetsResponse.setError(null);
				log.info("All tweets of the user fetched successfully");

			} else {
				allTweetsResponse.setError(new ErrorMessage(103, "No tweets to be fetched"));
			}
			return allTweetsResponse;
			}
		
		    
		private List<UserTweet> findAllTweetsOfUser(String userId) {
			List<UserTweet> tweets= tweetsRepository.findAll();
			List<UserTweet> userTweets= new ArrayList<UserTweet>();
			for(UserTweet tweet : tweets) {
				if(tweet.getUserName().equalsIgnoreCase(userId)) {
					//System.out.println("username : "+tweet.getUserName()+"-> tweet : "+tweet.getTweet());
					userTweets.add(tweet);
				}
			}
			if(userTweets==null)
				System.out.println("Tweet history is empty");
			return userTweets;
		}


		public ResponseDTO updateTweet(String id, String tweet) throws Exception {
			ResponseDTO updateTweet = new ResponseDTO();
			UserTweet userTweet = tweetsRepository.findById(id);
			//UserTweet userTweet = tweetsRepository.getTweetByIdandTweet(String id, String tweet);
			if(userTweet != null) {
				userTweet.setTweet(tweet);
				tweetsRepository.deleteById(id);
				tweetsRepository.save(userTweet);
				updateTweet.setStatus(true);
				updateTweet.setError(null);		
			}
			else {
				updateTweet.setStatus(false);
				updateTweet.setError(new ErrorMessage(108, "Unable To find Tweet"));
				throw new Exception("Unable to find tweet");
			}
			return updateTweet;
		}

		public ResponseDTO deleteTweet(String id) throws Exception {
			ResponseDTO deleteTweet = new ResponseDTO();
			try {
				UserTweet update = tweetsRepository.findById(id);
				if (update != null) {
					tweetsRepository.deleteById(id);
					deleteTweet.setStatus(true);
					deleteTweet.setError(null);
				} else {
					deleteTweet.setStatus(false);
					deleteTweet.setError(new ErrorMessage(109, "Unable To delete Tweet"));
				}
			} catch (Exception exception) {
				deleteTweet.setStatus(false);
				deleteTweet.setError(new ErrorMessage(109, "Unable To find Tweet"));
				throw new Exception("Unable to find tweet");
			}
			return deleteTweet;
		}

		public ResponseDTO replyTweet(String userId, String id, String reply,String token) throws Exception {
			ResponseDTO replied = new ResponseDTO();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd(HH:mm:ss)");
			LocalDateTime now = LocalDateTime.now();
			String postedDate=dtf.format(now);
			DateTimeFormatter dtfdate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String posteddate=dtfdate.format(now);
			DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
			String postedtime=dtftime.format(now);
			ResponseEntity<ResponseDO> nameResponse = authFeignClient.getName(token,userId);
			String name = nameResponse.getBody().getName();
			UserTweet usertweet = new UserTweet(userId+postedDate,userId,name,reply,posteddate+" at "+postedtime,null,0);
			UserTweet repliedTo = tweetsRepository.findById(id);
			if(repliedTo!=null) {
			List<UserTweet> replies = repliedTo.getReplies();
			if(replies==null)
				replies=new ArrayList<UserTweet>();
			replies.add(usertweet);
			repliedTo.setReplies(replies);
			tweetsRepository.deleteById(id);
			tweetsRepository.save(repliedTo);
			tweetsRepository.save(usertweet);
			replied.setStatus(true);
			replied.setError(null);
			}
			else {
				replied.setStatus(false);
				replied.setError(new ErrorMessage(109, "Tweet not found"));
			}
			return replied;
		}

		public ResponseDTO likeTweet(String userId, String id, Boolean status) throws Exception {
			ResponseDTO liked = new ResponseDTO();
			UserTweet likedTweet = tweetsRepository.findById(id);
			if(likedTweet!=null) {
			if(status==true) {
				int likedBy = likedTweet.getLikes();
				likedBy= likedBy+1;
				likedTweet.setLikes(likedBy);
				tweetsRepository.deleteById(id);
				tweetsRepository.save(likedTweet);
				liked.setStatus(true);
				liked.setError(null);
			}}
			else {
				liked.setStatus(false);
				liked.setError(new ErrorMessage(109, "Tweet not found"));
			}
			return liked;
		}


		public ResponseDTO deleteAllTweets(String userName) {
			ResponseDTO delete = new ResponseDTO();
			try {
				AllTweetsResponseDTO allTweetsResponse=getUserTweets(userName);
				for(UserTweet tweet : allTweetsResponse.getAllTweets()) {
					tweetsRepository.deleteById(tweet.getId());
				}
				delete.setStatus(true);
				delete.setError(null);
			} catch (Exception e1) {
				delete.setStatus(false);
				delete.setError(new ErrorMessage(109, "Unable To find Tweet"));
				e1.printStackTrace();
			}
			return delete;
			
		}


		public AllTweetsResponseDTO getReplies(String id) {
			AllTweetsResponseDTO allTweetsResponse = new AllTweetsResponseDTO();
			List<UserTweet> tweets = tweetsRepository.findById(id).getReplies();
			if (tweets != null) {
				allTweetsResponse.setAllTweets(tweets);
				allTweetsResponse.setError(null);
				log.info("Replies of the tweet fetched successfully");

			} else {
				allTweetsResponse.setError(new ErrorMessage(103, "No tweets to be fetched"));
			}
			return allTweetsResponse;
			}

	}
