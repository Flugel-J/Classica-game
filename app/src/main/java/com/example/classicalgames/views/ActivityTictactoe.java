package com.example.classicalgames.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gametictoctoe.R;
import com.example.gametictoctoe.contracts.DoTicTacToeContract;
import com.example.gametictoctoe.presenters.DoTicTacToePresenter;

public class ActivityTictactoe extends AppCompatActivity implements View.OnClickListener, DoTicTacToeContract.view {
    DoTicTacToeContract.presenter presenter;
    private TextView playerOneScore, playerTwoScore, playerStatus ;//khai bao bien
    private Button []  buttons = new Button[9];//tao mang do dai 9
    private Button resetGame;

    //bien dem so diem cua nguoi choi


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame = (Button) findViewById(R.id.resetGame);

        for (int i=0;i<buttons.length; i++){
            String buttonID = "btn_"+i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] =   (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        presenter = new DoTicTacToePresenter(this);

    }

    @Override
    public void onClick(View v) {
//        Log.i("test","button is clicked!");
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());//btn_2
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));//2

        if (presenter.isActivityPlayer()){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            presenter.setGameState(1,gameStatePointer);
        }else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            presenter.setGameState(0,gameStatePointer);
        }
        presenter.setRountCount(presenter.getRountCount()+1);

        //man ktra nguoi chien thang
        if (presenter.checkWinner()){
            if (presenter.isActivityPlayer()){
                presenter.updateGame(1);
                Toast.makeText(this,"Player One Won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }else {
                presenter.updateGame(2);
                Toast.makeText(this,"Player Two Won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }

        }else if (presenter.getRountCount() == 9){
            playAgain();
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();


        }else {
            presenter.setActivityPlayer(!presenter.isActivityPlayer());
        }
        if (presenter.getPlayerOneScoreCount() > presenter.getPlayerTwoScoreCount()){
            playerStatus.setText("Player One is Winning!");
        }else if (presenter.getPlayerTwoScoreCount() > presenter.getPlayerOneScoreCount()){
            playerStatus.setText("Player Two is Winning!");
        }else {
            playerStatus.setText("");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                presenter.setPlayerOneScoreCount(0);
                presenter.setPlayerTwoScoreCount(0);
                playerStatus.setText("");
                updatePlayerScore(0,0);
            }
        });
    }
    @Override
    public void updatePlayerScore(int p1,int p2){
        playerOneScore.setText(Integer.toString(p1));
        playerTwoScore.setText(Integer.toString(p2));

    }
    public void playAgain(){
        presenter.setRountCount(0);
        presenter.setActivityPlayer(true);

        for (int i = 0;i < buttons.length; i++){
            presenter.setGameState(2,i);
            buttons[i].setText("");
        }
    }
}