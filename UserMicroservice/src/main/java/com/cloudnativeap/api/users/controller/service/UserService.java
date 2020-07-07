package com.cloudnativeap.api.users.controller.service;

import java.io.Serializable;
import java.util.List;

import com.cloudnativeap.api.users.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);

	UserDto getUserByUserId(String userId);

	List<UserDto> getUsers();

	UserDto updateUser(UserDto userDto);

	void deleteUser(String userId);

}
