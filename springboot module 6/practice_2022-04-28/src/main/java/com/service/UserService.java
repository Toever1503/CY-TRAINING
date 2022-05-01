package com.service;

import com.entity.UserEntity;
import com.entity.dto.UserDto;
import com.entity.model.UserModel;

public interface UserService extends BaseService<UserModel, UserDto, Long> {
    UserEntity findByEmail(String email);
}
