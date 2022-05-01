package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
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
