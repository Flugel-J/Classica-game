package com.example.classicalgames.contracts;

import android.content.Context;

import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.models.Cell;

import java.util.List;

public interface Do2048Contract {
    interface View {
        void Display(Cell[][] array, int score,int[] newCellLocation);
        void gameOver(int score);

    }
    interface Presenter{
        void start();
        void update(Direction direction);
        void loadSaved(List<Cell> cells);
        void savedCurrentGame(Context context);
    }
}
