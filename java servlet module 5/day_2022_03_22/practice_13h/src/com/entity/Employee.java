package com.entity;

import java.lang.reflect.Field;

public class Employee {
    private Long Id;
    private String Name;
    private Double Salary;

    public Employee() {

    }

    public Employee(Long id, String name, Double salary) {
        Id = id;
        Name = name;
        Salary = salary;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public String DisplayInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Emp Name: ").append(this.Name);
        sb.append("\r\nSalary: ").append(this.Salary);
        return sb.toString();
    }

    @Override
    public String toString() {
        return DisplayInformation();
    }

    public static void main(String[] args) {
        Class s = new Employee(1l, "12fa",  Double.valueOf("42412")).getClass();
        System.out.println("Total fields: "+ s.getFields().length);
        try {
            System.out.println(s.getField("Name").getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //        for(Field n: s.getFields())
//            System.out.println(n);
    }
}
