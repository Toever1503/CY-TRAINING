package com.entity.dto;

import com.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String createdBy;
}
