package com.example;

public class Rectangle extends ShapeImpl {
    private final Integer width;
    private final Integer height;

    public Rectangle(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public Double area() {
        return (double)(width * height);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Area of a ")
                .append(width)
                .append("x")
                .append(height)
                .append(" rectangle is ")
                .append(area().intValue())
                .toString();
    }
}
