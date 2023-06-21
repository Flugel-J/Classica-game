package com.example.classicalgames.presenters;


import android.util.Log;

import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.models.Cells;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;
    private int totalBlockGenerate;
    private int maxBlockPerTurn = 2;
    private int[][] gameboard = new int[4][4];//[x][y]
    private Cells cells;

    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
        cells = new Cells();
    }

    //start call once in a game
    @Override
    public void start() {
        addNewCell();
        view.Display(change());
    }
    //update call every swipe
    @Override
    public void update(Direction direction) {
        change_matrix(direction);
//        Log.d("spare",totalSpareBlock()+"");
        addNewCell();
        view.Display(change());
    }
    private Cell[][] change(){
        Cell[][] array = new Cell[4][4];
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                array[i][j]=cells.search(gameboard[i][j]);
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
        //Log.d("block","total:"+totalSpareBlock+" Create:"+totalBlockGenerate);
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

    }

    private void change_matrix(Direction direction) {           //plus matrix in each direction holy shit
        boolean check = false;

        if (direction == Direction.Left) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int a = gameboard[i][j];
                    if(a!=0){
                        for (int k = j + 1; k < 4; k++) {
                            int b = gameboard[i][k];
                            if(b!=0) {
                                if (a == b) {
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
                    int a = gameboard[i][j];
                    if (a == 0) {
                        for (int k = j + 1; k < 4; k++) {
                            int b = gameboard[i][k];
                            if(b!=0) {
                                gameboard[i][j] = gameboard[i][k];
                                gameboard[i][k] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (direction == Direction.Right) {
            for (int i = 3; i >= 0; i--) {
                for (int j = 3; j >= 0; j--) {
                    int a = gameboard[i][j];
                    if(a!=0){
                        for (int k = j - 1; k >=0; k--) {
                            int b = gameboard[i][k];
                            if(b!=0){
                                if (a == b) {
                                    gameboard[i][j] = gameboard[i][k] + 1;
                                    gameboard[i][k] = 0;
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 3; i >= 0; i--) {
                for (int j = 3; j >= 0; j--) {
                    int a = gameboard[i][j];
                    if (a == 0) {
                        for (int k = j - 1; k >=0; k--) {
                            int b = gameboard[i][k];
                            if(b!=0){
                                gameboard[i][j] = gameboard[i][k];
                                gameboard[i][k] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (direction == Direction.Top) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int a = gameboard[j][i];
                    if(a!=0){
                        for (int k = j + 1; k < 4; k++) {
                            int b = gameboard[k][i];
                            if(b!=0){
                                if (a == b) {
                                    gameboard[j][i] = gameboard[j][i] + 1;
                                    gameboard[k][i] = 0;
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int a = gameboard[j][i];
                    if (a == 0) {
                        for (int k = j + 1; k < 4; k++) {
                            int b = gameboard[k][i];
                            if(b!=0){
                                gameboard[j][i] = gameboard[k][i];
                                gameboard[k][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }

        }
        else {
            for (int i = 3; i >=0 ; i--) {
                for (int j = 3; j >=0; j--) {
                    int a = gameboard[j][i];
                    if(a!=0){
                        for (int k = j - 1; k >=0; k--) {
                            int b = gameboard[k][i];
                            if(b!=0){
                                if (a == b) {
                                    gameboard[j][i] = gameboard[j][i] + 1;
                                    gameboard[k][i] = 0;
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 3; i >= 0; i--) {
                for (int j = 3; j >= 0; j--) {
                    int a = gameboard[j][i];
                    if (a == 0) {
                        for (int k = j - 1; k >=0; k--) {
                            int b = gameboard[k][i];
                            if(b!=0){
                                gameboard[j][i] = gameboard[k][i];
                                gameboard[k][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }

        }
    }
    private List<Integer> spareBlocksPosition() {
        List<Integer> spare = new ArrayList<>();
        for (int i = 0; i < gameboard.length; i++){
            for (int j = 0; j < gameboard[i].length; j++) {
                if (  gameboard[i][j] == 0) {
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

}