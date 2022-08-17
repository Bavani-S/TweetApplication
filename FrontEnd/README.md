# Tweet App

This is a Microservice- Architecture based application for users to register themselves followed by the several operations such as login, post new tweets, view tweets by other users, logout, reset password, delete user account.

## Cloud deployment:
This application has been deployed in **Amazon Web Services Cloud**. 
### Website URL:  
#### TweetApp URL : http://tweetapplication-ui.s3-website-us-east-1.amazonaws.com/
#### Load balancer URL for microservices' health check :
1. Authenticaton Microservice - http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/auth/health
2. User Microservice                 - http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/user/health
3. Tweet Microservice               - http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/tweet/health
### AWS services used:
1. Amazon DynamoDB 
2. Amazon Elastic Container Registry (ECR)
3. Amazon Elastic Container Service (ECS)
4. Amazon Elastic Load Balancer (ELB)
5. Amazon Serverless S3
6. Features of Elastic Compute Cloud (EC2) & Fargate

## Backend Component Details

Application comprises of three micro-services

1. **UserAuthenticationService** - Microservice to handle user authorizations
2. **UserService** - Microservice to user functionalities
3. **TweetService** - Microservice to process tweet messages

## Steps to follow before running the application in local machine

### Back-End

1. Unzip and extract the **TweetApp-BackEnd.Zip** file and import all the 3 components in any IDE to run spring boot applications
2. Make sure the dynamodb database details in the components' application.properties file matches the system configuration
3. Build all the 3 projects and update maven dependencies
4. Run the all the 3 application as spring boot application

### Front-End

 1. Unzip and extract the **TweetApp-FrontEnd.Zip** file and import all the 5 components in any IDE to run the angular application
 2. Make sure npm, angular client, typescript installed in the system
 3. Navigate to the angular project folder
 4. Run the command **npm install** to add the required node modules
 5. Run the command **ng serve** to start the angular project
 6. Navigate to the url http://localhost:4200/ to use the Tweet Application
