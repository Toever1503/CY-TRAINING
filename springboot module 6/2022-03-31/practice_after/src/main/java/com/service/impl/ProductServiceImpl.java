package com.service.impl;


import com.domain.Category;
import com.domain.Product;
import com.domain.User;
import com.service.CategoryService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static Long increment = 1l;
    private List<Product> products;
    private final CategoryService categoryService;
    private final UserService userService;

    // sử dụng tạm thời list product thay cho việc tạo  repository
    public ProductServiceImpl(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.products = new ArrayList<Product>();

        Category category = categoryService.findById(1l);
        Category category1 = categoryService.findById(2l);
        User user = userService.findById(1l);
        Date date = Calendar.getInstance().getTime();
        this.products.add(new Product(increment++, "Solid Color Loose Irregular Shirt ...", 234, 199, "https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/E2/AA/8271a425-7a69-4386-99b8-68d8e5a4cb6f.jpg.webp?s=360x480", category, 0, true, date, user));
        this.products.add(new Product(increment++, "Solid Lace Button V-neck Blouse ...", 672, 399, "https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/80/D0/aed3566f-cffc-4fff-aaea-ece9a5e1313f.jpg.webp?s=360x480", category, 15, true, date, user));
        this.products.add(new Product(increment++, "Solid Pocket Maxi Shirt ...", 72, 45, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/E4/9A/8d21fb67-35a2-4d38-a69f-8a463ee81474.jpg.webp?s=240x320", category, 20, true, date, user));
        this.products.add(new Product(increment++, "Paisley Ethnic Pattern Black Kimono ...", 59, 23, "https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/19/8B/53ae43c9-fa60-4672-88fd-dbc2f3b38675.jpg.webp?s=360x480", category, 25, true, date, user));
        this.products.add(new Product(increment++, "Solid Pocket Maxi Shirt ...", 62, 22, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/EF/9C/2f37a919-8a74-452a-9683-2c43949f5fdd.jpg.webp?s=240x320", category, 30, true, date, user));
        this.products.add(new Product(increment++, "Denim Contrast Stitching Kimono ...", 16, 10, "https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/BD/54/c2ffde86-59d6-4a54-bae0-71a2c7381e8c.jpg.webp?s=360x480", category, 35, true, date, user));
        this.products.add(new Product(increment++, "Solid Pocket Maxi Shirt ...", 5, 2, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B4/D5/8e98a18f-b845-46ec-a1fa-f1013623eb5f.jpeg.webp?s=240x320", category1, 40, true, date, user));
        this.products.add(new Product(increment++, "Mens Waffle Loungewear Set ...", 6, 3, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B1/AF/03eb62e1-df83-4324-b064-379b84464fb2.jpg.webp?s=240x320", category1, 45, true, date, user));
        this.products.add(new Product(increment++, "Holiday Stripe Co-ords For ...", 7, 4, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/36/29/4dd53689-86d3-4400-90b6-f1283b703412.jpg.webp?s=240x320", category, 50, true, date, user));
        this.products.add(new Product(increment++, "Contrast Patchwork Hooded Sports C ...", 8, 5, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B0/B8/7a73b63b-d167-49c8-96fb-bc70f3a56b53.jpg.webp?s=240x320", category1, 55, true, date, user));
        this.products.add(new Product(increment++, "Anime Elements Rectangular Neckl", 9, 6, "https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/DD/DF/7d140c1a-b890-42e7-addb-dd1cf5624c33.jpg.webp?s=240x320", category, 60, true, date, user));
        this.products.add(new Product(increment++, "Plus Size Graffiti Striped Shirt", 10, 7, "https://imgaz1.chiccdn.com/os/202203/20220315012737_420.jpg.webp", category, 65, true, date, user));
        this.products.add(new Product(increment++, "Japanese Cat Print Hoodies", 10, 7, "https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/AA/39/26a725bb-806e-490f-8f32-dd6e0d22ee0f.jpg?s=360x480", category, 65, true, date, user));
    }

    @Override
    public Product addProduct(Product product) {
        product.setId(increment++);
        this.products.add(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return this.products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Product updateProduct(Product product) {
        this.products = this.products.stream().map(p -> p.getId().equals(product.getId()) ? product : p).collect(Collectors.toList());
        return product;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<Product> findAll(Integer page) {
        if (page == null) return this.products; // trả về tất cả product nếu page = null
        return this.products.stream().skip(page * 6).limit(6).collect(Collectors.toList()); // trả về 2 product cuả page
    }

    @Override
    public List<Product> searchProduct(String q) {
        return this.products.stream().filter(product -> product.getName().contains(q)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByCategory(Long catId) { // tìm kiếm danh sách product con bằng category id
        return this.products.stream().filter(product -> product.getCategory().getId().equals(catId)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
    }
}
