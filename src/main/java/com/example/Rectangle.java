package com.example;

import static com.example.ShapeType.RECTANGLE;

public class Rectangle extends ShapeImpl {
    private Integer x;
    private Integer y;

    public Rectangle(Integer x, Integer y) {
        super(RECTANGLE);
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public Double getArea() {
        return null;
//        return MathService.rectangleArea(x, y);
    }
}
