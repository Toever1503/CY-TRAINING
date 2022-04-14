package com.entity.dto;

import com.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String catName;
    private String slug;
    private Category catParent;
    private java.util.List<Category> catChilds;
}
