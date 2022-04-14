package com.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    private ProductDto product;
    private Integer quantity;

    public CartProduct(ProductDto product) {
        this.product = product;
        this.quantity = 1;
    }
}
