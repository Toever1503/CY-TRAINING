package com.example.practice_2022_04_08.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "points")
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point")
    private Float point;

    @Column(name = "point_type")
    private String pointType;

    @Column(name = "point_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pointDate;

    @ManyToOne
    @JoinColumn(name = "registeredSubject_id")
    private RegisteredSubject registeredSubject;

    @ManyToOne
    @JoinColumn(name = "adminstrator_id")
    private Adminstrator adminstrator;

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", point=" + point +
                ", pointType='" + pointType + '\'' +
                ", pointDate=" + pointDate +
                '}';
    }
}
