package com.service.impl;

import com.entity.ProductEntity;
import com.entity.dto.ProductDto;
import com.entity.model.ProductModel;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import com.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    ProductEntity toEntity(ProductModel productModel) {
        if (productModel == null) return null;
        return ProductEntity.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .image(productModel.getImage())
                .price(productModel.getPrice())
                .build();
    }

    @Override
    public ProductDto findById(Long aLong) {
        return ProductDto.toDto(productRepository.findById(aLong).orElse(null));
    }

    @Override
    public Page<ProductDto> findAll(Pageable page) {
        return this.productRepository.findAll(page).map(ProductDto::toDto);
    }

    @Override
    public Page<ProductDto> search(String keyword, Pageable page) {
        return this.productRepository.findAllByNameLike("%" + keyword + "%", page).map(ProductDto::toDto);
    }

    @Override
    public ProductDto add(ProductModel productModel) {
        ProductEntity productEntity = toEntity(productModel);
        if (productEntity != null) {
            productEntity.setCategory(categoryRepository.findById(productModel.getCategory()).orElse(null));
        }
//        productEntity.setCreatedBy(userRepository.findById(productModel.getId()).orElse(null));
        return ProductDto.toDto(productRepository.save(productEntity));
    }

    @Override
    public ProductDto update(ProductModel productModel) {
        ProductEntity original = productRepository.findById(productModel.getId()).orElse(null);
        ProductEntity productEntity = toEntity(productModel);
        if (original.getImage().equalsIgnoreCase(productEntity.getImage())) {
            productEntity.setImage(original.getImage());
        }
//        productEntity.setCreatedBy(userRepository.findById(productModel.getId()).orElse(null));
        return ProductDto.toDto(productRepository.save(productEntity));
    }

    @Override
    public Boolean deleteById(Long aLong) {
        productRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Page<ProductDto> findAllByCategory(Long categoryId, Pageable page) {
        return this.productRepository.findAllByCategoryId(categoryId, page).map(ProductDto::toDto);
    }
}
