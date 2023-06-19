package com.example.classicalgames.models;

public class MinesweeperBoard {
    private int x;
    private int y;
    private int numberOfMine;

    public MinesweeperBoard() {
    }

    public MinesweeperBoard(int x, int y, int numberOfMine) {
        this.x = x;
        this.y = y;
        this.numberOfMine = numberOfMine;
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

    public int getNumberOfMine() {
        return numberOfMine;
    }

    public void setNumberOfMine(int numberOfMine) {
        this.numberOfMine = numberOfMine;
    }
}
