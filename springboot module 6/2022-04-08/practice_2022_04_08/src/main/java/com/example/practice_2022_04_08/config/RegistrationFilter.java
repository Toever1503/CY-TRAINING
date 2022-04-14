package com.example.practice_2022_04_08.config;

import com.example.practice_2022_04_08.domain.Adminstrator;
import com.example.practice_2022_04_08.domain.Student;
import com.example.practice_2022_04_08.domain.UserLogin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("RegistrationFilter");
        UserLogin adminstrator = (UserLogin) req.getSession().getAttribute("studentLogin");
        String redirect = "/login?type=student&force=true&redirect=" + req.getServletPath();
        if (adminstrator == null) {
            resp.sendRedirect(redirect);
            return;
        }
        filterChain.doFilter(req, resp);
    }
}
