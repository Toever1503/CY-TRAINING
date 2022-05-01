package com.domain.relation.id;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoandstaffId implements Serializable {
    private static final long serialVersionUID = 2449053235526940695L;
    @Column(name = "video", nullable = false)
    private Long video;

    @Column(name = "staff", nullable = false)
    private Long staff;

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }

    public Long getStaff() {
        return staff;
    }

    public void setStaff(Long staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoandstaffId entity = (VideoandstaffId) o;
        return Objects.equals(this.staff, entity.staff) &&
                Objects.equals(this.video, entity.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staff, video);
    }

}