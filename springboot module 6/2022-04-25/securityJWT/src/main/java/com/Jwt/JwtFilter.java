package com.Jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try {
            if (token != null && token.startsWith("Bearer ")) {
                String username = jwtTokenProvider.getUsernameFromToken(token);
                if (username != null && jwtTokenProvider.validateToken(token)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(401);
        response.getWriter().println("Unauthorized");
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        LoginRequest obj = new LoginRequest();
        obj.setUsername("admin");

        System.out.println("before call: "+ obj);
        testAddress(obj);
        System.out.println("after call: "+ obj);
    }
    public static void testAddress(LoginRequest obj){
        obj.setPassword("1234");
    }

}
