package com.controller.site;

import com.entity.dto.CartProduct;
import com.entity.dto.CustomResponseEntity;
import com.entity.dto.ProductDto;
import com.security.SecurityUtils;
import com.service.MailService;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MailService mailService;

    @ResponseBody
    @GetMapping
    public ResponseEntity getCart(HttpSession session) {
        Map<Long, CartProduct> cart = (Map<Long, CartProduct>) session.getAttribute("cart");
        if (cart == null) {
            return CustomResponseEntity.of("Cart is empty", null);
        }
        return CustomResponseEntity.of("", cart.values());
    }

    @ResponseBody
    @GetMapping("/add/{id}")
    public ResponseEntity addProductToCart(@PathVariable Long id, HttpSession session) {
        Map<Long, CartProduct> cart = (Map<Long, CartProduct>) session.getAttribute("cart");


        ProductDto product = productService.findById(id);
        if (product == null) {
            return CustomResponseEntity.of("Product not found", null);
        }

        if (cart == null) {
            cart = new HashMap<>();
            cart.put(product.getId(), new CartProduct(product, product.getPrice(), 1));
            session.setAttribute("cart", cart);
        } else {
            CartProduct cartProduct = cart.get(product.getId());
            if (cartProduct == null) {
                cart.put(product.getId(), new CartProduct(product, product.getPrice(), 1));
            } else
                cart.get(product.getId()).setQuantity(cart.get(product.getId()).getQuantity() + 1);
        }
        return CustomResponseEntity.of("", "null");
    }

    @ResponseBody
    @GetMapping("delete/{id}")
    public ResponseEntity deleteProductFromCart(@PathVariable Long id, HttpSession session) {
        Map<Long, CartProduct> cart = (Map<Long, CartProduct>) session.getAttribute("cart");
        if (cart == null) {
            return CustomResponseEntity.of("Cart is empty", null);
        }
        cart.remove(id);
        return CustomResponseEntity.of("", "");
    }

    @ResponseBody
    @GetMapping("checkout")
    public ResponseEntity checkout(@SessionAttribute("cart") Map<Long, CartProduct> cart) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("products", cart.values());
        vars.put("total", cart.values().stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum());
        try {
            mailService.sendMail("checkout.html", SecurityUtils.getCurrentLoggedUser().getUser().getEmail(), "Thank You For Ordered", vars);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        cart.clear();
        return CustomResponseEntity.of("", "");
    }

}
