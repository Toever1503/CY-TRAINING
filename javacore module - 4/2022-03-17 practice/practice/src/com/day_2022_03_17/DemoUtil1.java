package com.day_2022_03_17;

import java.util.Scanner;

public class DemoUtil1 {

    private int number;
    private String name;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void InputInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        setNumber(scanner.nextInt());

        System.out.print("Enter name: ");
        setName(scanner.next());
        scanner.close();
    }

    public void OutputInformation() {
        System.out.println("\nInformation");
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
    }

    public static void main(String[] args) {
        DemoUtil1 demoUtil1 = new DemoUtil1();
        demoUtil1.InputInformation();
        demoUtil1.OutputInformation();
    }
}
