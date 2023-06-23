package com.example.classicalgames.presenters;


import android.util.Log;

import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.models.CellsArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;
    private int totalBlockGenerate;
    private int maxBlockPerTurn = 2;
    private int[][] gameboard = new int[4][4];//[x][y]
    private CellsArray cellsArray;

    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
        cellsArray = new CellsArray();
    }

    //start call once in a game
    @Override
    public void start() {
        //addNewCell();
        generateGameOver();
        view.Display(change());
    }
    //update call every swipe
    @Override
    public void update(Direction direction) {
        change_matrix(direction);
//        Log.d("spare",totalSpareBlock()+"");
        view.Display(change());
        if(!addNewCell()){
            if(checkGameOver()) {
                view.gameOver();
                Log.d("gameover","gameOver");
            }
        }
    }
    private Cell[][] change(){
        Cell[][] array = new Cell[4][4];
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                array[i][j]= cellsArray.search(gameboard[i][j]);
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
    private boolean addNewCell(){
        Random r = new Random();
        int totalSpareBlock = totalSpareBlock();
        boolean canAdd = false;
        totalBlockGenerate = r.nextInt(maxBlockPerTurn)+1;
        if(totalBlockGenerate>totalSpareBlock)
            totalBlockGenerate=totalSpareBlock;
        if(totalBlockGenerate>=0){
            canAdd = true;
            Log.d("gameover","cant create block");
        }

        for (int i = 0; i < totalBlockGenerate; i++) {
            int c;
            if (r.nextBoolean()) {
                c=1;
            }
            else {
                c=2;
            }
            int rand = r.nextInt(totalSpareBlock);
            int pos = position(rand);
            //Log.d("block"," At:"+pos);
            gameboard[pos/10][pos%10]=c;
        }
        return canAdd;
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

    private void change_matrix(Direction direction) {           //plus matrix in each direction holy shit
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
                    Log.d("block",(i*10+j)+"");
                    return false;
                }
                else if(gameboard[i][j]==gameboard[i+1][j]) {
                    Log.d("block",i*10+j+"");
                    return false;
                }
            }
        }
        Log.d("block","none");
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
    }
}