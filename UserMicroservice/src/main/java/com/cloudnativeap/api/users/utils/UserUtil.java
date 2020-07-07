package com.cloudnativeap.api.users.utils;

import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.util.Assert;
import org.modelmapper.internal.util.Types;
import org.springframework.stereotype.Service;

import com.cloudnativeap.api.users.data.UserEntity;
import com.cloudnativeap.api.users.dto.UserDto;
import com.cloudnativeap.api.users.model.UserRequestModel;
import com.cloudnativeap.api.users.model.UserResponseModel;

@Service
public class UserUtil {

	public UserDto mapUserRequestModelToUserDto(UserRequestModel source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto target = modelMapper.map(source, UserDto.class);

		return target;
	}

	public UserResponseModel mapUserDtoToUserResponseModel(UserDto source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserResponseModel target = modelMapper.map(source, UserResponseModel.class);

		return target;
	}

	public UserEntity mapUserDtoToUserEntity(UserDto source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity target = modelMapper.map(source, UserEntity.class);

		return target;
	}

	public UserDto mapUserEntityToUserDto(UserEntity source) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto target = modelMapper.map(source, UserDto.class);

		return target;
	}
}
