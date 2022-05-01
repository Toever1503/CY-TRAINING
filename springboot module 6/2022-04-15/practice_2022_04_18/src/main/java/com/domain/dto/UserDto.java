package com.domain.dto;

import com.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends BaseEntity {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String phone;

    private String address;

    private String image;
    private Set<String> authorities;
    private List<UserMetaDto> userMetas;
    private String parent;
    private Date createdDate;
    private Date lastModifiedDate;
}
