package com.controller.admin;

import com.domain.Product;
import com.domain.User;
import com.service.CategoryService;
import com.service.ProductService;
import com.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.annotation.MultipartConfig;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("admin/products")
@MultipartConfig(maxFileSize = 100048576, maxRequestSize = 100048576)
public class ProductController {
    // autowired bằng constructor
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("pageTitle", "Products");  // để hiển thị trên view
        model.addAttribute("listProducts", productService.findAll(null)); // set list products để hiển thị trên view
        model.addAttribute("listCats", categoryService.findAll()); // set list categories để hiển thị trên view
        return "admin/product/products"; // trả về view
    }

    @GetMapping("/add")
    public String showAddProduct(Model model) {
        model.addAttribute("product", new Product()); // set model product mới
        model.addAttribute("pageTitle", "Add product"); // set page title hiển thị trang
        model.addAttribute("listCats", categoryService.findAll()); // set list categories để hiển thị trên view
        return "admin/product/addProduct";
    }

    @PostMapping
    public String addProduct(Product product, @RequestParam Long productCat, @SessionAttribute("userLogin") User userLogin, @RequestParam(name = "ProductImage") MultipartFile file, RedirectAttributes redirectAttributes) {
        Product saveProduct = null; // khởi tạo product được lưu = null
        product.setCategory(categoryService.findById(productCat)); // set category cho product
        product.setCreatedDate(Calendar.getInstance().getTime());
        product.setCreatedBy(userLogin); // set user create  product

        String img = ImageUtil.uploadImage(file); // upload image lên server và trả về tên file
        String toastType = "error";
        if (img != null) // nếu upload thành công và trả về tên file != null thì set image cho product
            product.setImage("http://localhost:8080/images/" + img);
        saveProduct = productService.addProduct(product); // gọi service add product và gán cho biến saveProduct

        if (saveProduct != null) // nếu saveProduct != null thì thông báo thêm thành công
        {
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Product added successfully!");
        } else // nếu saveProduct == null thì thông báo thêm thất bại
            redirectAttributes.addFlashAttribute("message", "Product add failed!");

        redirectAttributes.addFlashAttribute("toastType", toastType); // set toastType cho redirectAttributes
        return "redirect:/admin/products/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id)); // get product theo id và hiển thị lên view
        model.addAttribute("pageTitle", "Edit product"); // set page title hiển thị trang
        model.addAttribute("listCats", categoryService.findAll()); // set list categories để hiển thị trên view
        return "admin/product/editProduct";
    }

    @PostMapping("/edit/{id}")
    public String handleEditProduct(@PathVariable Long id, Product product, @RequestParam Long productCat, @RequestParam(name = "ProductImage") MultipartFile file, RedirectAttributes redirectAttributes) {
        product.setId(id); // set id cho product
        Product originalProduct = productService.findById(id); // get product ban đầu theo id

        product.setCategory(categoryService.findById(productCat)); // set category cho product lấy từ service lấy từ category id truyền vào
        product.setImage(originalProduct.getImage()); // set image cho product lấy từ product ban đầu

        String img = ImageUtil.uploadImage(file); // upload image lên server và trả về tên file

        String toastType = "error";
        if (img != null) // nếu upload thành công và trả về tên file != null thì set image cho product
            product.setImage("http://localhost:8080/images/" + img);
        Product productUpdate = productService.updateProduct(product); // gọi service update product và gán cho biến productUpdate

        if (productUpdate != null) // nếu productUpdate != null thì thông báo sửa thành công
        {
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Product edited successfully!");
        } else // nếu productUpdate == null thì thông báo sửa thất bại
            redirectAttributes.addFlashAttribute("message", "Product edited failed!");

        redirectAttributes.addFlashAttribute("toastType", toastType); // set toastType cho redirectAttributes
        return "redirect:/admin/products/edit/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String toastType = "error";
        if (productService.deleteById(id)) // xóa product theo id, nếu xóa thành công thì thông báo xóa thành công
        {
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        } else // nếu xóa thất bại thì thông báo xóa thất bại
            redirectAttributes.addFlashAttribute("message", "Product deleted failed!");
        redirectAttributes.addFlashAttribute("toastType", toastType); // set toastType cho redirectAttributes
        return "redirect:/admin/products";
    }

    @GetMapping("search")
    public String searchProduct(@RequestParam("q") String q, Model model) {
        List<Product> searchProductList = productService.searchProduct(q); // lấy danh sách product theo từ khóa tìm kiếm
        model.addAttribute("pageTitle", "Search product");  // để hiển thị trên view
        model.addAttribute("listProducts", searchProductList); // set list products tìm kiếm được để hiển thị trên view

        if (searchProductList.isEmpty()) {
            model.addAttribute("toastType", "error");
            model.addAttribute("message", "No product found");
        }
        return "admin/product/products";

    }

    @GetMapping("filter")
    public String filterProduct(@RequestParam("catId") Long id, Model model) {
        List<Product> filterProductList = productService.findAllByCategory(id); // lấy danh sách product theo caegory
        model.addAttribute("pageTitle", "Filter product");  // để hiển thị trên view
        model.addAttribute("listProducts", filterProductList); // set list products tìm kiếm được để hiển thị trên view
        model.addAttribute("listCats", categoryService.findAll()); // set list categories để hiển thị trên view

        if (filterProductList.isEmpty()) {
            model.addAttribute("toastType", "error");
            model.addAttribute("message", "No product found");
        }
        return "admin/product/products";
    }

}
