package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.presenters.DoMinesweeperPresenter;

public class ActivityMinesweeper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        minesweeper_presenter.RandomGenerateMineByDifficultLevel("beginner9x9Area10Mines");
        minesweeper_presenter.NewGameByDifficultLevel("beginner9x9Area");
    }

}