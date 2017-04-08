package com.example;

public class Circle implements Shape {
    private Integer radius;

    public Circle(Integer radius) {

        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    @Override
    public Double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
