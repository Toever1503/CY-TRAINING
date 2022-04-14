package com.controller.admin;

import com.service.dto.CartProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @GetMapping
    public String adminDashboard(HttpSession session, Model model) {
        Map<Long, CartProduct> cart = (Map<Long, CartProduct>) session.getAttribute("cart");
        StringBuilder soldProductLabels = new StringBuilder();
        StringBuilder soldProductData = new StringBuilder();

        if (cart != null) {
            cart.forEach((k, v) -> {
                soldProductLabels.append("'").append(v.getProduct().getName()).append("',");
                soldProductData.append(v.getQuantity()).append(",");
            });
            soldProductLabels.setLength(soldProductLabels.length() - 1);
            soldProductData.setLength(soldProductData.length() - 1);
        }


        model.addAttribute("pageTitle", "Stats Dashboard");
        model.addAttribute("soldProductLabels", soldProductLabels.toString());
        model.addAttribute("soldProductData", soldProductData.toString());
        return "admin/dashboard";
    }
}
