package com.entity.dto;

import com.entity.CategoryEntity;
import com.entity.ProductEntity;
import com.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private CategoryDto category;
    private UserDto createdBy;

    public static ProductDto toDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .image(productEntity.getImage())
                .category(CategoryDto.toDto(productEntity.getCategory()))
                .createdBy(UserDto.toDto(productEntity.getCreatedBy()))
                .build();
    }
}
