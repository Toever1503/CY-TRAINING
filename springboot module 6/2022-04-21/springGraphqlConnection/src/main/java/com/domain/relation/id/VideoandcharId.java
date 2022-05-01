package com.domain.relation.id;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoandcharId implements Serializable {
    private static final long serialVersionUID = 7904267305201156452L;
    @Column(name = "video", nullable = false)
    private Long video;

    @Column(name = "chars", nullable = false)
    private Long chars;

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }

    public Long getChars() {
        return chars;
    }

    public void setChars(Long chars) {
        this.chars = chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoandcharId entity = (VideoandcharId) o;
        return Objects.equals(this.video, entity.video) &&
                Objects.equals(this.chars, entity.chars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video, chars);
    }

}