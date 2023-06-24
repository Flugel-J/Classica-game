package com.example.classicalgames.contracts;

import android.content.Context;

import com.example.classicalgames.models.Cell;

import java.util.List;

public interface Do2048Contract {
    interface View{
       void Display(Cell list[][], int score);
       void gameOver(int score);
    }
    interface Presenter{
        void start();
        void update(Direction direction);
    }
}
