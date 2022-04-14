package com.config.filter;

import com.service.dto.CartProduct;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class MyCartFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    // khi nguoi dung truy cap vao url /my-cart hoac /my-cart/add thi se chay ham nay
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest; // chuyen doi request sang http request
        Map<Long, CartProduct> cart = (Map<Long, CartProduct>) req.getSession().getAttribute("cart"); // lay gio hang tu session
        if (cart == null) // neu gio hang chua co san thi tao gio hang moi
            cart = new HashMap<Long, CartProduct>();

        req.getSession().setAttribute("cart", cart); // luu gio hang vao session
        filterChain.doFilter(req, servletResponse); // chay tiep filter
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
