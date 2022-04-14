package com.practice_2022_04_06.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "positions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pos_name", nullable = false)
    private String posName;

    @Column(name = "salary_factor", nullable = false)
    private Float salaryFactor;

    @OneToMany(mappedBy = "staffPos", fetch = FetchType.LAZY)
    private List<Staff> staffList;

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", posName='" + posName + '\'' +
                ", salaryFactor=" + salaryFactor +
                '}';
    }
}
