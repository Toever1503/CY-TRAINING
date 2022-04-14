package com.service.dto;

import com.domain.Categories;
import com.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String image;
    private User createdUser;
    private Categories category;
}
