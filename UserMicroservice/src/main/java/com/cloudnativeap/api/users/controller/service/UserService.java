package com.cloudnativeap.api.users.controller.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cloudnativeap.api.users.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);

	UserDto getUserByUserId(String userId);

	List<UserDto> getUsers();

	UserDto updateUser(UserDto userDto);

	void deleteUser(String userId);
	
	UserDto getUserByUserEmail(String email);
}
