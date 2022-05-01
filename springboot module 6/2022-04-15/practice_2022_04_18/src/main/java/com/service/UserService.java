package com.service;

import com.domain.dto.UserDto;
import com.domain.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<UserModel, UserDto, Long> {
}
