package com.Controller;

import com.domain.Product;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "product/Products";
    }

    //create method post add product
    @GetMapping("add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @PostMapping
    public String handleAddProduct(Product product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product added successfully");
        return "redirect:/product/add";
    }

    @GetMapping("edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product/editProduct";
    }

    @PostMapping("edit")
    public String handleEditProduct(Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product edited successfully");
        return "redirect:product/edit?id=" + product.getId();
    }

    @GetMapping("delete")
    public String deleteProduct(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProductBy(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully");
        return "redirect:/product";
    }

}
