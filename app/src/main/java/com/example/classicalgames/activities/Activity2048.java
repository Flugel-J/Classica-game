package com.example.classicalgames.activities;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.presenters.Do2048Presenter;

public class Activity2048 extends AppCompatActivity implements Do2048Contract.View,gameOver_2048.NoticeDialogListener{
    Do2048Contract.Presenter presenter;
    LinearLayout gameBoard;
    TextView playerScore;
    TextView highScore;
    MediaPlayer mediaPlayer;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        playerScore =(TextView) findViewById(R.id.txt_score);
        highScore = (TextView) findViewById(R.id.txt_highScore);
        presenter = new Do2048Presenter(this);
        sharedPref = this.getSharedPreferences("2048HighScore",Context.MODE_PRIVATE);
        int highscore =0;
        highscore+= sharedPref.getInt("HighScore",0);
        highScore.setText(highscore+"");
        mediaPlayer = MediaPlayer.create(this, R.raw.funky_town);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        gameBoard = findViewById(R.id.gameboard);
        presenter.start();
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
    public void Display(Cell array[][],int score) {
        playerScore.setText(score+"");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                if(array[i][j].getValue()!=0) {
                    findCoordinator(i, j)
                            .setImageResource(array[i][j].getSource());
                }
                else{
                    findCoordinator(i, j)
                            .setImageResource(0);
                }
            }
        }
    }

    @Override
    public void gameOver(int score) {
        sharedPref = this.getSharedPreferences("2048HighScore",Context.MODE_PRIVATE);
        if(sharedPref.getInt("HighScore",0)<score) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("HighScore", score);
            editor.apply();
        }
        gameOver_2048 game = new gameOver_2048();
        Bundle bundle = new Bundle();
        bundle.putString("score","Score: "+ score);
        game.setArguments(bundle);
        game.setCancelable(false);
        game.show(getSupportFragmentManager(),"gameOver");
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
    //Bak to Main menu
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent i = new Intent(Activity2048.this, MainActivity.class);
        startActivity(i);
    }
    //Try Again
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer=null;
    }
}