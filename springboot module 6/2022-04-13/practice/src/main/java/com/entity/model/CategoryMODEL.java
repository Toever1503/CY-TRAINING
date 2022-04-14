package com.entity.model;

import com.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMODEL {

    private Long id;
    private String catName;
    private String slug;
    private Long catParent;
    private java.util.List<Long> catChilds;
}
