package com.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikeTypeMODEL {
    private Long id;
    private String typeName;
    private Long typeParent;
    private Float price;
}
