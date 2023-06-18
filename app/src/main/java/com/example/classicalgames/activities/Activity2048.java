package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.presenters.Do2048Presenter;

public class Activity2048 extends AppCompatActivity implements Do2048Contract.View {
    Do2048Contract.Presenter presenter;
    LinearLayout gameBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        presenter = new Do2048Presenter(this);
        presenter.start();
//        int id = getResources().getIdentifier("funkyTown.mp3","raw",getPackageName());
//        MediaPlayer mediaPlayer = MediaPlayer.create(this,id);
        gameBoard = findViewById(R.id.gameboard);
        gameBoard.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeTop() {

                presenter.update(Direction.Top);
            }

            @Override
            public void onSwipeBottom() {
                presenter.update(Direction.Bottom);
            }

            @Override
            public void onSwipeLeft() {

                presenter.update(Direction.Left);
            }

            @Override
            public void onSwipeRight() {

                presenter.update(Direction.Right);
            }
        });
    }




    @Override
    public void Display(Cell array[][]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j]!=null) {
                    findCoordinator(array[i][j].getX(), array[i][j].getY())
                            .setImageResource(array[i][j].getSource());
                }
                }
            }
    }

    ImageView findCoordinator(int x,int y){
        int coordinator= (10*x)+y;
        String imageViewId;
        if(x!=0){
             imageViewId ="brick"+coordinator;
        }
        else{
            imageViewId ="brick0"+coordinator;
        }

        Resources resources = getResources();
        int resourceId = resources.getIdentifier(imageViewId, "id", getPackageName());
        return findViewById(resourceId);
    }
}