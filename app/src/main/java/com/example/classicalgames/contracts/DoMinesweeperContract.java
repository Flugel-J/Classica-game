package com.example.classicalgames.contracts;

import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.models.MinesweeperBoard;

import java.util.List;

public interface DoMinesweeperContract {
    interface Presenter{
        void NewGameByDifficultLevel(String difficult_level);
        List<MineSquare> RandomGenerateMineByDifficultLevel(MinesweeperBoard minesweeperBoard);
        List<MineSquare> generateNumberAroundMine(List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        boolean IsWin(MinesweeperBoard minesweeperBoard);
        boolean IsMine(MineSquare mineSquare);
        boolean IsFlag(MineSquare mineSquare);
        void setFlag(MineSquare mineSquare, boolean isInsertFlag);
        void setMarkedUnknown(MineSquare mineSquare, boolean isMarkUnknown);
    }
}
