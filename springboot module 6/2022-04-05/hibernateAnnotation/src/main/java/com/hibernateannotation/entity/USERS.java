package com.hibernateannotation.entity;

import javax.persistence.*;
import java.io.Serializable;

//@Table(name = "users")
@Entity(name = "users")
public class USERS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority role;

    @Column(name = "is_lock")
    private Boolean isLock;
    @Column(name = "time_failed")
    private Integer timeFailed;

    public USERS() {
    }

    public USERS(String username, String password, Authority role, Boolean lock, Integer timeFailed) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isLock = lock;
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
        return isLock;
    }

    public void setLock(Boolean lock) {
        this.isLock = lock;
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
                ", lock=" + isLock +
                ", timeFailed=" + timeFailed +
                '}';
    }
}
