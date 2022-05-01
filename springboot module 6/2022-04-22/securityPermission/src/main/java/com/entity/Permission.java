package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "permissions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Transient
    private static final String READ = "READ";
    @Transient
    private static final String WRITE = "WRITE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission_name", unique = true)
    private String permissionName;

}
