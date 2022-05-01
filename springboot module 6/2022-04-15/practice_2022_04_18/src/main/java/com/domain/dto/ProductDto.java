package com.domain.dto;

import com.domain.BaseEntity;
import com.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto extends BaseEntity {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String createdBy;
    private Date createdDate;
    private Date lastModifiedDate;
}
