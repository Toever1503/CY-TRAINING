package com.entity.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMODEL {
    private Long id;
    private String title;
    private Float price;
    private String image;
    private Long category;
}
