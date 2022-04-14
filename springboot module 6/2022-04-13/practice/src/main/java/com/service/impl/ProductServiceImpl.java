package com.service.impl;

import com.entity.Product;
import com.entity.dto.ProductDTO;
import com.entity.model.ProductMODEL;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import com.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    Product toEntity(ProductMODEL model) {
        if (model == null) return null;
        return Product.builder()
                .id(model.getId())
                .title(model.getTitle())
                .price(model.getPrice())
                .image(model.getImage())
                .build();
    }

    ProductDTO toDTO(Product product) {
        if (product == null) return null;
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .image(product.getImage())
                .category(product.getCategory())
                .build();
    }

    ProductMODEL entityToModel(Product entity) {
        if (entity == null) return null;
        return ProductMODEL.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .image(entity.getImage())
                .category(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .build();
    }

    @Override
    @Transactional
    public ProductMODEL save(ProductMODEL productMODEL) {
        Product entity = toEntity(productMODEL);
        entity.setCategory(categoryRepository.findById(productMODEL.getCategory()).get());
        if (entity.getId() != null) {
            Product oldProduct = productRepository.findById(entity.getId()).orElse(null);
            if (productMODEL.getImage() == null)
                entity.setImage(productMODEL.getImage());
        }
        return entityToModel(productRepository.save(entity));
    }

    @Override
    public ProductMODEL update(ProductMODEL productMODEL) {
        return save(productMODEL);
    }

    @Override
    @Transactional
    public ProductDTO findById(Long id) {
        return toDTO(productRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Page<ProductDTO> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable).map(this::toDTO);
    }


    @Override
    public Page<ProductMODEL> filter(String q, Float minPrice, Float maxPrice, Long catId, Pageable page) {
        return this.productRepository.search(q, minPrice, maxPrice, catId, page).map(this::entityToModel);
    }

    @Override
    public Page<ProductDTO> findAllByCategoryId(Long id, Pageable pageable) {
        return this.productRepository.findAllByCategoryId(id, pageable).map(this::toDTO);
    }

    @Override
    public Page<ProductDTO> search(String q, Pageable pageable) {
        return this.productRepository.findAllByTitleLike("%".concat(q.concat("%")), pageable).map(this::toDTO);
    }
}
