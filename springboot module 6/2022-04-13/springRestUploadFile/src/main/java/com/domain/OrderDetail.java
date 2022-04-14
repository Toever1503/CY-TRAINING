package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "orderDetail")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="order_id")
    private Long orderId;

    @Column(name="product_id")
    private Long ProductId;

    @Column(name="price")
    private Integer price;

    @Column(name="quantity")
    private Integer quantity;

}
