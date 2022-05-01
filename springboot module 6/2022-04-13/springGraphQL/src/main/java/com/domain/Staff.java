package com.domain;

import lombok.Data;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
@Data

public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @Transient
    private List<Video> videos;

}