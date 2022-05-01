package com.domain.relation;

import com.domain.relation.id.VideoandtagId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videoandtag")
public class Videoandtag {
    @EmbeddedId
    private VideoandtagId id;

    public VideoandtagId getId() {
        return id;
    }

    public void setId(VideoandtagId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}