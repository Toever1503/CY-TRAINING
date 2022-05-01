package com.service.impl;

import com.entity.UserEntity;
import com.entity.dto.UserDto;
import com.entity.model.UserModel;
import com.repository.AuthorityRepository;
import com.repository.UserRepository;
import com.security.SecurityUtils;
import com.service.CustomUserDetail;
import com.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
//        userRepository.save(new UserEntity(null, "admin", passwordEncoder.encode("1234"), "admin", "admin@afa.com", true, 0, 0, Collections.singletonList(authorityRepository.findByAuthority(SecurityUtils.ROLE_ADMIN))));
    }

    UserEntity toEntity(UserModel userModel) {
        if (userModel == null) return null;
        return UserEntity.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .status(userModel.getStatus())
                .activeCode(userModel.getActiveCode())
                .timeFailed(userModel.getTimeFailed())
                .build();
    }

    @Override
    public UserDto findById(Long aLong) {
        return UserDto.toDto(userRepository.findById(aLong).orElse(null));
    }

    @Override
    public Page<UserDto> findAll(Pageable page) {
        return this.userRepository.findAll(page).map(UserDto::toDto);
    }

    @Override
    public Page<UserDto> search(String keyword, Pageable page) {
        return this.userRepository.search(keyword, page).map(UserDto::toDto);
    }

    @Override
    public UserDto add(UserModel userModel) {
        UserEntity userEntity = toEntity(userModel);
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        if (userModel.getAuthorities() == null || userModel.getAuthorities().isEmpty()) {
            userEntity.setAuthorities(Collections.singletonList(authorityRepository.findByAuthority(SecurityUtils.ROLE_USER)));
        } else
            userEntity.setAuthorities(authorityRepository.findAllByIdIn(userModel.getAuthorities()));
        return UserDto.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto update(UserModel userModel) {
        UserEntity original = userRepository.findById(userModel.getId()).orElse(null);
        UserEntity userEntity = toEntity(userModel);
        if (userModel.getAuthorities() == null || userModel.getAuthorities().isEmpty()) {
            userEntity.setAuthorities(original.getAuthorities());
        }
        return UserDto.toDto(userRepository.save(userEntity));
    }

    @Override
    public Boolean deleteById(Long aLong) {
        userRepository.deleteById(aLong);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(username, username).orElse(null);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
