package com.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryModel {
    private Long id;

    private String catName;

    private String catSlug;

    private Integer catOrder;

    private Long catParent;

    private Boolean status;

    private List<Long> subCategories;
}
