package com.example;

import java.text.DecimalFormat;

public class Circle extends ShapeImpl {
    private Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    @Override
    public Double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        String resultStr;
        DecimalFormat df = new DecimalFormat("#.#####");
        resultStr = df.format(area());
        return new StringBuilder()
                .append("Area of a circle with a radius of ")
                .append(radius)
                .append(" is ")
                .append(resultStr).toString();
    }

}
