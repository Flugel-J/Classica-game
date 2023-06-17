package com.example.classicalgames.models;

public class Cell {
    private int value;
    private int x;
    private int y;
    private int source;

    public Cell() {
    }

    public Cell(int value, int x, int y, int source) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
