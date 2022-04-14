package com.controller.admin;

import com.domain.Category;
import com.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/categories") // path default cho controller
public class CategoryController {

    // autowired bằng constructor
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("listCats", categoryService.findAll()); // set list thể loại
        model.addAttribute("pageTitle", "Categories"); // set title hiển thị trên trang
        return "admin/category/categories";
    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        model.addAttribute("listCats", categoryService.findAll()); // set list thể loại
        model.addAttribute("category", new Category());  // set model category mới
        model.addAttribute("pageTitle", "Add category"); // set title hiển thị trên trang
        return "admin/category/addCategory";
    }

    @PostMapping
    public String handleAddCategory(Category category, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        Long catParent = Long.parseLong(req.getParameter("catParentId")); // get cat id  từ form để set cat parent
        category.setCatParent(categoryService.findById(catParent)); // get cart from service và  set cat parent
        category.setStatus(true); // set status = true, mặc định khi tạo mới là true
        category.setCatSlug(category.getCatName().toLowerCase().replace(" ", "-")); // set slug = cat name để lưu trữ, thay thế khoảng trắng bằng dấu -, Ví dụ: "Thể loại game" -> "the-loai-game"

        categoryService.addCategory(category); // add category vào service
        redirectAttributes.addFlashAttribute("message", "Category added successfully"); // set message hiển thị thông báo
        redirectAttributes.addFlashAttribute("toastType", "success"); // set toastType cho redirectAttributes
        return "redirect:/admin/categories/add";
    }

    @GetMapping("/edit/{id}")
    // get id trên url, pathvariable path chứa id của thể loại, ví dụ 'localhost:8080/admin/categories/edit/1' thì 1 là id của thể loại
    public String showEditCategory(@PathVariable("id") Long id, Model model) {
        Category cat = categoryService.findById(id); // get category theo id
        model.addAttribute("pageTitle", "Edit category"); // set title hiển thị trên trang
        model.addAttribute("catParentId", cat.getCatParent() != null ? cat.getCatParent().getId() : null); // set cat parent
        model.addAttribute("category", cat); // set model category
        model.addAttribute("listCats", categoryService.findAll().stream().filter(iCat -> iCat.getCatParent() == null).collect(Collectors.toList())); // set list thể loại
        return "admin/category/editCategory"; // return view khi get
    }

    @PostMapping("/edit/{id}")
    public String handleEditCategory(@PathVariable("id") Long id, Category category, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(id); // get category theo id
        category.setId(id); // set id cho category theo path variable
        category.setStatus(cat.getStatus()); // set status cho category từ cat sau khi truy vấn

        Long catParent = Long.parseLong(req.getParameter("catParentId")); // parse cat parent id từ form
        if (catParent != 0) { // nếu cat parent id khác 0 thì set cat parent cho category
            category.setCatParent(categoryService.findById(catParent));
        }
        category.setCatSlug(category.getCatName().toLowerCase().replace(" ", "-")); // set slug = cat name để lưu trữ, thay thế khoảng trắng bằng dấu -, Ví dụ: "Thể loại game" -> "the-loai-game"
        categoryService.updateCategory(category); // update category vào service

        redirectAttributes.addFlashAttribute("message", "Category added successfully"); // set message hiển thị thông báo
        redirectAttributes.addFlashAttribute("toastType", "success"); // set toastType cho redirectAttributes
        return "redirect:/admin/categories/edit/" + id; // redirect view
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id); // delete category theo id
        redirectAttributes.addFlashAttribute("message", "Category deleted successfully"); // set message hiển thị thông báo
        redirectAttributes.addFlashAttribute("toastType", "success"); // set toastType cho redirectAttributes
        return "redirect:/admin/categories";
    }

    @GetMapping("/disable/{id}")
    public String disableCategory(@PathVariable("id") Long id, @RequestParam("status") String status, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(id); // get category theo id
        cat.setStatus(status.equalsIgnoreCase("active") ? true : false); // set status cho category khi actice hoặc disable
        redirectAttributes.addFlashAttribute("message", "Category " + status + " successfully"); // set message hiển thị thông báo
        redirectAttributes.addFlashAttribute("toastType", "success"); // set toastType cho redirectAttributes
        return "redirect:/admin/categories";
    }

    @GetMapping("search")
    public String searchCategory(@RequestParam("q") String q, Model model) {
        List<Category> searchCategoryList = categoryService.searchCat(q); // tìm kiếm category theo q
        model.addAttribute("pageTitle", "Search category"); // set title hiển thị trên trang
        model.addAttribute("listCats", searchCategoryList); // set list thể loại tìm kiếm được

        if (searchCategoryList.isEmpty()) {
            model.addAttribute("toastType", "error");
            model.addAttribute("message", "No product found");
        }
        return "admin/category/categories";
    }
}
