package com.domain.relation;

import com.domain.relation.id.VideoandstaffId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videoandstaff")
public class Videoandstaff {
    @EmbeddedId
    private VideoandstaffId id;

    public VideoandstaffId getId() {
        return id;
    }

    public void setId(VideoandstaffId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}