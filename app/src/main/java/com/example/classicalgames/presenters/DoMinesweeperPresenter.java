package com.example.classicalgames.presenters;

import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MinesweeperBoard;
import com.example.classicalgames.models.MineSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoMinesweeperPresenter implements DoMinesweeperContract.Presenter {

    @Override
    public void NewGameByDifficultLevel(String difficult_level) {
        if (difficult_level.equals("beginner9x9Area10Mines")) {

        } else if (difficult_level.equals("medium16x16Area40Mines")) {

        } else if (difficult_level.equals("hard16x30Area90Mines")) {

        }
    }

    /*
        Clustered Placement: Instead of randomly placing mines across the entire board,
        this algorithm will first divide the board into clusters or regions,
        and then place a predetermined number of mines in each cluster.
        This can help ensure that mines are spread out evenly across the board
        and not clustered together in one area.
    */
    @Override
    public List<MineSquare> RandomGenerateMineByDifficultLevel(String difficult_level) {
        MinesweeperBoard mineBoard = new MinesweeperBoard();
        List<MineSquare> mineSquareList = new ArrayList<>();

        switch (difficult_level) {
            case "beginner9x9Area10Mines":
                mineBoard.setNumberOfColumn(9);
                mineBoard.setNumberOfRow(9);
                mineBoard.setTotalMine(10);
                mineBoard.setNumberOfMine(10);

                mineSquareList = GenerateMineSquareList(9, 9, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(mineBoard, mineSquareList);
                break;
            case "medium16x16Area40Mines":
                mineBoard.setNumberOfColumn(16);
                mineBoard.setNumberOfRow(16);
                mineBoard.setTotalMine(40);
                mineBoard.setNumberOfMine(40);

                mineSquareList = GenerateMineSquareList(16, 16, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(mineBoard, mineSquareList);

                break;
            case "hard16x30Area90Mines":
                mineBoard.setNumberOfColumn(16);
                mineBoard.setNumberOfRow(30);
                mineBoard.setTotalMine(99);
                mineBoard.setNumberOfMine(99);

                mineSquareList = GenerateMineSquareList(16, 30, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(mineBoard, mineSquareList);
                break;
        }

        return mineSquareList;
    }

    private List<MineSquare> GenerateMineSquareList(int column, int row, List<MineSquare> mineSquareList) {
        //x=i; y=j;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                mineSquareList.add(new MineSquare(i, j, false));
            }
        }
        return mineSquareList;
    }

    private List<MineSquare> RandomMineIntoGameBoardByDifficultLevel(MinesweeperBoard minesweeperBoard, List<MineSquare> mineSquareList) {
        int mineRemain = minesweeperBoard.getTotalMine();
        Random random = new Random();
        while (mineRemain != 0) {
            for (int i = 0; i < mineSquareList.size(); i++) {
                if (random.nextInt(150) <= 3) {
                    if (!mineSquareList.get(i).isMine()) {
                        mineSquareList.get(i).setMine(true);
                        mineRemain -= 1;
                        if (mineRemain == 0) {
                            break;
                        }
                    }
                }
            }
        }
        return mineSquareList;
    }

    @Override
    public boolean IsWin(MinesweeperBoard minesweeperBoard) {
        return (minesweeperBoard.getNumberOfMine() == 0);
    }

    @Override
    public boolean IsMine(MineSquare mineSquare) {
        return mineSquare.isMine();
    }

    @Override
    public boolean IsFlag(MineSquare mineSquare) {
        return mineSquare.isFlag();
    }

    @Override
    public void setFlag(MineSquare mineSquare, boolean isInsertFlag) {
        if (isInsertFlag) {
            mineSquare.setFlag(true);
        }
    }

    @Override
    public void setMarkedUnknown(MineSquare mineSquare, boolean isMarkUnknown) {
        if (isMarkUnknown) {
            mineSquare.setMarkedUnknown(true);
        }
    }
}
