package com.tweetapp.tweetservice.model;



public class ResponseDTO {
	
	private Boolean status;
	private ErrorMessage error;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ErrorMessage getError() {
		return error;
	}
	public void setError(ErrorMessage error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "ResponseDTO [status=" + status + "]";
	}
	public ResponseDTO(Boolean status, ErrorMessage error) {
		super();
		this.status = status;
		this.error = error;
	}
	public ResponseDTO() {
		super();
	}
	
}
