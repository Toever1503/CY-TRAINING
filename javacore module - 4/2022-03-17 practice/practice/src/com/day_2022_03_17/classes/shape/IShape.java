package com.day_2022_03_17.classes.shape;

public interface IShape {
    String color = null;

    abstract double getArea();

    abstract String toString();

    public static void main(String[] args) {
        IShape circle = new Circle( "red", 10);
        IShape rectangle = new Rectangle( "blue", 10, 20);

        System.out.println(circle);
        System.out.println(rectangle);
    }
}
