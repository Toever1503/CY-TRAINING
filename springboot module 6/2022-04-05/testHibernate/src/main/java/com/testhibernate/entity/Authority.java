package com.testhibernate.entity;

import java.util.List;

public class Authority {
    private Integer id;
    private String roleName;

    private List<USER> users;

    public Authority(){}
    public Authority(String role) {
        this.roleName = role;
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

    public List<USER> getUsers() {
        return users;
    }

    public void setUsers(List<USER> users) {
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
