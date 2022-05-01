package com.domain.relation;

import com.domain.relation.id.VideoandstudioId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videoandstudio")
public class Videoandstudio {
    @EmbeddedId
    private VideoandstudioId id;

    public VideoandstudioId getId() {
        return id;
    }

    public void setId(VideoandstudioId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}