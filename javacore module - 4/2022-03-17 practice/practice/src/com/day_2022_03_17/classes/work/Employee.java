package com.day_2022_03_17.classes.work;

import java.util.Arrays;

public class Employee {
    private int Id;
    private String Name;

    public Employee(int id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void DisplayEmployee() {
        System.out.println("Employee Information");
        System.out.println("Employee Id: " + this.Id);
        System.out.println("Employee Name: " + this.Name);
    }

    public static void main(String[] args) {
        Employee e1 = new BackendDevelopment(1, "John", "Spring boot", "EN", "Java");
        Employee e2 = new FrontendDevelopment(2, "Jane", "Angular", Arrays.asList("HTML", "CSS").toArray(new String[2]));

        e1.DisplayEmployee();
        System.out.println("---------------------------------");
        e2.DisplayEmployee();
    }
}
