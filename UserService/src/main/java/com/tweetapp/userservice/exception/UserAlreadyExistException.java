package com.tweetapp.userservice.exception;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -2862505141325062716L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
