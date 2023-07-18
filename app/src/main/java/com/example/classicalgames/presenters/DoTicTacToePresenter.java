package com.example.classicalgames.presenters;

import com.example.gametictoctoe.contracts.DoTicTacToeContract;

public class DoTicTacToePresenter implements DoTicTacToeContract.presenter {
    DoTicTacToeContract.view view;
    private int playerOneScoreCount=0, playerTwoScoreCount=0, rountCount=0;
    //private int rountCount;  //dem so vong cho den khi dat den 9 va sau do dung tro choi
    //bien boolen de chuyen doi nguoi choi
    boolean activityPlayer=true;

    //p1 => 0
    //p2 =>1
    //empty =>2
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [] [] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},//row
            {0,3,6}, {1,4,7}, {2,5,8},//colum
            {0,4,8}, {2,4,6}//cheo
    };

    public DoTicTacToePresenter(DoTicTacToeContract.view view) {
        this.view = view;
    }

    @Override
    public  boolean checkWinner(){
        boolean winnerResult = false;
        for (int [] winningPosion : winningPositions){
            if (gameState[winningPosion[0]] == gameState[winningPosion[1]] && gameState[winningPosion[1]] == gameState[winningPosion[2]] && gameState[winningPosion[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }
    @Override
    public boolean isActivityPlayer() {
        return activityPlayer;
    }

    @Override
    public void updateGame(int player) {
        if(player==1)
            playerOneScoreCount++;
        else
            playerTwoScoreCount++;
        view.updatePlayerScore(playerOneScoreCount,playerTwoScoreCount);
    }

    @Override
    public void setActivityPlayer(boolean activityPlayer) {
        this.activityPlayer = activityPlayer;
    }
    @Override
    public int getPlayerOneScoreCount() {
        return playerOneScoreCount;
    }
    @Override
    public void setPlayerOneScoreCount(int playerOneScoreCount) {
        this.playerOneScoreCount = playerOneScoreCount;
    }
    @Override
    public int getPlayerTwoScoreCount() {
        return playerTwoScoreCount;
    }
    @Override
    public void setPlayerTwoScoreCount(int playerTwoScoreCount) {
        this.playerTwoScoreCount = playerTwoScoreCount;
    }
    @Override
    public int getRountCount() {
        return rountCount;
    }
    @Override
    public void setRountCount(int rountCount) {
        this.rountCount = rountCount;
    }
    @Override
    public void setGameState(int value, int pos) {
        this.gameState[pos] = value;
    }
}
