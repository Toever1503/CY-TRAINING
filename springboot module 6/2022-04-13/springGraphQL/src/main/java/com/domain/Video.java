package com.domain;

import com.graphql.model.*;
import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "titleJp", length = 500)
    private String titleJp;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "season")
    private String season;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "image_banner")
    private String imageBanner;

    @Transient
    private StaffConnection staffs;

    @Transient
    private StudioConnection studios;

    public StaffConnection staffs(int page, int perPage, StaffSort sort) {
        return this.staffs;
    }

    public StudioConnection studios(int page, int perPage, StudioSort sort) {
        return this.studios;
    }

}
