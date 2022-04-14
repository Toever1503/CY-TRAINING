package com.controller.site;

import com.domain.Category;
import com.service.CategoryService;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    // autowired bằng constructor
    private final CategoryService categoryService;
    private final ProductService productService;

    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        Map<Category, List<Category>> menuList = new HashMap<>(); // tạo list menu category hiển thị trên header

        List<Category> parentMenuList = categoryService.findAll().stream().filter(cat -> cat.getStatus() != false && cat.getCatParent() == null).collect(Collectors.toList()); // lấy ra danh sách các menu category cha có trạng thái active, category cha là category có catParent = null
        parentMenuList.sort((a, b) -> a.getCatOrder() - b.getCatOrder());  // sắp xếp danh sách category theo thứ tự của thuộc tính menuOrder

        // duyệt từng category cha và lấy ra danh sách các category con của category cha đó
        parentMenuList.forEach(category -> {
            List<Category> subItem = categoryService.findAllByParent(category.getId()); // lấy ra danh sách category con của category cha từ id
            if (subItem != null) { // nếu danh sách category con của category cha khác null, thì ta filter ra danh sách category con có trạng thái active
                subItem = subItem.stream().filter(subI -> subI.getStatus() != false).collect(Collectors.toList());
                subItem.sort((a, b) -> a.getCatOrder() - b.getCatOrder()); // sắp xếp danh sách category theo thứ tự của thuộc tính menuOrder
            }
            menuList.put(category, subItem); // thêm vào list menuList
        });

        model.addAttribute("menuList", menuList); // thêm menu vào model để hiển thị trên view
        model.addAttribute("latestProduct", productService.findAll(page)); // thêm danh sách sản phẩm mới nhất vào model để hiển thị trên view
        model.addAttribute("page", page); // thêm số trang hiện tại vào model để hiển thị trên view

        return "site/home";
    }

    @GetMapping("/404")
    public String error404(Model model) {
        model.addAttribute("pageTitle", "404");
        return "site/404";
    }

    @GetMapping("/category/{catSlug}")
    public String showProductInCategoryPage(@PathVariable String catSlug, Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page){
        Category cat = categoryService.findBySlug(catSlug);
        if(cat == null)
            return "redirect:/404";
        model.addAttribute("listProducts", productService.findAllByCategory(cat.getId()));
        model.addAttribute("pageTitle", cat.getCatName());
        model.addAttribute("page", page); // thêm số trang hiện tại vào model để hiển thị trên view
        return "site/category";
    }

}
