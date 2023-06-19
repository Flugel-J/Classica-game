package com.example.classicalgames.contracts;

import com.example.classicalgames.models.MineSquare;

import java.util.List;

public interface DoMinesweeperContract {
    interface Presenter{
        void NewGameByDifficultLevel(String difficult_level);
        List<MineSquare> RandomGenerateMineByDifficultLevel(String difficult_level);
        boolean IsMine();
        boolean IsWin();
    }
}
