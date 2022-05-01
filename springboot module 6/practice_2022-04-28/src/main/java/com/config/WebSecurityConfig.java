package com.config;

import com.entity.UserEntity;
import com.entity.dto.UserDto;
import com.repository.AuthorityRepository;
import com.repository.UserRepository;
import com.security.SecurityUtils;
import com.service.CustomUserDetail;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/cart/checkout").authenticated()
                .anyRequest().permitAll();

        http.formLogin().loginPage("/signin")
                .loginProcessingUrl("/signin").permitAll();

        http.oauth2Login()
                .successHandler((req, res, attributes) -> {
                    UserService userService = (UserService) userDetailsService;
                    DefaultOAuth2User oauth2User = (DefaultOAuth2User) (attributes.getPrincipal());
                    String email = oauth2User.getAttributes().get("email").toString();

                    UserEntity original = userService.findByEmail(email);
                    if (original == null) {//create new user
                        original = userRepository.save(new UserEntity(null, oauth2User.getAttribute("sub"), passwordEncoder.encode(UUID.randomUUID().toString()), oauth2User.getName(), email, true, 0, 0, Collections.singletonList(authorityRepository.findByAuthority(SecurityUtils.ROLE_ADMIN))));
                    }
                    Authentication authentication = new UsernamePasswordAuthenticationToken(new CustomUserDetail(original), null, original.getAuthorities() == null ? null :
                            original.getAuthorities().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toList()));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    res.sendRedirect("/");
                })
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll();
    }
}
