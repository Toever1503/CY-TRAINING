package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

@Service
public class UserDetail implements UserDetailsService {

    private final UserRepository userRepository;
    private final EntityManagerFactory em;

    public UserDetail(UserRepository userRepository, EntityManagerFactory em) {
        this.userRepository = userRepository;
        this.em = em;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }
}
