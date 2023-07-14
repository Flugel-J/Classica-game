package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classicalgames.R;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore, playerStatus ;//khai bao bien
    private Button []  buttons = new Button[9];//tao mang do dai 9
    private Button resetGame;

    //bien dem so diem cua nguoi choi
    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    //private int rountCount;  //dem so vong cho den khi dat den 9 va sau do dung tro choi
    //bien boolen de chuyen doi nguoi choi
    boolean activityPlayer;

    //p1 => 0
    //p2 =>1
    //empty =>2
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [] [] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},//row
            {0,3,6}, {1,4,7}, {2,5,8},//colum
            {0,4,8}, {2,4,6}//cheo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

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
        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activityPlayer = true;

    }

    @Override
    public void onClick(View v) {
//        Log.i("test","button is clicked!");
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());//btn_2
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));//2

        if (activityPlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;
        }else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        //man ktra nguoi chien thang
        if (checkWinner()){
            if (activityPlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player One Won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Two Won!", Toast.LENGTH_SHORT).show();
                playAgain();

            }

        }else if (rountCount == 9){
            playAgain();
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();


        }else {
            activityPlayer = !activityPlayer;
        }
        if (playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player One is Winning!");
        }else if (playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Player Two is Winning!");
        }else {
            playerStatus.setText("");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }
    public  boolean checkWinner(){
        boolean winnerResult = false;
        for (int [] winningPosion : winningPositions){
            if (gameState[winningPosion[0]] == gameState[winningPosion[1]] && gameState[winningPosion[1]] == gameState[winningPosion[2]] && gameState[winningPosion[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }
    public void playAgain(){
        rountCount = 0;
        activityPlayer = true;

        for (int i = 0;i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

}
