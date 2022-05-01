package com.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.domain.dto.ProductDto;
import com.domain.model.ProductModel;
import com.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductMutationResolver implements GraphQLMutationResolver {

    private final ProductService productService;

    public ProductMutationResolver(ProductService productService) {
        this.productService = productService;
    }

    public ProductDto createProduct(ProductModel model) {
        return productService.saveOrUpdate(model);
    }

    public ProductDto updateProduct(Long id, ProductModel model) {
        model.setId(id);
        return productService.saveOrUpdate(model);
    }

    public boolean deleteProduct(Long id) {
        return productService.deleteById(id);
    }
}
