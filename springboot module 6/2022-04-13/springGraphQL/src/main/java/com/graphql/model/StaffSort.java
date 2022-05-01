package com.graphql.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class StaffSort {
    private StaffSortBy by;
    private Sort.Direction direction;
}
