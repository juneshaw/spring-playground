package com.example;

public class CalculateInfo {
    private String operation;
    private String x;
    private String y;

    public CalculateInfo(String operation, String x, String y) {
        this.operation = operation;
        this.x = x;
        this.y = y;
    }

    public String getOperation() {
        return operation;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
