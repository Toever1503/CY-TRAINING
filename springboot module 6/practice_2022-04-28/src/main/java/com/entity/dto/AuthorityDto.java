package com.entity.dto;

import com.entity.AuthorityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorityDto {

    private Integer id;

    private String authority;

    public static AuthorityDto toDto(AuthorityEntity authority) {
        if (authority == null) return null;
        return AuthorityDto.builder()
                .id(authority.getId())
                .authority(authority.getAuthority())
                .build();
    }
}
