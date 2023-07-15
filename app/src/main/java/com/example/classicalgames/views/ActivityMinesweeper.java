package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        int column = 9;
        String difficult_level = "";

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screen_width = displayMetrics.widthPixels;
        int screen_height = displayMetrics.heightPixels;

        int view_width = screen_width / column;   //width for imageview

        mineboardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();
//        for (int i = 0; i < 480; i++) {
//            mineSquaresArrayList.add(new MineSquare());
//        }

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel("beginner9x9Area10Mines");

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineboardGridView.setNumColumns(column);
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
//        LinearLayout linearLayout_menu = findViewById(R.id.linearlayout_menu);
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
}