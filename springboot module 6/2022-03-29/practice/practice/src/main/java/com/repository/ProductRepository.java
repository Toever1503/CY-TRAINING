package com.repository;

import com.domain.Product;

public interface ProductRepository extends IRepository<Product, Long> {
    int sumTotalPricesOfProduct();
}
