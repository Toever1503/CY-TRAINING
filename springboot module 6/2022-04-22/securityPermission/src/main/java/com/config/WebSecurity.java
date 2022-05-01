package com.config;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/", "/home", "/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler((req, res, auth) -> {
                    String username = req.getParameter("username");
                    String message = "Username or password is incorrect";
                    if (!username.isEmpty()) {
                        User u = userRepository.findByUsername(username);
                        if (u != null) {
                            u.setTimeFailed(u.getTimeFailed() + 1);
                            if (u.getTimeFailed() >= 3) {
                                message = "Your account has been locked. Please contact admin to unlock your account.";
                            }
                            else
                            {
                                userRepository.save(u);
                            }
                        }
                    }
                    req.getSession().setAttribute("login_message", message);
                    req.getRequestDispatcher("/login").forward(req, res);
                })
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
    }
}
