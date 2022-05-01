package com.service.impl;

import com.entity.User;
import com.entity.dto.UserDto;
import com.entity.model.UserModel;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User modelToEntity(UserModel userModel) {
        if (userModel == null) return null;

        return User.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .build();
    }

    UserModel toModel(User user) {
        if (user == null) return null;
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdBy(user.getCreatedBy() != null ? user.getCreatedBy().getId() : null)
                .build();
    }

    UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdBy(user.getCreatedBy() != null ? user.getCreatedBy().getUsername() : null)
                .build();
        dto.setCreatedAt(user.getCreatedAt());
        dto.setLastModifiedAt(user.getLastModifiedAt());
        return dto;
    }

    @Override
    public UserDto saveOrUpdate(UserModel userModel) {
        User entity = modelToEntity(userModel);
        if (userModel.getCreatedBy() != null) {

        }
        return toDto(userRepository.save(entity));
    }

    @Override
    public UserDto findById(Long aLong) {
        return toDto(userRepository.findById(aLong).orElse(null));
    }

    @Override
    public boolean deleteBYId(Long aLong) {
        this.userRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<UserDto> search(String q, Pageable pageable) {
        return this.userRepository.findAllByUsernameLike("%" + q + "%", pageable).map(this::toDto);
    }
}
