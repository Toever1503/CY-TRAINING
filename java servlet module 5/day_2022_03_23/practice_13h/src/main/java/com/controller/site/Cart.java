package com.controller.site;

import com.dao.ProductDao;
import com.entity.Product;
import com.util.Func;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Cart", urlPatterns = {"/my-cart", "/my-cart/add", "/my-cart/delete"})
public class Cart extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletPath();

        List<Product> cart = (List<Product>) req.getSession().getAttribute("cart");

        if(cart == null)
            cart = new ArrayList<>();

        switch (Func.getLastPath(req.getRequestURI())) {
            case "my-cart":
                req.setAttribute("cart", cart);
                break;
            case "add": {
                String id = req.getParameter("id");
                Product product = productDao.findById(Long.valueOf(id));

                cart.add(product);
                req.getSession().setAttribute("cart", cart);
                List<Product> productList = productDao.findLatestProduct();
                req.setAttribute("latestProduct", productList);

                req.setAttribute("addP", "success");
                req.getRequestDispatcher("/view/homePage.jsp").forward(req, resp);
                return;
            }
            case "delete": {
                String id = req.getParameter("id");
//                cart = cart.stream().filter(p -> p.getId() != Long.valueOf(id)).collect(Collectors.toList());
                for (int i = 0; i < cart.size(); i++) {
                    if(Long.valueOf(id) == cart.get(i).getId()) {
                        cart.remove(i);
                        break;
                    }
                }
                req.setAttribute("delete", "success");
                req.getSession().setAttribute("cart", cart);
                break;
            }
            default:
                break;
        }
        req.getRequestDispatcher("/view/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
