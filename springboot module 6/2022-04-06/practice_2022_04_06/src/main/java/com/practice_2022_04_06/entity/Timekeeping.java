package com.practice_2022_04_06.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "timekeepings")
@Getter
@Setter
public class Timekeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time_date", length = 5)
    private String timeStart;

    @Column(name = "time_end", length = 5)
    private String timeEnd;

    @ManyToOne(fetch= FetchType.LAZY)
    private Staff workStaff;

    @Override
    public String toString() {
        return "Timekeeping{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }
}
