package com.example;

public class TestShape extends ShapeImpl {
    String type;

    public TestShape(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public Double area() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
