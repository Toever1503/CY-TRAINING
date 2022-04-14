package com.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFilterDTO {
    private String q;
    private Long category;
    private Float minPrice;
    private Float maxPrice;
}
