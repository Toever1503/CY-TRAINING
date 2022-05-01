package com.domain.relation.id;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoandtagId implements Serializable {
    private static final long serialVersionUID = 3532225143022327424L;
    @Column(name = "video", nullable = false)
    private Long video;

    @Column(name = "tag", nullable = false)
    private Long tag;

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoandtagId entity = (VideoandtagId) o;
        return Objects.equals(this.video, entity.video) &&
                Objects.equals(this.tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video, tag);
    }

}