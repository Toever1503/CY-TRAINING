package com.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "totalPrice")
    private Integer total;

    @Column(name = "createdUser")
    private Long createdUser;

    @Column(name = "totalQuantity")
    private Integer totalQuantity;

}
