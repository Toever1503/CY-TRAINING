package com.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "totalPrice")
    private Integer total;

    @Column(value = "createdUser")
    private Long createdUser;

    @Column(value = "totalQuantity")
    private Integer totalQuantity;

}
