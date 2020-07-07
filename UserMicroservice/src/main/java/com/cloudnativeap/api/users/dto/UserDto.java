package com.cloudnativeap.api.users.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class UserDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1619264580807812251L;
	
	private String name;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
	
	
	public UserDto() {
	}

	public UserDto(String name, String email, String password, String userId, String encryptedPassword) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.encryptedPassword = encryptedPassword;
	}
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}
