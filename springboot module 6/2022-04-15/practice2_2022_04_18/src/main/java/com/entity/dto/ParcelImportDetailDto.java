package com.entity.dto;

import com.entity.ParcelImport;
import com.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParcelImportDetailDto {
    private Long id;

    private Double price;

    private Long quantity;

    private String productImport;

    private Long parcelImport;

}
