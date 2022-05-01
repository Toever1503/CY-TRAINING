package com.service;

import com.entity.AuthorityEnitty;
import com.entity.UserEntity;
import com.repository.AuthorityRepository;
import com.repository.CustomUserDetailService;
import com.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;

//        AuthorityEnitty auth1 = this.authorityRepository.save(new AuthorityEnitty(null, "ROLE_USER"));
//        AuthorityEnitty auth2 = this.authorityRepository.save(new AuthorityEnitty(null, "ROLE_ADMIN"));
//
//        this.userRepository.save(new UserEntity(null, "admin", passwordEncoder.encode("1234"), 0, Set.of(auth2, auth1)));
//        this.userRepository.save(new UserEntity(null, "user", passwordEncoder.encode("1234"), 0, Set.of(auth1)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetailService(this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
