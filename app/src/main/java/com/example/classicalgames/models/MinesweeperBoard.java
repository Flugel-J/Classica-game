package com.example.classicalgames.models;

public class MinesweeperBoard {
    private int x;
    private int y;
    private int totalMine;
    private int numberOfMine;

    public MinesweeperBoard() {
    }

    public MinesweeperBoard(int x, int y, int totalMine, int numberOfMine) {
        this.x = x;
        this.y = y;
        this.totalMine = totalMine;
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

    public int getTotalMine() {
        return totalMine;
    }

    public void setTotalMine(int totalMine) {
        this.totalMine = totalMine;
    }

    public int getNumberOfMine() {
        return numberOfMine;
    }

    public void setNumberOfMine(int numberOfMine) {
        this.numberOfMine = numberOfMine;
    }
}
