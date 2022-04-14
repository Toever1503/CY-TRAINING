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
@Table(name = "registered_subjects")
@Entity
public class RegisteredSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AvailSubject availSubject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "registration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredTime;

    @Column(name ="status")
    private Boolean status;
}
