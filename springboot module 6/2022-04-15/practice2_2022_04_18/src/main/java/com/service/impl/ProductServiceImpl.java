package com.service.impl;

import com.entity.Product;
import com.entity.dto.ProductDto;
import com.entity.model.ProductModel;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Product modelToEntity(ProductModel productModel) {
        if (productModel == null) return null;
        return Product.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .image(productModel.getImage())
                .build();
    }

    ProductModel entityToModel(Product product) {
        if (product == null) return null;
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .createdBy(product.getCreatedBy() != null ? product.getCreatedBy().getId() : null)
                .build();
    }

    ProductDto entityToDto(Product product) {
        if (product == null) return null;

        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .createdBy(product.getCreatedBy() != null ? product.getCreatedBy().getUsername() : null)
                .build();
        dto.setCreatedAt(product.getCreatedAt());
        dto.setLastModifiedAt(product.getLastModifiedAt());
        return dto;
    }

    @Override
    public ProductDto saveOrUpdate(ProductModel model) {
        Product entity = modelToEntity(model);
        if (model.getId() != null) {
            Product oldProduct = this.productRepository.findById(model.getId()).orElse(null);
            entity.setCreatedAt(Calendar.getInstance().getTime());
        }
        entity = this.productRepository.save(entity);
        return entity == null ? null : entityToDto(entity);
    }

    @Override
    public ProductDto findById(Long id) {
        return entityToDto(this.productRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteBYId(Long id) {
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public Page<ProductDto> search(String q, Pageable pageable) {
        return this.productRepository.findAllByNameLike("%" + q + "%", pageable).map(this::entityToDto);
    }
}
