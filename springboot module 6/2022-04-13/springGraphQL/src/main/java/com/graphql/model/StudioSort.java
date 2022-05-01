package com.graphql.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class StudioSort {
    private StudioSortBy by;
    private Sort.Direction direction;
}
