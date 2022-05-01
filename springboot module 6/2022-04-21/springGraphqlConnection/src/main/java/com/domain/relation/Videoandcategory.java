package com.domain.relation;

import com.domain.relation.id.VideoandcategoryId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videoandcategory")
public class Videoandcategory {
    @EmbeddedId
    private VideoandcategoryId id;

    public VideoandcategoryId getId() {
        return id;
    }

    public void setId(VideoandcategoryId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}