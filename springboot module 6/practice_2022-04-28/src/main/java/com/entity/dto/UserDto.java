package com.entity.dto;


import com.entity.AuthorityEntity;
import com.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String username;
    private String password;

    private String fullName;
    private String email;

    private java.util.List<AuthorityDto> authorities;

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) return null;
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .authorities(userEntity.getAuthorities().stream().map(AuthorityDto::toDto).collect(java.util.stream.Collectors.toList()))
                .build();
    }
}
