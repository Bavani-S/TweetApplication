FROM openjdk:8-jdk-alpine
EXPOSE 8085
COPY ./target/UserService-0.0.1-SNAPSHOT.jar UserService-0.0.1.jar 
ENTRYPOINT ["java","-jar","/UserService-0.0.1.jar"]