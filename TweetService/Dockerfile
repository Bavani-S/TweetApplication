FROM openjdk:8-jdk-alpine
EXPOSE 8086
COPY ./target/TweetService-0.0.1-SNAPSHOT.jar TweetService-0.0.1.jar 
ENTRYPOINT ["java","-jar","/TweetService-0.0.1.jar"]