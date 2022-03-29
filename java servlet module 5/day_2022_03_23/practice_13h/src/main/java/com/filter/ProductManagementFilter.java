package com.filter;


import com.dao.LogDao;
import com.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

//@WebFilter(filterName = "ProductManagementFilter", value = {"/products/*"})
public class ProductManagementFilter implements Filter {
    private LogDao logDao = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logDao = new LogDao();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        System.out.println("filter");
        boolean check = false;
        if (session != null) {
            User userLogin = (User) session.getAttribute("userLogin");
            if (userLogin != null) {
//                long currentLogin = Calendar.getInstance().getTimeInMillis();
//                long lastLogin = (long) session.getAttribute("lastLogin");
//
//                System.out.println("Last try login: " + currentLogin);
//                System.out.println("Session id =>" + session.getId());
//
//                long distance = currentLogin - lastLogin;
//                System.out.println("Distance: " + distance);
//
//                if (distance <= 30000) {
//                    check = true;
//                }
                check = true;
            }
            if (check) {
                chain.doFilter(req, response);
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h1>Login timout! please click <a href='/practice_13h/login'>here</a>  to login again</h1>");
            }
        }
    }

    @Override
    public void destroy() {

    }

}
