package com.example.classicalgames.presenters;


import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.classicalgames.contracts.CellDAO;
import com.example.classicalgames.contracts.Database2048;
import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.models.CellsArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;
    private int totalBlockGenerate;
    private int maxBlockPerTurn = 2;
    private int[][] gameboard;//[x][y]
    private CellsArray cellsArray;
    private int score;
    private int[] newCellsLocation;
    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
        cellsArray = new CellsArray();
    }

    //start call once in a game
    @Override
    public void start() {
        score=0;
        gameboard= new int[4][4];
        addNewCell();
        //generateGameOver();
        view.Display(change(),score,newCellsLocation);
    }
    //update call every swipe
    @Override
    public void update(Direction direction) {
        changeMatrix(direction);
        addNewCell();
        if(totalSpareBlock()==0){
            if(checkGameOver()) {
                view.gameOver(score);
            }
        }
        view.Display(change(),score,newCellsLocation);
    }

    @Override
    public void loadSaved(List<Cell> cells) {
        gameboard = new int[4][4];
        for(Cell cell : cells){
            gameboard[cell.getX()][cell.getY()]=cell.getValue();
            Log.d("loadSaved","cell:"+cell.getKey()+" X:"+cell.getX()+" Y:"+cell.getY()+" Value:"+cell.getValue());
        }
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                    score+=gameboard[i][j];
            }
        }
        view.Display(change(),score,null);
    }

    @Override
    public void savedCurrentGame(Context context) {
        List<Cell> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(gameboard[i][j]!=0) {
                    Cell c = cellsArray.search(gameboard[i][j]);
                    c.setX(i);
                    c.setY(j);
                    list.add(c);
                    Log.d("ReturnSaved"," X:"+c.getX()+" Y:"+c.getY()+" Value:"+c.getValue());
                }
            }
                Database2048 db = Room.databaseBuilder(context,
                    Database2048.class, "saveGame2048").build();
            CellDAO cellDAO = db.cellDAO();
            for(Cell cell:list)
                cellDAO.insertAll(cell);
        }
    }

    private Cell[][] change(){
        Cell[][] array = new Cell[4][4];
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                array[i][j]= cellsArray.search(gameboard[i][j]);
                if(array[i][j]==null)
                    gameboard[i][j]=0;
            }
        }
        return array;
    }
    private int totalSpareBlock(){
        int index = 0;
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                if (gameboard[i][j] == 0) {
                    index++;
                }
            }
        }
        return index;
    }
    private void addNewCell(){
        Random r = new Random();
        int totalSpareBlock = totalSpareBlock();
        totalBlockGenerate = r.nextInt(maxBlockPerTurn)+1;
        if(totalBlockGenerate>totalSpareBlock)
            totalBlockGenerate=totalSpareBlock;
        newCellsLocation = new int[totalBlockGenerate];
        for (int i = 1; i <= totalBlockGenerate; i++) {
            int c;
            if (r.nextBoolean())
                c=1;
            else
                c=2;
            score+=c;
            int rand = r.nextInt(totalSpareBlock);
            int pos = position(rand);
            newCellsLocation[i-1]=pos;
            //Log.d("block"," At:"+pos);
            gameboard[pos/10][pos%10]=c;
        }

    }
    private void swipeLeft(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(gameboard[i][j]!=0){
                    for (int k = j + 1; k < 4; k++) {
                        if(gameboard[i][k]!=0) {
                            if (gameboard[i][j] == gameboard[i][k]) {
                                gameboard[i][j] = gameboard[i][k] + 1;
                                gameboard[i][k] = 0;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameboard[i][j] == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if(gameboard[i][k]!=0) {
                            gameboard[i][j] = gameboard[i][k];
                            gameboard[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeRight(){
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if(gameboard[i][j]!=0){
                    for (int k = j - 1; k >=0; k--) {
                        if(gameboard[i][k]!=0){
                            if (gameboard[i][j] == gameboard[i][k]) {
                                gameboard[i][j] = gameboard[i][j] + 1;
                                gameboard[i][k] = 0;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (gameboard[i][j] == 0) {
                    for (int k = j - 1; k >=0; k--) {
                        if(gameboard[i][k]!=0){
                            gameboard[i][j] = gameboard[i][k];
                            gameboard[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeTop(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(gameboard[j][i]!=0){
                    for (int k = j + 1; k < 4; k++) {
                        if(gameboard[k][i]!=0){
                            if (gameboard[j][i] == gameboard[k][i]) {
                                gameboard[j][i] = gameboard[j][i] + 1;
                                gameboard[k][i] = 0;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameboard[j][i] == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if(gameboard[k][i]!=0){
                            gameboard[j][i] = gameboard[k][i];
                            gameboard[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeBottom(){
        for (int i = 3; i >=0 ; i--) {
            for (int j = 3; j >=0; j--) {
                if(gameboard[j][i]!=0){
                    for (int k = j - 1; k >=0; k--) {
                        if(gameboard[k][i]!=0){
                            if (gameboard[j][i] == gameboard[k][i]) {
                                gameboard[j][i] = gameboard[j][i] + 1;
                                gameboard[k][i] = 0;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (gameboard[j][i] == 0) {
                    for (int k = j - 1; k >=0; k--) {
                        if(gameboard[k][i]!=0){
                            gameboard[j][i] = gameboard[k][i];
                            gameboard[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void changeMatrix(Direction direction) {           //plus matrix in each direction holy shit
        switch (direction) {
            case Left:
                swipeLeft();
                break;
            case Right:
                swipeRight();
                break;
            case Top:
                swipeTop();
                break;
            case Bottom:
                swipeBottom();
                break;
        }
    }
    private boolean checkGameOver(){
        for (int i = 0;i<3;i++){
            for (int j =0;j<3;j++){
                if(gameboard[i][j]==gameboard[i][j+1]) {
                    return false;
                }
                else if(gameboard[i][j]==gameboard[i+1][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private List<Integer> spareBlocksPosition() {
        List<Integer> spare = new ArrayList<>();
        for (int i = 0; i < gameboard.length; i++){
            for (int j = 0; j < gameboard[i].length; j++) {
                if (gameboard[i][j] == 0) {
                    spare.add(i*10+j);
                }
            }
        }
        return spare;
    }

    private int position(int rand){
        List<Integer> spare = spareBlocksPosition();
        return spare.get(rand);
    }
    private void generateGameOver(){
        for (int i = 0; i < 4; i++){
            for (int j = 3; j >=0; j--) {
                gameboard[i][j]=i+j+1>10?1:i+j+1;
            }
        }
        score+=3;
    }





}