package com.day_2022_03_17.classes;

public class Toyota extends Car {
    public Toyota(String color, String producer) {
        super(color, producer);
    }

    @Override
    public void InitStart() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getColor()).append(" Car is starting");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Toyota("Red", "Toyota").InitStart();
    }
}
