package com.cloudnativeap.api.users.data.repo;

import org.springframework.data.repository.CrudRepository;

import com.cloudnativeap.api.users.data.UserEntity;
import com.cloudnativeap.api.users.dto.UserDto;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUserId(String userId);

}
