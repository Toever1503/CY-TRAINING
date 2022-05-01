package com.entity.model;

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
public class ParcelImportModel extends BaseEntity {
    private Long id;
    private Long importedBy;
    private List<ParcelImportDetailModel> parcelDetails;
}
