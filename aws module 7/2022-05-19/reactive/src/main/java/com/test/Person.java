package com.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    private boolean gender;
    private String status;

    public void toStatus() {
        System.out.println("=============Person: " + this.name + " =============");
        System.out.println("age: " + this.age);
        System.out.println("gender: " + (this.gender ? "male" : "female"));
        System.out.println("Status: " + this.status);
    }
}
