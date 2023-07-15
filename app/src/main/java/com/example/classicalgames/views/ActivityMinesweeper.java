package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.presenters.DoMinesweeperPresenter;

import java.util.ArrayList;
import java.util.List;

public class ActivityMinesweeper extends AppCompatActivity {

    GridView mineboardGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        minesweeper_presenter.RandomGenerateMineByDifficultLevel("beginner9x9Area10Mines");
        minesweeper_presenter.NewGameByDifficultLevel("beginner9x9Area10Mines");

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screen_width = displayMetrics.widthPixels;    //width of the device screen
        int screen_height = displayMetrics.heightPixels;  ////height of the device screen

        int view_width = screen_width / 16;   //width for imageview

        mineboardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();
        for (int i = 0; i < 480; i++) {
            mineSquaresArrayList.add(new MineSquare());
        }

        DoMinesweeperContract.Presenter doMinesweeperPresenter = new DoMinesweeperPresenter();

//        mineSquaresArrayList = doMinesweeperPresenter.RandomGenerateMineByDifficultLevel("beginner9x9Area10Mines");

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineboardGridView.setNumColumns(16);
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
        LinearLayout linearLayout_menu = findViewById(R.id.linearlayout_menu);
        linearLayout_game_board.getLayoutParams().height = screen_height - 200;

        mineboardGridView.setAdapter(mineSquareAdapter);
    }

}