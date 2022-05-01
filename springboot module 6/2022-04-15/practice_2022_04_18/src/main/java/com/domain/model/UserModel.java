package com.domain.model;


import com.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String phone;

    private String address;

    private String image;

    private Set<Integer> authorities;

    private List<UserMetaModel> userMetas;

    private Long parent;

    private Date createdDate;

    private Date lastModifiedDate;
}
