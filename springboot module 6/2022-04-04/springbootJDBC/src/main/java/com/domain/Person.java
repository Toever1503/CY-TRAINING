package com.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(value = "person")
public class Person {
    @Id
    private Long id;
    @Column(value = "name")
    private String name;
    @Column(value = "lastName")
    private String lastName;
    @Column(value = "age")
    private Integer age;

    @CreatedDate
    @Transient
    private Date createdDate;

    public Person() {
    }

    public Person(Long id, String name, String lastName, Integer age, Date createdDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", createdDate=" + createdDate +
                '}';
    }
}
