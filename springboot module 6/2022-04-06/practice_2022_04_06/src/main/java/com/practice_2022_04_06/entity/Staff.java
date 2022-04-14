package com.practice_2022_04_06.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name = "staffs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "date_works", nullable = false)
    private Integer workDates;
    @Column(name = "salary", nullable = false)
    private Integer salary;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position staffPos;
    @ManyToOne(fetch = FetchType.EAGER)
    private Department staffDepartment;

    @OneToMany(mappedBy = "workStaff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Timekeeping> dateWorks;

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                ", workDates=" + workDates +
                ", salary=" + salary +
                '}';
    }

    public String toJson() {
        Position pos = this.getStaffPos();
        pos.setStaffList(null);
        Department department = this.getStaffDepartment();
        department.setDepartmentStaffs(null);

        String json = new JSONObject(this).toString().replace("\"", "'");
        return json;
    }
}
