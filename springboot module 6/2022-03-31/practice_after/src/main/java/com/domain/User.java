package com.domain;

public class User {
    private Long Id;
    private String Username;
    private String Password;
    private Boolean Role;

    public User() {
    }

    public User(Long id, String username, String password, Boolean role) {
        Id = id;
        Username = username;
        Password = password;
        Role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getRole() {
        return Role;
    }

    public void setRole(Boolean role) {
        Role = role;
    }
}
