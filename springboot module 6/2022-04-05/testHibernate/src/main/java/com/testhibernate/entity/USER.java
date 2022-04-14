package com.testhibernate.entity;

public class USER {

    private Long id;
    private String username;
    private String password;
    private Authority role;
    private Boolean lock;
    private Integer timeFailed;

    public USER() {
    }

    public USER(String username, String password, Authority role, Boolean lock, Integer timeFailed) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lock = lock;
        this.timeFailed = timeFailed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getRole() {
        return role;
    }

    public void setRole(Authority role) {
        this.role = role;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Integer getTimeFailed() {
        return timeFailed;
    }

    public void setTimeFailed(Integer timeFailed) {
        this.timeFailed = timeFailed;
    }

    @Override
    public String toString() {
        return "USER{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lock=" + lock +
                ", timeFailed=" + timeFailed +
                '}';
    }
}
