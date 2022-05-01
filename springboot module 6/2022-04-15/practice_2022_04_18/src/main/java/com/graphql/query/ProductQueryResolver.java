package com.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.domain.Product;
import com.domain.dto.ProductDto;
import com.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductQueryResolver implements GraphQLQueryResolver {

    private final ProductService productService;

    public ProductQueryResolver(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDto> getProducts(int first, int size) {
        return productService.findAll(PageRequest.of(first, size)).getContent();
    }

    public List<ProductDto> getProducts(String q, int first, int size) {
        return productService.search(q, PageRequest.of(first, size)).getContent();
    }

    public ProductDto getProduct(Long id) {
        return productService.findById(id);
    }
}
