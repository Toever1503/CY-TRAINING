package com.service.impl;

import com.domain.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public User save(User obj) {
        return this.userRepository.save(obj);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    public List<User> searchUser(String q) {
        return this.userRepository.findAllByUsernameLike(q);
    }
}
