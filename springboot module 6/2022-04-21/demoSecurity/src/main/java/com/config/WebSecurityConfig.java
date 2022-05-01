package com.config;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("haunv").password("123456").roles("USER");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/custom-login")
                .loginProcessingUrl("/custom-login").permitAll()
                .failureHandler((req, res, e) -> {
                    String username = req.getParameter("username");
                    String message = "Username or password is incorrect";
                    if (!username.isEmpty()) {
                        User u = userRepository.findByUsername(username);
                        if (u != null) {
                            u.setTimeFailed(u.getTimeFailed() + 1);
                            userRepository.save(u);
                            if (u.getTimeFailed() >= 3) {
                                message = "Your account has been locked. Please contact admin to unlock your account.";
                            }
                        }
                    }
                    req.getSession().setAttribute("login_message", message);
                    req.getRequestDispatcher("/customLogin").forward(req, res);
                })
                .defaultSuccessUrl("/admin")
                .and()
                .logout()
                .permitAll();
    }
}
