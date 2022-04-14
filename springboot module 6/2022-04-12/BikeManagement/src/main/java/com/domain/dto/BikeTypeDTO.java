package com.domain.dto;

import com.domain.BikeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BikeTypeDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String typeName;
    private BikeType typeParent;
    private Float price;
}
