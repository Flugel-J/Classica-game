package com.example.classicalgames.models;

import com.example.classicalgames.R;

import java.util.ArrayList;

public class Cells extends ArrayList<Cell> {
    public Cells(){
        this.add(new Cell(1, R.drawable.c_2));
        this.add(new Cell(2, R.drawable.c_4));
        this.add(new Cell(3, R.drawable.c_8));
        this.add(new Cell(4, R.drawable.c_16));
        this.add(new Cell(5, R.drawable.c_32));
        this.add(new Cell(6, R.drawable.c_64));
        this.add(new Cell(7, R.drawable.c_128));
        this.add(new Cell(8, R.drawable.c_256));
        this.add(new Cell(9, R.drawable.c_1024));
        this.add(new Cell(10, R.drawable.c_2048));

    }
    public Cell search(int value){
        for (Cell c :this){
            if(c.getValue()==value)
                return c;
        }
        return null;
    }


    public void setResource(int value, int resource) {
        search(value).setSource(resource);
    }
}
