package com.entity.dto;

import com.entity.BaseEntity;
import com.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParcelImportDto extends BaseEntity {
    private Long id;

    private String importedBy;
    private List<ParcelImportDetailDto> parcelDetailDtos;
}
