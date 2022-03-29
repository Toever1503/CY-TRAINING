package com.day_2022_03_21.io;

public class Employee {
    private Integer id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void DisplayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Employee{id=" + this.id + ", name=" + this.name + "}";
    }
}
