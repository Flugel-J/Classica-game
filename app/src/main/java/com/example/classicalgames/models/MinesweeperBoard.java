package com.example.classicalgames.models;

public class MinesweeperBoard {
    private int numberOfColumn;
    private int numberOfRow;
    private int totalMine;
    private int numberOfMine;

    public MinesweeperBoard() {
    }

    public MinesweeperBoard(int x, int y, int totalMine, int numberOfMine) {
        this.numberOfColumn = x;
        this.numberOfRow = y;
        this.totalMine = totalMine;
        this.numberOfMine = numberOfMine;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setNumberOfColumn(int numberOfColumn) {
        this.numberOfColumn = numberOfColumn;
    }

    public int getNumberOfRow() {
        return numberOfRow;
    }

    public void setNumberOfRow(int numberOfRow) {
        this.numberOfRow = numberOfRow;
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
