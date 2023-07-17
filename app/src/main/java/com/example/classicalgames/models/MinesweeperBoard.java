package com.example.classicalgames.models;

public class MinesweeperBoard {
    private int numberOfColumn;
    private int numberOfRow;
    private int totalMine;
    private int numberOfRemainMine;
    private String difficult_level;

    public MinesweeperBoard() {
    }

    public MinesweeperBoard(int numberOfColumn, int numberOfRow, int totalMine, int numberOfRemainMine, String difficult_level) {
        this.numberOfColumn = numberOfColumn;
        this.numberOfRow = numberOfRow;
        this.totalMine = totalMine;
        this.numberOfRemainMine = numberOfRemainMine;
        this.difficult_level = difficult_level;
    }

    public String getDifficult_level() {
        return difficult_level;
    }

    public void setDifficult_level(String difficult_level) {
        this.difficult_level = difficult_level;
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

    public int getNumberOfRemainMine() {
        return numberOfRemainMine;
    }

    public void setNumberOfRemainMine(int numberOfRemainMine) {
        this.numberOfRemainMine = numberOfRemainMine;
    }
}
