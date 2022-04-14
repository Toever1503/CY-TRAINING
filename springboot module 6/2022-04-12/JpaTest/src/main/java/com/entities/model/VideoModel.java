package com.entities.model;

import com.entities.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VideoModel {
    private Long id;
    private String title;
    private String titleJp;
    private String poster;
    private String description;
    private Integer views;
    private Integer active;
    private String alternateTtitle;
    private String country;
    private String season;
    private Integer year;
    private String releaseDate;
    private String status;
    private Integer rating;
    private Long userId;
    private Integer duration;
    private Integer volume;
    private Long idAnilist;
    private Instant dateCreate;
    private String imageBanner;
    private Instant dateUpdate;
    private String trailer;

    private List<Long> studios;
    private List<Long> vCategories;
    private List<Long> vStaffs;
    private List<Long> vTags;
    private List<Long> vChars;
}
