package com.practice.entities;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDate {

	@NotBlank(message="User Name can not be empty !!")
	@Size(min=3,max=12,message="user name must be between 3-12 charactors !!")
	private String userName;
	
	@Pattern(regexp =  "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email !!")
	private String email;
	
	@AssertTrue(message = "must agree terms and condition !!")
	private boolean agreed;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}		
	public boolean isAgreed() {
		return agreed;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	
	@Override
	public String toString() {
		return "LoginDate [userName=" + userName + ", email=" + email + "]";
	}
			
}
