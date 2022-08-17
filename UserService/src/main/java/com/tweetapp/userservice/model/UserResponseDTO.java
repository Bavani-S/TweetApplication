package com.tweetapp.userservice.model;

import java.util.List;

public class UserResponseDTO {
	
	private List<UserAccount> users;
	private ErrorMessage error;
	public List<UserAccount> getUsers() {
		return users;
	}
	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "UserResponseDTO [users=" + users + ", error=" + error + "]";
	}
	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponseDTO(List<UserAccount> users, ErrorMessage error) {
		super();
		this.users = users;
		this.error = error;
	}

}
