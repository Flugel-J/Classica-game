package com.example.classicalgames.models;

public class Cell {
    private int value;
    private int x;//store prev x location
    private int y;//store prev y location
    private int source;

    public Cell() {
    }

    public Cell(int value, int source) {
        this.value = value;
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
