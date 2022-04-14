package com.controller.site;

import com.domain.Categories;
import com.domain.User;
import com.service.CategoryService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;
import com.service.dto.CartProduct;
import com.service.dto.OrderDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, OrderService orderService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String home(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        model.addAttribute("products", productService.findAll(PageRequest.of(page, 6)));
        return "site/index";
    }

    @GetMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes, @SessionAttribute("cart") Map<Long, CartProduct> cart, HttpSession session, @SessionAttribute(name="userLogin", required = false) User userLogin) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedUser(userLogin);

        AtomicReference<Integer> totalPrice = new AtomicReference<>(0);
        AtomicReference<Integer> totalQuantity = new AtomicReference<>(0);
        List<CartProduct> cartProducts = cart.values().stream().map(cartProduct -> {
            totalQuantity.updateAndGet(v -> v + cartProduct.getQuantity());
            totalPrice.updateAndGet(v -> v + cartProduct.getQuantity() * cartProduct.getProduct().getPrice());
            return cartProduct;
        }).collect(Collectors.toList());

        orderDto.setTotalPrice(totalPrice.get());
        orderDto.setTotalQuantity(totalQuantity.get());
        orderDto.setOrderProduct(cartProducts);
        this.orderService.save(orderDto);

        redirectAttributes.addFlashAttribute("toastType", "success");
        redirectAttributes.addFlashAttribute("message", "Checkout Successfully!, You can continue shopping");
        cart.clear();
        session.setAttribute("cart", cart);
        return "redirect:/my-cart";
    }

    @GetMapping("/my-order")
    public String myOrders(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @SessionAttribute(name="userLogin", required = false) User userLogin) {
        model.addAttribute("myOrders", this.orderService.findAllByUser(userLogin.getId(), PageRequest.of(page, 6)));
        return "site/myOrder";
    }

}
