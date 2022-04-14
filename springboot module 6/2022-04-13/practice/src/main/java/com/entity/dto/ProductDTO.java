package com.entity.dto;


import com.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String q;
    private Long id;
    private String title;
    private Float price;
    private String image;
    private Category category;
}
