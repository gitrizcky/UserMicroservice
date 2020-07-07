package com.cloudnativeap.api.users.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudnativeap.api.users.controller.service.UserService;
import com.cloudnativeap.api.users.data.UserEntity;
import com.cloudnativeap.api.users.data.repo.UserRepository;
import com.cloudnativeap.api.users.dto.UserDto;
import com.cloudnativeap.api.users.model.UserRequestModel;
import com.cloudnativeap.api.users.model.UserResponseModel;
import com.cloudnativeap.api.users.utils.UserUtil;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	Environment env;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	UserUtil userUtils;

	@GetMapping("/status/check")
	public String status() {
		return "user ws is working on port " + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, produces = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {

		UserDto userDto = userUtils.mapUserRequestModelToUserDto(userRequestModel);
		userDto = userService.createUser(userDto);
		UserResponseModel returnValue = userUtils.mapUserDtoToUserResponseModel(userDto);
		return new ResponseEntity<UserResponseModel>(returnValue, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable("userId") String userId) {

		UserDto userDto = userService.getUserByUserId(userId);
		UserResponseModel returnValue = userUtils.mapUserDtoToUserResponseModel(userDto);
		return new ResponseEntity<UserResponseModel>(returnValue, HttpStatus.OK);
	}

	@PutMapping(value = "/{userId}", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, produces = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<UserResponseModel> updateUserByUserId(@PathVariable("userId") String userId,
			@Valid @RequestBody UserRequestModel userRequestModel) {

		UserDto userDto = userUtils.mapUserRequestModelToUserDto(userRequestModel);
		userDto.setUserId(userId);
		userDto = userService.updateUser(userDto);
		UserResponseModel returnValue = userUtils.mapUserDtoToUserResponseModel(userDto);
		return new ResponseEntity<UserResponseModel>(returnValue, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<UserResponseModel> deleteUserByUserId(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<UserResponseModel>(HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<UserResponseModel> getUsers() {
		
		List<UserDto> userDto = userService.getUsers();
		//List<String> resultList = userDto.stream().map(dto -> dto.getName()).collect(Collectors.toList());
		
		List<UserResponseModel> resultList = userDto.stream().map(
				temp -> {
					UserResponseModel obj = new UserResponseModel();
					obj.setName(temp.getName());
					obj.setEmail(temp.getEmail());
					obj.setUserId(temp.getUserId());
					return obj;
				}).collect(Collectors.toList());
		return resultList;
	}

}
