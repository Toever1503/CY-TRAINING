package com.practice_2022_04_06.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer page;
    private Integer size;
    private String by;
    private String direction;
}
