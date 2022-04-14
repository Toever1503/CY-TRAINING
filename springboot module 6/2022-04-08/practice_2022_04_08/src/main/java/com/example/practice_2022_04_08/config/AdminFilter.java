package com.example.practice_2022_04_08.config;

import com.example.practice_2022_04_08.domain.Student;
import com.example.practice_2022_04_08.domain.UserLogin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        UserLogin student = (UserLogin) req.getSession().getAttribute("adminstratorLogin");
        String redirect = "/login?type=admin&force=true&redirect=" + req.getServletPath();
        if (student == null) {
            resp.sendRedirect(redirect);
            return;
        }
        filterChain.doFilter(req, resp);
    }
}
