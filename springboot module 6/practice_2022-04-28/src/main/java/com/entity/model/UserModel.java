package com.entity.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Long id;

    private String username;
    private String password;

    private String fullName;

    private String email;

    private Boolean status = true;

    private Integer activeCode = 0;

    private Integer timeFailed;

    private java.util.List<Integer> authorities;
}
