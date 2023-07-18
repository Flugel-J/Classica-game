package com.example.classicalgames.contracts;

import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.models.MinesweeperBoard;

import java.util.List;

public interface DoMinesweeperContract {
    interface Presenter{
        void NewGameByDifficultLevel(String difficult_level);
        List<MineSquare> RandomGenerateMineByDifficultLevel(MinesweeperBoard minesweeperBoard);
        List<MineSquare> generateNumberAroundMine(List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        int countMineAroundSquare(List<MineSquare> mineSquareList, int indexCurrentSquare, MinesweeperBoard minesweeperBoard);
        int addCountIfMineSquareIsMine(int mineCount, MineSquare mineSquare);
        MineSquare getMineSquareRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareBelowRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareBelowLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareBelow(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareUpRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareUpLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        MineSquare getMineSquareUp(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard);
        List<MineSquare> GenerateMineSquareList(int column, int row, List<MineSquare> mineSquareList);
        List<MineSquare> RandomMineIntoGameBoardByDifficultLevel(MinesweeperBoard minesweeperBoard, List<MineSquare> mineSquareList);
        boolean IsWin(MinesweeperBoard minesweeperBoard);
        boolean IsMine(MineSquare mineSquare);
        boolean IsFlag(MineSquare mineSquare);
        void setFlag(MineSquare mineSquare, boolean isInsertFlag);
        void setMarkedUnknown(MineSquare mineSquare, boolean isMarkUnknown);
    }
}
