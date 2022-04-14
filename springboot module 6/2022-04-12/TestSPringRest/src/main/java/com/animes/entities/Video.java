package com.animes.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "video")
public class Video {
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
    }

    public Instant getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Instant dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Long getIdAnilist() {
        return idAnilist;
    }

    public void setIdAnilist(Long idAnilist) {
        this.idAnilist = idAnilist;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlternateTtitle() {
        return alternateTtitle;
    }

    public void setAlternateTtitle(String alternateTtitle) {
        this.alternateTtitle = alternateTtitle;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitleJp() {
        return titleJp;
    }

    public void setTitleJp(String titleJp) {
        this.titleJp = titleJp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}