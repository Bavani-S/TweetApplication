package com.tweetapp.userservice.model;

public class ResponseDTO {
	private UserAccount useraccount;
	private ErrorResponseDTO error;
	public UserAccount getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}
	public ErrorResponseDTO getError() {
		return error;
	}
	public void setError(ErrorResponseDTO error) {
		this.error = error;
	}
	public ResponseDTO(UserAccount useraccount, ErrorResponseDTO error) {
		super();
		this.useraccount = useraccount;
		this.error = error;
	}
	public ResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

}
