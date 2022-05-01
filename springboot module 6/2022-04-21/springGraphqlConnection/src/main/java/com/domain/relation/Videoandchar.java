package com.domain.relation;

import com.domain.relation.id.VideoandcharId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videoandchar")
public class Videoandchar {
    @EmbeddedId
    private VideoandcharId id;

    public VideoandcharId getId() {
        return id;
    }

    public void setId(VideoandcharId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}