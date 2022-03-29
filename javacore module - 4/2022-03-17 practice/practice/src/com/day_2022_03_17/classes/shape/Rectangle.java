package com.day_2022_03_17.classes.shape;

public class Rectangle implements IShape {
    private float Width;
    private float Length;

    public Rectangle(String color, float width, float length) {
        this.Width = width;
        this.Length = length;
    }

    public double getArea() {
        return Width * Length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Circle with length ").append(this.Length)
                .append(", width ").append(this.Width)
                .append(" has area is ").append(getArea());
        return sb.toString();
    }
}
