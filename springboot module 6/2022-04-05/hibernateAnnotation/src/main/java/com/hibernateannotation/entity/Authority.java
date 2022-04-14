package com.hibernateannotation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Table(name = "authority")
@Entity(name = "authority")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany
    @JoinColumn(name = "id")
    private List<USERS> users;

    public Authority(){}

    public Authority(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<USERS> getUsers() {
        return users;
    }

    public void setUsers(List<USERS> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
