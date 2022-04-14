package com.service.impl;

import com.domain.Product;
import com.repository.ProductRepository;
import com.service.CategoryService;
import com.service.ProductService;
import com.service.UserService;
import com.service.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    private ProductDto toDto(Product obj) {
        if (obj == null) return null;

        ProductDto dto = new ProductDto();
        dto.setId(obj.getId());
        dto.setName(obj.getName());
        dto.setDescription(obj.getDescription());
        dto.setPrice(obj.getPrice());
        dto.setImage(obj.getImage());
        dto.setCreatedUser(this.userService.findById(obj.getCreatedUser()));
//        dto.setCategory(this.categoryService.find(obj.getId()));
        return dto;
    }

    private Product toEntity(ProductDto dto) {
        if (dto == null) return null;
        Product obj = new Product();
        obj.setId(dto.getId());
        obj.setName(dto.getName());
        obj.setDescription(dto.getDescription());
        obj.setPrice(dto.getPrice());
        obj.setImage(dto.getImage());
        obj.setCreatedUser(dto.getCreatedUser().getId());
        return obj;
    }

    @Override
    public ProductDto findById(Long id) {
        return toDto(this.productRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto obj) {
        return toDto(this.productRepository.save(toEntity(obj)));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<ProductDto> findAll(Pageable page) {
        return this.productRepository.findAll(page).map(this::toDto);
    }

    @Override
    public List<ProductDto> search(String q) {
        return this.productRepository.findAllByNameLike(q).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByCategory(Long id) {
        return this.productRepository.findAllByCatId(id);
    }
}
