package com.day_2022_03_17.classes.shape;

public class Circle implements IShape {
    private float Radius;

    public Circle(String color, float radius) {
        this.Radius = radius;
    }

    public float getRadius() {
        return Radius;
    }

    public float setRadius(float radius) {
        return this.Radius = radius;
    }

    public double getArea() {
        return Math.PI * this.Radius * this.Radius;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Circle with radius ").append(this.Radius).append(" has area is ").append(getArea());
        return sb.toString();
    }
}
