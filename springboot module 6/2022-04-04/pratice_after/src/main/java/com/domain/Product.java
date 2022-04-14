package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(value="id")
    private Long id;

    @Column(value="name")
    private String name;

    @Column(value="description")
    private String description;

    @Column(value="price")
    private Integer price;

    @Column(value="image")
    private String image;

    @Column(value = "createdUser")
    private Long createdUser;

    @Column(value = "cat_id")
    private Long catId;


}
