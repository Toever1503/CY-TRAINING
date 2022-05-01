package com.service.impl;

import com.domain.User;
import com.domain.UserMeta;
import com.domain.dto.UserDto;
import com.domain.dto.UserMetaDto;
import com.domain.model.UserMetaModel;
import com.domain.model.UserModel;
import com.repository.AuthorityRepository;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    User modelToEntity(UserModel model) {
        if (model == null) return null;
        return User.builder()
                .id(model.getId())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .fullName(model.getFullName())
                .phone(model.getPhone())
                .address(model.getAddress())
                .build();
    }

    UserModel toModel(User entity) {
        if (entity == null) return null;
        return UserModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .authorities(entity.getAuthorities().stream().map(authority -> authority.getId()).collect(Collectors.toSet()))
                .userMetas(entity.getUserMetas().stream().map(this::entityToMetaModel).collect(Collectors.toList()))
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }

    UserDto toDto(User entity) {
        if (entity == null) return null;
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .authorities(entity.getAuthorities().stream().map(authority -> authority.getRoleName()).collect(Collectors.toSet()))
                .userMetas(entity.getUserMetas().stream().map(this::entityToMetaDto).collect(Collectors.toList()))
                .createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }

    UserMeta metaModelToEntity(UserMetaModel model) {
        if (model == null) return null;
        return UserMeta.builder()
                .id(model.getId())
                .metaKey(model.getMetaKey())
                .metaValue(model.getMetaValue())
                .build();
    }

    UserMetaModel entityToMetaModel(UserMeta entity) {
        if (entity == null) return null;
        return UserMetaModel.builder()
                .id(entity.getId())
                .metaKey(entity.getMetaKey())
                .metaValue(entity.getMetaValue())
                .build();
    }

    UserMetaDto entityToMetaDto(UserMeta entity) {
        if (entity == null) return null;
        return UserMetaDto.builder()
                .id(entity.getId())
                .metaKey(entity.getMetaKey())
                .metaValue(entity.getMetaValue())
                .build();
    }

    @Override
    public UserDto saveOrUpdate(UserModel model) {
        User user = modelToEntity(model);
        if (user.getId() != null) {
            User oldUser = userRepository.findById(model.getId()).orElse(null);
            user.setCreatedDate(oldUser.getCreatedDate());
        }
        if (user.getAuthorities() == null)
            model.setAuthorities(Collections.emptySet());
        user.setAuthorities(this.authorityRepository.findAllByIdIn(model.getAuthorities()));
        if (user.getUserMetas() == null)
            model.setUserMetas(Collections.emptyList());

        user.setUserMetas(model.getUserMetas().stream().map(this::metaModelToEntity).collect(Collectors.toList()));
        return this.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        return this.toDto(this.userRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<UserDto> search(String q, Pageable pageable) {
        return this.userRepository.search(q, pageable).map(this::toDto);
    }
}
