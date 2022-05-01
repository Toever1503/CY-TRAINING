package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;


@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    private Date lastModifiedDate;

    @PrePersist
    public void prePersist() {
        System.out.println("BaseEntity init");
        Calendar calendar = Calendar.getInstance();
        createdDate = calendar.getTime();
        lastModifiedDate = calendar.getTime();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("BaseEntity update");
        lastModifiedDate = Calendar.getInstance().getTime();
    }
}
