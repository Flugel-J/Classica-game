package com.example.classicalgames.presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.example.classicalgames.R;
import com.example.classicalgames.activities.Activity2048;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;
     int score;
    private Integer array[][];//[x][y]

    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
    }

    //start call once in a game

    @Override
    public void start() {
        array = new Integer[4][4];
        List<Cell> cells = new ArrayList<>();
        Random r = new Random();
        int startBlockNumber = r.nextInt(2);
        for (int i = 0; i <= startBlockNumber; i++) {
            Cell c = new Cell();
            if (r.nextBoolean()) {
                c.setValue(1);
                c.setSource(R.drawable.c2);
            } else {
                c.setValue(2);
                c.setSource(R.drawable.c4);
            }
            int rand = r.nextInt(totalSpareBlock());
            int pos = position(rand);
            c.setX(pos/10);
            c.setY(pos%10);
            cells.add(c);
        }
        view.Display(cells);
    }

    //update call every swipe
    @Override
    public void update() {

    }

    private int totalSpareBlock(){
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null) {
                    index++;
                }
            }

        }
        return index;
    }

    private List<Integer> spareBlocksPosition() {
        List<Integer> spare = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null) {
                    spare.add(i*10+j) ;
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