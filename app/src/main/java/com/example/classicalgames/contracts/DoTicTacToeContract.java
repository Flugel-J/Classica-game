package com.example.classicalgames.contracts;

public interface DoTicTacToeContract {
    interface view{
        void updatePlayerScore(int p1, int p2);
    }
    interface presenter{
        boolean checkWinner();
        void setActivityPlayer(boolean activityPlayer);
        boolean isActivityPlayer();
        void updateGame(int player);
        void setRountCount(int rountCount);
        int getRountCount();
        void setPlayerTwoScoreCount(int playerTwoScoreCount);
        int getPlayerTwoScoreCount();
        void setPlayerOneScoreCount(int playerOneScoreCount);
        int getPlayerOneScoreCount();
        void setGameState(int value, int pos);
    }
}