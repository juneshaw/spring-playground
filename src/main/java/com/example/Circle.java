package com.example;

public class Circle extends ShapeImpl {
    private Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    @Override
    public Double getArea() {
        return MathService.circleArea(radius);
    }
}
