package com.domain.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoModel {
    private Long id;

    private String title;

    private String titleJp;

    private String description;

    private Integer views;

    private Boolean active;

    private String alternateTtitle;

    private String country;

    private String season;

    private Integer year;

    private String releaseDate;

    private String status;

    private Integer rating;
}
