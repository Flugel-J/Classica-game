package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.models.MinesweeperBoard;
import com.example.classicalgames.presenters.DoMinesweeperPresenter;

import java.util.ArrayList;
import java.util.List;

public class ActivityMinesweeper extends AppCompatActivity {

    GridView mineBoardGridView;
    DisplayMetrics displayMetrics;
    boolean isMineBoardGridViewClickable = true;
    TextView tv_difficult_level;
    TextView tv_game_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        tv_difficult_level = findViewById(R.id.tv_difficult_level);
        tv_difficult_level.setText("beginner9x9Area10Mines");

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

        mineBoardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel(minesweeperBoard);
        mineSquaresArrayList = minesweeper_presenter.generateNumberAroundMine(mineSquaresArrayList, minesweeperBoard);

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineBoardGridView.setNumColumns(minesweeperBoard.getNumberOfColumn());
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
        linearLayout_game_board.getLayoutParams().height = screen_height - 250;

        mineBoardGridView.setAdapter(mineSquareAdapter);

        setOnClickForImageViewInGridView();
    }

    private void setOnClickForImageViewInGridView() {
        mineBoardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isMineBoardGridViewClickable){
                    ImageView imageView = view.findViewById(R.id.idImvMineSquare);
                    MineSquare mineSquare = (MineSquare) mineBoardGridView.getItemAtPosition(position);
                    int numberOfMineAround = mineSquare.getNumber_of_mine_around();

                    if (!mineSquare.isReveal()) {
                        revealMineSquare(mineSquare, numberOfMineAround, imageView);
                    }
                }
            }
        });

    }
    private void revealMineSquare(MineSquare mineSquare, int numberOfMineAround, ImageView imageView) {
        if (mineSquare.isMine()) {
            imageView.setImageResource(R.drawable.minesweeper_square_mine_actived);
        } else if (numberOfMineAround != 0) {
            switch (numberOfMineAround) {
                case 1:
                    imageView.setImageResource(R.drawable.minesweeper_number_1);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.minesweeper_number_2);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.minesweeper_number_3);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.minesweeper_number_4);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.minesweeper_number_5);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.minesweeper_number_6);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.minesweeper_number_7);
                    break;
                case 8:
                    imageView.setImageResource(R.drawable.minesweeper_number_8);
                    break;
            }
        } else {
            imageView.setImageResource(R.drawable.minesweeper_square_blank);
        }
    }

    @Override
    public void onBackPressed() {
        ConstraintLayout constraintLayoutForDifficultMenu = findViewById(R.id.constrainlayout_difficult_menu);
        if (constraintLayoutForDifficultMenu.getVisibility() == View.VISIBLE) {
            constraintLayoutForDifficultMenu.setVisibility(View.INVISIBLE);
            isMineBoardGridViewClickable = true;
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
        ConstraintLayout constraintLayoutForDifficultMenu = findViewById(R.id.constrainlayout_difficult_menu);
        constraintLayoutForDifficultMenu.setVisibility(View.VISIBLE);
        isMineBoardGridViewClickable = false;
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
        tv_difficult_level = findViewById(R.id.tv_difficult_level);
        tv_difficult_level.setText("beginner9x9Area10Mines");

        closeConstraintLayoutForDifficultMenu();

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard();
        minesweeperBoard.setNumberOfColumn(9);
        minesweeperBoard.setNumberOfRow(9);
        minesweeperBoard.setTotalMine(10);
        minesweeperBoard.setNumberOfRemainMine(10);
        minesweeperBoard.setDifficult_level("beginner9x9Area10Mines");

        generateGameboard(minesweeperBoard);
    }

    private void closeConstraintLayoutForDifficultMenu() {
        ConstraintLayout constraintLayoutForDifficultMenu = findViewById(R.id.constrainlayout_difficult_menu);
        if (constraintLayoutForDifficultMenu.getVisibility() == View.VISIBLE) {
            constraintLayoutForDifficultMenu.setVisibility(View.INVISIBLE);
            isMineBoardGridViewClickable = true;
        }
    }

    private void generateGameboard(MinesweeperBoard minesweeperBoard) {
        displayMetrics = getResources().getDisplayMetrics();
        int screen_width = displayMetrics.widthPixels;
        int screen_height = displayMetrics.heightPixels;

        int view_width = screen_width / minesweeperBoard.getNumberOfColumn();   //width for imageview

        mineBoardGridView = findViewById(R.id.gridview_minesweeper);
        List<MineSquare> mineSquaresArrayList = new ArrayList<MineSquare>();

        DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

        mineSquaresArrayList = minesweeper_presenter.RandomGenerateMineByDifficultLevel(minesweeperBoard);
        mineSquaresArrayList = minesweeper_presenter.generateNumberAroundMine(mineSquaresArrayList, minesweeperBoard);

        MineSquareAdapter mineSquareAdapter = new MineSquareAdapter(this, mineSquaresArrayList, view_width);
        mineBoardGridView.setNumColumns(minesweeperBoard.getNumberOfColumn());
        LinearLayout linearLayout_game_board = findViewById(R.id.linearlayout_gameboard);
        linearLayout_game_board.getLayoutParams().height = screen_height - 250;

        mineBoardGridView.setAdapter(mineSquareAdapter);

        setOnClickForImageViewInGridView();
    }

    public void onClickMediumButton(View view) {
        tv_difficult_level = findViewById(R.id.tv_difficult_level);
        tv_difficult_level.setText("medium16x16Area40Mines");

        closeConstraintLayoutForDifficultMenu();

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard();
        minesweeperBoard.setNumberOfColumn(16);
        minesweeperBoard.setNumberOfRow(16);
        minesweeperBoard.setTotalMine(40);
        minesweeperBoard.setNumberOfRemainMine(40);
        minesweeperBoard.setDifficult_level("medium16x16Area40Mines");

        generateGameboard(minesweeperBoard);
    }

    public void onClickHardButton(View view) {
        tv_difficult_level = findViewById(R.id.tv_difficult_level);
        tv_difficult_level.setText("hard16x30Area90Mines");
        closeConstraintLayoutForDifficultMenu();

        MinesweeperBoard minesweeperBoard = new MinesweeperBoard();
        minesweeperBoard.setNumberOfColumn(16);
        minesweeperBoard.setNumberOfRow(30);
        minesweeperBoard.setTotalMine(99);
        minesweeperBoard.setNumberOfRemainMine(99);
        minesweeperBoard.setDifficult_level("hard16x30Area90Mines");

        generateGameboard(minesweeperBoard);
    }

}