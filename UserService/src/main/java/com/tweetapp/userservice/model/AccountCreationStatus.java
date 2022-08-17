package com.tweetapp.userservice.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AccountCreationStatus {


	private long accountId;
	private String message;
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AccountCreationStatus(long accountId, String message) {
		super();
		this.accountId = accountId;
		this.message = message;
	}
	

}
