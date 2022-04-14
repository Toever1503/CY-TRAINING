package com.service;

import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    User save(User obj);

    boolean deleteById(Long id);

    Page<User> findAll(Pageable page);

    List<User> searchUser(String q);
}
