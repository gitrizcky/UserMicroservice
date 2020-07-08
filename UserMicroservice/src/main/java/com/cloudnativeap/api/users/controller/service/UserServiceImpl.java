package com.cloudnativeap.api.users.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudnativeap.api.users.data.UserEntity;
import com.cloudnativeap.api.users.data.repo.UserRepository;
import com.cloudnativeap.api.users.dto.UserDto;
import com.cloudnativeap.api.users.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService {

	BCryptPasswordEncoder bCryptPasswordEncoder;
	UserRepository userRepository;

	@Autowired
	UserUtil userUtils;

	@Autowired
	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
			com.cloudnativeap.api.users.data.repo.UserRepository userRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		UserEntity resultEntity = userUtils.mapUserDtoToUserEntity(userDto);
		userRepository.save(resultEntity);
		UserDto returnValue = userUtils.mapUserEntityToUserDto(resultEntity);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub

		UserEntity resultEntity = userRepository.findByUserId(userId);
		UserDto returnValue = userUtils.mapUserEntityToUserDto(resultEntity);
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers() {
		// TODO Auto-generated method stub
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Iterable<UserEntity> source = userRepository.findAll();
        List<UserDto> targetList = StreamSupport.stream(source.spliterator(), false)
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
		return targetList;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity resultEntity = userRepository.findByUserId(userId);
		userRepository.deleteById(resultEntity.getId());
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		UserEntity resultEntity = userRepository.findByUserId(userDto.getUserId());
		resultEntity.setEmail(userDto.getEmail());
		resultEntity.setName(userDto.getName());
		resultEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userRepository.save(resultEntity);
		UserDto returnValue = userUtils.mapUserEntityToUserDto(resultEntity);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity resultEntity = userRepository.findByEmail(username);
		
		if(resultEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(
				resultEntity.getEmail(), 
				resultEntity.getEncryptedPassword(), 
				true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserEmail(String email) {
		// TODO Auto-generated method stub
		
		UserEntity resultEntity = userRepository.findByEmail(email);
		if(resultEntity == null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(resultEntity, UserDto.class);
	}

}
