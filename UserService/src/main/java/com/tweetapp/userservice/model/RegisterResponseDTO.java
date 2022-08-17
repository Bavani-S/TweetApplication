package com.tweetapp.userservice.model;

public class RegisterResponseDTO {
	
	private Boolean status;
	
	private ErrorMessage errorMessage;
    
	@Override
	public String toString() {
		return "RegisterResponseDTO [status=" + status + "]";
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
    public RegisterResponseDTO() {
		
	}
public RegisterResponseDTO(Boolean status, ErrorMessage errorMessage ) {
	this.status = status;
	this.errorMessage = errorMessage;
	}
}
