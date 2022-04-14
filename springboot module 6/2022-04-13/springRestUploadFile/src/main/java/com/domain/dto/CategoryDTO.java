package com.domain.dto;

import com.domain.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;

    private String catName;

    private String catSlug;

    private Integer catOrder;

    private Categories catParent;

    private Boolean status;

    private List<Categories> subCategories;
}
