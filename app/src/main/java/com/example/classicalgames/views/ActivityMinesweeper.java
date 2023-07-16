package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.presenters.DoMinesweeperPresenter;

import java.util.ArrayList;
import java.util.List;

public class ActivityMinesweeper extends AppCompatActivity {

    GridView mineboardGridView;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        int column = 16;
        String difficult_level = "beginner9x9Area10Mines";
         difficult_level = "medium16x16Area40Mines";
//         difficult_level = "hard16x30Area90Mines";

        displayMetrics = getResources().getDisplayMetrics();
        int screen_width = displayMetrics.widthPixels;
        int screen_height = displayMetrics.heightPixels;

        int view_width = screen_width / column;   //width for imageview

        mineboardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel(difficult_level);

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineboardGridView.setNumColumns(column);
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
        linearLayout_game_board.getLayoutParams().height = screen_height - 200;

        mineboardGridView.setAdapter(mineSquareAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ActivityMinesweeper.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //new game base on current game difficult
    public void onClickFaceEmotion(View view) {
        String difficult_level = "beginner9x9Area10Mines";
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();
        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();
        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel(difficult_level);
    }
    //choose different of difficult easy, medium, hard, custom from easy to hard
    public void onClickDifficultButton(View view) {
        RelativeLayout relativeLayoutForDifficultMenu = findViewById(R.id.relativelayout_difficult_menu);
        relativeLayoutForDifficultMenu.setVisibility(View.VISIBLE);
//        int height = displayMetrics.heightPixels * 50 / 100;
//        int width = displayMetrics.widthPixels * 50 / 100;
//        relativeLayoutForDifficultMenu.getLayoutParams().height = height;
//        relativeLayoutForDifficultMenu.getLayoutParams().width = width;
    }
    //switch to place flag mode from reveal mine mode or vice versa
    public void onClickFlagButton(View view) {
    }

    public void onClickCustomButton(View view) {
    }

    public void onClickEasyButton(View view) {
    }

    public void onClickMediumButton(View view) {
    }

    public void onClickHardButton(View view) {
    }
}