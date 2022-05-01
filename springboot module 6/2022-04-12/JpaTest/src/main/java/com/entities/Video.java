package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "video")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "titleJp", length = 500)
    private String titleJp;

    @Column(name = "poster")
    private String poster;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "views")
    private Integer views;

    @Column(name = "active")
    private Integer active;

    @Column(name = "alternateTtitle", length = 500)
    private String alternateTtitle;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "season", length = 6)
    private String season;

    @Column(name = "year")
    private Integer year;

    @Column(name = "releaseDate", length = 100)
    private String releaseDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "idAnilist")
    private Long idAnilist;

    @Column(name = "dateCreate")
    private Instant dateCreate;

    @Column(name = "imageBanner", length = 500)
    private String imageBanner;

    @Column(name = "dateUpdate")
    private Instant dateUpdate;

    @Column(name = "trailer", length = 500)
    private String trailer;

//    @JsonIgnoreProperties("studioVideos")
    @ManyToMany
    @JoinTable(name = "videoandstudio",
            joinColumns = @JoinColumn(name = "video"),
            inverseJoinColumns = @JoinColumn(name = "studio"))
    private List<Studio> studios;


//    @JsonManagedReference
//    @ManyToMany
//    @JoinTable(name = "videoandcategory",
//            joinColumns = @JoinColumn(name = "video"),
//            inverseJoinColumns = @JoinColumn(name = "category"))
//    private List<Category> vCategories;
//
//    @JsonManagedReference
//    @ManyToMany
//    @JoinTable(name = "videoandstaff",
//            joinColumns = @JoinColumn(name = "video"),
//            inverseJoinColumns = @JoinColumn(name = "staff"))
//    private List<Staff> vStaffs;


}