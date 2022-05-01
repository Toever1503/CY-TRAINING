package com.entity.model;

import com.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserModel extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Long createdBy;
}
