package com.cloudnativeap.api.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestModel {
	
	@NotNull(message = "Name cannot be null")
	@Size(min=2, message = "Name must not be less then 2 char")
	private String name;
	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
	@NotNull(message = "Password cannot be null")
	@Size(min=8, message = "Name must not be less then 8 char")
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
	
}
