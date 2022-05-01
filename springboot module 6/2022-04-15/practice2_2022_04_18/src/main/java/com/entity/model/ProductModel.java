package com.entity.model;

import com.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductModel extends BaseEntity {
    private Long id;

    private String name;

    private Float price;

    private String image;

    private Long createdBy;
}
