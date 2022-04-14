package com.service.dto;

import com.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private User createdUser;
    private Integer totalPrice;
    private Integer totalQuantity;
    private List<CartProduct> orderProduct;
}
