package com.domain.relation.id;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoandcategoryId implements Serializable {
    private static final long serialVersionUID = -2426217223197779267L;
    @Column(name = "video", nullable = false)
    private Long video;

    @Column(name = "category", nullable = false)
    private Long category;

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoandcategoryId entity = (VideoandcategoryId) o;
        return Objects.equals(this.video, entity.video) &&
                Objects.equals(this.category, entity.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video, category);
    }

}