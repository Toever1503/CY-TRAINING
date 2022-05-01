package com.entity.dto;

import com.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto extends BaseEntity {
    private Long id;

    private String name;

    private Float price;

    private String image;

    private String createdBy;
}
