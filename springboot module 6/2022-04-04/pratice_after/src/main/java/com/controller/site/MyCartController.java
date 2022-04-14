package com.controller.site;

import com.service.ProductService;
import com.service.dto.CartProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/my-cart") // cart là giỏ hàng
public class MyCartController {
    // autowired bằng constructor
    private final ProductService productService;

    public MyCartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping // giỏ hàng đã được thêm tự động từ MyCartFilter.class trong package config
    public String showMyCart() {
        return "site/myCart";
    }

    @GetMapping("add/{id}")
    public String addCart(HttpSession session, @SessionAttribute("cart") Map<Long, CartProduct> cart, @PathVariable Long id, @RequestParam("redirect") String redirectUrl, RedirectAttributes redirectAttributes) {
        System.out.println("cart of add method is null? " + (cart == null));
        CartProduct cartProduct = cart.get(id); // lấy ra sản phẩm trong giỏ hàng
        if(cartProduct != null) { // nếu sản phẩm đã có trong giỏ hàng thì tăng số lượng lên 1
            cartProduct.setQuantity(cartProduct.getQuantity() + 1);
            cart.put(id, cartProduct);
        }
        else
            cart.put(id, new CartProduct(productService.findById(id))); // thêm sản phẩm vào giỏ hàng
        System.out.println("cart size: " + cart.size());

        session.setAttribute("cart", cart); // cập nhật lại giỏ hàng vào session
        redirectAttributes.addFlashAttribute("message", "Product added successfully!");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/" + redirectUrl; // chuyển về trang mà người dùng đã truy cập bằng tham số parameter truyền thêm vào url, ví dụ 'localhost:8080/my-cart/add/1?redirect=/'  thì  sẽ thêm sản phẩm có id là 1 vào giỏ hàng và chuyển về trang /, trang / là trang home tương ứng với đường path localhost:8080
    }

    @GetMapping("delete/{id}")
    public String deleteProductOutOfCart(@PathVariable Long id, HttpSession session, @SessionAttribute("cart") Map<Long, CartProduct> cart, RedirectAttributes redirectAttributes) {
        if (cart.remove(id) != null) // nếu xóa thành công thì set message xóa thành công
            redirectAttributes.addFlashAttribute("message", "Product deleted from cart");
        else // nếu xóa thất bại thì set message xóa thất bại
            redirectAttributes.addFlashAttribute("message", "Product not found in cart");
//        session.setAttribute("cart", cart); // cập nhật lại giỏ hàng vào session
        redirectAttributes.addFlashAttribute("message", "Product deleted out of cart successfully!");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/my-cart";
    }
}
