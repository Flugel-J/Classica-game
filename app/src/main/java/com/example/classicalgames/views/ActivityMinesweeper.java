package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.models.MinesweeperBoard;
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

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard();
        minesweeperBoard.setNumberOfColumn(9);
        minesweeperBoard.setNumberOfRow(9);
        minesweeperBoard.setTotalMine(10);
        minesweeperBoard.setNumberOfRemainMine(10);
        minesweeperBoard.setDifficult_level("beginner9x9Area10Mines");

        displayMetrics = getResources().getDisplayMetrics();
        int screen_width = displayMetrics.widthPixels;
        int screen_height = displayMetrics.heightPixels;

        int view_width = screen_width / minesweeperBoard.getNumberOfColumn();   //width for imageview

        mineboardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel(minesweeperBoard);
        mineSquaresArrayList = minesweeper_presenter.generateNumberAroundMine(mineSquaresArrayList, minesweeperBoard);

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineboardGridView.setNumColumns(minesweeperBoard.getNumberOfColumn());
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
        linearLayout_game_board.getLayoutParams().height = screen_height - 200;

        mineboardGridView.setAdapter(mineSquareAdapter);
    }

    @Override
    public void onBackPressed() {
        RelativeLayout relativeLayoutDifficultMenu = findViewById(R.id.relativelayout_difficult_menu);
        if (relativeLayoutDifficultMenu.getVisibility() == View.VISIBLE) {
            relativeLayoutDifficultMenu.setVisibility(View.INVISIBLE);
        } else {
            Intent intent = new Intent(ActivityMinesweeper.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //new game base on current game difficult
    public void onClickFaceEmotion(View view) {
        String difficult_level = "beginner9x9Area10Mines";
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();
        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();
//        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel();
    }

    //choose different of difficult easy, medium, hard, custom from easy to hard
    public void onClickDifficultButton(View view) {
        RelativeLayout relativeLayoutForDifficultMenu = findViewById(R.id.relativelayout_difficult_menu);
        relativeLayoutForDifficultMenu.setVisibility(View.VISIBLE);
    }

    //switch to place flag mode from reveal mine mode or vice versa
    public void onClickFlagButton(View view) {
        TextView tv_flag_mode = findViewById(R.id.tv_flag_mode);

        if (tv_flag_mode.getText().equals("Flag mode on")) {

        }
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