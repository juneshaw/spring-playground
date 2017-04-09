package com.example;

public class Rectangle extends ShapeImpl {
    private Integer x;
    private Integer y;

    public Rectangle(Integer x, Integer y) {
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
    public Double area() {
        return (double)(x * y);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Area of a ")
                .append(x)
                .append("x")
                .append(y)
                .append(" rectangle is ")
                .append(area().intValue())
                .toString();
    }
}
