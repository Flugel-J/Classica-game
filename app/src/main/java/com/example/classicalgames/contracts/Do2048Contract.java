package com.example.classicalgames.contracts;

import android.content.Context;

import com.example.classicalgames.models.Cell;

import java.util.List;

public interface Do2048Contract {
    interface View{
       void Display(List<Cell> list);
    }
    interface Presenter{
        void start();
        void update();
    }
}
