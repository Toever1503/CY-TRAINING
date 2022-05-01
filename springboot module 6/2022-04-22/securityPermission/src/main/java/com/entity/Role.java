package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Transient
    public static final String USER = "USER";
    @Transient
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", unique = true)
    private String roleName;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private Set<User> roleUsers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "permissions_permission_roles",
            joinColumns = @JoinColumn(name = "role_permissions_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_roles_id")
    )
    private Set<Permission> rolePermissions;

}
