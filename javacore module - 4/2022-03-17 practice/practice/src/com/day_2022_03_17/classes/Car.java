package com.day_2022_03_17.classes;

public abstract class Car {
    private String Color;
    private String Producer;

    public Car(String color, String producer) {
        this.Color = color;
        this.Producer = producer;
    }
    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        this.Color = color;
    }
    public String getProducer() {
        return Producer;
    }
    public void setProducer(String producer) {
        this.Producer = producer;
    }
    public abstract void InitStart();

}
