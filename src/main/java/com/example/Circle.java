package com.example;

import static com.example.ShapeType.CIRCLE;

public class Circle extends ShapeImpl {
    private Integer radius;

    public Circle(Integer radius) {
        super(CIRCLE);
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
