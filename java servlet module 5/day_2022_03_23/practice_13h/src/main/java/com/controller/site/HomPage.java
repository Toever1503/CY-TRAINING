package com.controller.site;

import com.dao.ProductDao;
import com.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/home", loadOnStartup = 10)
public class HomPage extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        String pageStr = req.getParameter("pageXX");

        if (pageStr != null)
            page = Integer.valueOf(pageStr);

        List<Product> productList = productDao.findAll(page);
        req.setAttribute("latestProduct", productList);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/view/homePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
