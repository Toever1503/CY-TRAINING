package com.domain.relation.id;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoandstudioId implements Serializable {
    private static final long serialVersionUID = -6783818262375808L;
    @Column(name = "studio", nullable = false)
    private Long studio;

    @Column(name = "video", nullable = false)
    private Long video;

    public Long getStudio() {
        return studio;
    }

    public void setStudio(Long studio) {
        this.studio = studio;
    }

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoandstudioId entity = (VideoandstudioId) o;
        return Objects.equals(this.studio, entity.studio) &&
                Objects.equals(this.video, entity.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studio, video);
    }

}