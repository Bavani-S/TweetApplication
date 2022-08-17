FROM openjdk:8-jdk-alpine
EXPOSE 8084
COPY ./target/UserAuthenticationService-0.0.1-SNAPSHOT.jar AuthenticationService-0.0.1.jar 
ENTRYPOINT ["java","-jar","/AuthenticationService-0.0.1.jar"]