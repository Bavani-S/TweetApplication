package com.tweetapp.authenticationservice.model;

public class ForgotPasswordRequestDTO {
	
	public ForgotPasswordRequestDTO() {
		super();
	}
  
	private String password;
	private String dob;
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	ForgotPasswordRequestDTO(String password,String dob){
		this.password=password;
		this.dob=dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ForgotPasswordRequestDTO(String password) {
		super();
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForgotPasswordRequestDTO other = (ForgotPasswordRequestDTO) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ForgotPasswordRequestDTO [password=" + password + "]";
	}

}
