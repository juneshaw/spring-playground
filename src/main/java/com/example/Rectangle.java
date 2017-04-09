package com.example;

public class Rectangle extends ShapeImpl {
    private Double x;
    private Double y;

    public Rectangle(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public Double area() {
        return x * y;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Area of a ")
                .append(x)
                .append("x")
                .append(y)
                .append(" rectangle is ")
                .append(area())
                .toString();
    }
}
