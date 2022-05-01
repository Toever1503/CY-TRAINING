package com.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "auth_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "active_code")
    private Integer activeCode = 0;

    @Column(name = "timeFailed")
    private Integer timeFailed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "authority_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private java.util.List<AuthorityEntity> authorities;
}
