package com.authenication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegisterRequest {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid Email")
	private String email;
	
	@Size(min=6, message = "Password must be more than 6 characters")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterRequest(@NotBlank(message = "Name is required") String name,
			@Email(message = "Invalid Email") String email,
			@Size(min = 6, message = "Password must be more than 6 characters") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public RegisterRequest() {
		super();
	}

	

}
