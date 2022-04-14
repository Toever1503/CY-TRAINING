package com.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Integer price;

    @Column(name="image")
    private String image;

    @Column(name = "created_user")
    private Long createdUser;

    @Column(name = "cat_id")
    private Long catId;


}
