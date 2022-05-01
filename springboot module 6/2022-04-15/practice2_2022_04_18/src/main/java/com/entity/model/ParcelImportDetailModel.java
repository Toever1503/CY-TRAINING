package com.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParcelImportDetailModel {
    private Long id;

    private Double price;
    private Long quantity;
    private Long productImport;
    private Long parcelImport;

}
