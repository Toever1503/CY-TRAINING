package com.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "orderDetail")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @Column(value="id")
    private Integer id;

    @Column(value="order_id")
    private Long orderId;

    @Column(value="product_id")
    private Long ProductId;

    @Column(value="price")
    private Integer price;

    @Column(value="quantity")
    private Integer quantity;

}
