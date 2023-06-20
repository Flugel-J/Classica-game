package com.example.classicalgames.presenters;


import android.util.Log;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.models.Cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;
    private int totalBlockGenerate;
    private int maxBlockPerTurn = 2;
    private Cell array[][]= new Cell[4][4];//[x][y]
    private Cells cells;

    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
        cells = new Cells();
    }

    //start call once in a game
    @Override
    public void start() {
        addNewCell();
        view.Display(array);
    }
    //update call every swipe
    @Override
    public void update(Direction direction) {
        change_matrix();
        addNewCell();

        view.Display(array);
    }

    private int totalSpareBlock(){
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null) {
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
            Cell c;
            if (r.nextBoolean()) {
                c=cells.search(1);
            }
            else {
                c=cells.search(2);
            }
            int rand = r.nextInt(totalSpareBlock);
            int pos = position(rand);
            //Log.d("block"," At:"+pos);
            c.setX(pos/10);
            c.setY(pos%10);
            array[c.getX()][c.getY()]=c;
        }

    }
    private void change_matrix(){           //plus matrix in each direction holy shit
        boolean check = false;
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                if(array[i][j]!=null){
                    for(int k=j+1; k<4;k++){
                        if(array[i][k]==array[i][j]){
                            check = true;
                            array[i][j]= cells.search(array[i][j].getValue()+1);
                            array[i][k]=null;
                            j=k;
                            break;
                        }else if(array[i][k]!=null)
                            break;
                    }
                }
            }
            for(int j=0;j<4;j++) {
                if(array[i][j] == null) {
                    for(int k=j+1; k<4;k++) {
                        if(array[i][k]!=null) {
                            check = true;
                            array[i][j]=array[i][k];
                            array[i][k]=null;
                            break;
                        }
                    }
                }
            }
        }        //left
    }

    private List<Integer> spareBlocksPosition() {
        List<Integer> spare = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null) {
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