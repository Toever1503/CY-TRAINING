package com.practice_2022_04_06.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "departmentName", nullable = false)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff manager;

    @OneToMany(mappedBy = "staffDepartment", fetch= FetchType.LAZY)
    private List<Staff> departmentStaffs;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public String toJson() {
        this.departmentStaffs = null;
        return new JSONObject(this).toString().replace("\"", "'");
    }
}
