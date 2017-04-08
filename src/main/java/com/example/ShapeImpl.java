package com.example;

public abstract class ShapeImpl implements Shape {

    protected ShapeType shapeType;

    public ShapeImpl(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public Double getArea() {
        return null;
    }
}
