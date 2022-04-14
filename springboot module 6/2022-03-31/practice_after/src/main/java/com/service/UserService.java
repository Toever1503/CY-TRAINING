package com.service;

import com.domain.User;

import java.util.List;

public interface UserService {
    User addUser(User u);

    User updateUser(User u);

    boolean deleteById(Long id);

    User findById(Long id);

    User findByUserName(String name);

    List<User> findAll(Integer page);
    List<User> searchUser(String q);

    Integer getTotalPage();
}
