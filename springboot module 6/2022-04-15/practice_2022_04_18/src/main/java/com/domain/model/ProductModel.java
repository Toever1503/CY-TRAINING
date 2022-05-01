package com.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Long createdBy;
    private Date createdDate;
    private Date lastModifiedDate;
}
