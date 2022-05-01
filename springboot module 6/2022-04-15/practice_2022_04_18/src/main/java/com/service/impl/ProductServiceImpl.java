package com.service.impl;

import com.domain.Product;
import com.domain.dto.ProductDto;
import com.domain.model.ProductModel;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import com.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    Product modelToEntity(ProductModel model) {
        if (model == null) return null;
        return Product.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .price(model.getPrice())
                .build();
    }

    ProductModel toModel(Product product) {
        if (product == null) return null;
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdBy(product.getCreatedBy().getId())
                .build();
    }

    ProductDto entityToDto(Product product) {
        if (product == null) return null;
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdBy(product.getCreatedBy().getUsername())
                .build();
    }

    @Override
    public ProductDto saveOrUpdate(ProductModel d) {
        Product product = modelToEntity(d);
        if (d.getId() != null) {
            Product oldProduct = productRepository.findById(d.getId()).orElse(null);
            product.setCreatedDate(oldProduct.getCreatedDate());
        }
//        product.setCreatedBy(userRepository.findById(d.getCreatedBy()).orElse(null));
        return this.entityToDto(this.productRepository.save(product));
    }

    @Override
    public ProductDto findById(Long id) {
        return entityToDto(this.productRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(Long id) {
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public Page<ProductDto> search(String q, Pageable pageable) {
        return this.productRepository.findAllByNameLike(q, pageable).map(this::entityToDto);
    }
}
