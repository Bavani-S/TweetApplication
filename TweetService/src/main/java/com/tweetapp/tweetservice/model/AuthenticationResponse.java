package com.tweetapp.tweetservice.model;


public class AuthenticationResponse {
	
	
	private String userid;	
	private String name;
	private boolean isValid;
	
	public AuthenticationResponse(String userid, String name, boolean isValid) {
		super();
		this.userid = userid;
		this.name = name;
		this.isValid = isValid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	
	
}