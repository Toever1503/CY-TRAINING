package com.domain;

public class Role {
    private Integer Id;
    private String RoleName;

    public Role() {
    }
    public Role(Integer id, String roleName) {
        this.Id = id;
        this.RoleName = roleName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" + "Id=" + Id + ", RoleName=" + RoleName + '}';
    }
}
