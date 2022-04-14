package com.config;

import com.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("userLogin");
        String redirect = "/login?force=true&redirect=" + req.getServletPath();

        if (user == null) {
            res.sendRedirect(redirect);
            return;
        } else {
            if (user != null && !user.getRole()) {
                res.sendRedirect(redirect);
                return;
            }
        }
        filterChain.doFilter(req, res);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
