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
    public List<MineSquare> RandomGenerateMineByDifficultLevel(MinesweeperBoard minesweeperBoard) {
        List<MineSquare> mineSquareList = new ArrayList<>();
        String difficult_level = minesweeperBoard.getDifficult_level();

        switch (difficult_level) {
            case "beginner9x9Area10Mines":
                mineSquareList = GenerateMineSquareList(9, 9, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(minesweeperBoard, mineSquareList);
                break;
            case "medium16x16Area40Mines":
                mineSquareList = GenerateMineSquareList(16, 16, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(minesweeperBoard, mineSquareList);

                break;
            case "hard16x30Area90Mines":
                mineSquareList = GenerateMineSquareList(16, 30, mineSquareList);

                mineSquareList = RandomMineIntoGameBoardByDifficultLevel(minesweeperBoard, mineSquareList);
                break;
        }

        return mineSquareList;
    }

    @Override
    public List<MineSquare> generateNumberAroundMine(List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        for (int i = 0; i < mineSquareList.size(); i++) {
            if (!mineSquareList.get(i).isMine()) {
                int numberOfMineAround = countMineAroundSquare(mineSquareList, i, minesweeperBoard);
                mineSquareList.get(i).setNumber_of_mine_around(numberOfMineAround);
            }
        }
        return mineSquareList;
    }

    private int countMineAroundSquare(List<MineSquare> mineSquareList, int indexCurrentSquare, MinesweeperBoard minesweeperBoard) {
        int mineCount = 0;
        int currentX = mineSquareList.get(indexCurrentSquare).getX();
        int currentY = mineSquareList.get(indexCurrentSquare).getY();

        MineSquare mineSquareUp = getMineSquareUp(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareUpLeft = getMineSquareUpLeft(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareUpRight = getMineSquareUpRight(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareBelow = getMineSquareBelow(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareBelowLeft = getMineSquareBelowLeft(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareBelowRight = getMineSquareBelowRight(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareLeft = getMineSquareLeft(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);
        MineSquare mineSquareRight = getMineSquareRight(indexCurrentSquare, currentX, currentY, mineSquareList, minesweeperBoard);

        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareUp);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareUpLeft);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareUpRight);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareBelow);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareBelowLeft);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareBelowRight);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareLeft);
        mineCount = addCountIfMineSquareIsMine(mineCount, mineSquareRight);

        return mineCount;
    }

    private int addCountIfMineSquareIsMine(int mineCount, MineSquare mineSquare) {
        if (mineSquare.isMine()) {
            mineCount += 1;
        }
        return mineCount;
    }

    private MineSquare getMineSquareRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentX == (minesweeperBoard.getNumberOfColumn()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare+1);
        }
    }

    private MineSquare getMineSquareLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentX == 0) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare-1);
        }
    }

    private MineSquare getMineSquareBelowRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == (minesweeperBoard.getNumberOfRow()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else if (currentX == (minesweeperBoard.getNumberOfColumn()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare+minesweeperBoard.getNumberOfColumn()+1);
        }
    }

    private MineSquare getMineSquareBelowLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == (minesweeperBoard.getNumberOfRow()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else if (currentX == 0) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare+minesweeperBoard.getNumberOfColumn()-1);
        }
    }

    private MineSquare getMineSquareBelow(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == (minesweeperBoard.getNumberOfRow()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare+minesweeperBoard.getNumberOfColumn());
        }
    }

    private MineSquare getMineSquareUpRight(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == 0) {
            return new MineSquare(-1,-1,false,false);
        } else if (currentX == (minesweeperBoard.getNumberOfColumn()-1)) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare-minesweeperBoard.getNumberOfColumn()+1);
        }
    }

    private MineSquare getMineSquareUpLeft(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == 0) {
            return new MineSquare(-1,-1,false,false);
        } else if (currentX == 0) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare-minesweeperBoard.getNumberOfColumn()-1);
        }
    }

    private MineSquare getMineSquareUp(int indexCurrentSquare, int currentX, int currentY, List<MineSquare> mineSquareList, MinesweeperBoard minesweeperBoard) {
        if (currentY == 0) {
            return new MineSquare(-1,-1,false,false);
        } else {
            return mineSquareList.get(indexCurrentSquare-minesweeperBoard.getNumberOfColumn());
        }
    }

    private List<MineSquare> GenerateMineSquareList(int column, int row, List<MineSquare> mineSquareList) {
        //x=i; y=j;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                mineSquareList.add(new MineSquare(x, y, 0, false, false));
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
        return (minesweeperBoard.getNumberOfRemainMine() == 0);
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
