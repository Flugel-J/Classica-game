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
        int numberOfCluster;
        MinesweeperBoard mineBoard = new MinesweeperBoard();
        List<MineSquare> mineSquareList = new ArrayList<>();

        switch (difficult_level) {
            case "beginner9x9Area10Mines":
                numberOfCluster = 3;

                mineBoard.setNumberOfColumn(9);
                mineBoard.setNumberOfRow(9);
                mineBoard.setTotalMine(10);
                mineBoard.setNumberOfMine(10);

                mineSquareList = GenerateMineSquareList(9, 9, mineSquareList);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard, mineSquareList);
                break;
            case "medium16x16Area40Mines":
                numberOfCluster = 4;

                mineBoard.setNumberOfColumn(16);
                mineBoard.setNumberOfRow(16);
                mineBoard.setTotalMine(40);
                mineBoard.setNumberOfMine(40);

                mineSquareList = GenerateMineSquareList(16, 16, mineSquareList);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard, mineSquareList);

                break;
            case "hard16x30Area90Mines":
                numberOfCluster = 5;

                mineBoard.setNumberOfColumn(16);
                mineBoard.setNumberOfRow(30);
                mineBoard.setTotalMine(99);
                mineBoard.setNumberOfMine(99);

                mineSquareList = GenerateMineSquareList(16, 30, mineSquareList);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard, mineSquareList);
                break;
        }

        return mineSquareList;
    }

    private List<MineSquare> GenerateMineSquareList(int column, int row, List<MineSquare> mineSquareList) {
        for (int i = 0; i < column * row; i++) {
            mineSquareList.add(new MineSquare());
        }
        return mineSquareList;
    }

    @Override
    public List<MineSquare> RandomGenerateMineByMinesweeperBoard(MinesweeperBoard minesweeperBoard) {
        List<MineSquare> mineSquareList = new ArrayList<>();

        return mineSquareList;
    }

    private List<MineSquare> RandomMineIntoClusterByDifficultLevel(int numberOfCluster, MinesweeperBoard minesweeperBoard, List<MineSquare> mineSquareList) {
        int horizonSizeOfCluster = minesweeperBoard.getNumberOfColumn() / numberOfCluster;
        int numberOfMineInCluster = minesweeperBoard.getTotalMine() / numberOfCluster;
        int numberOfRemainedMine = minesweeperBoard.getTotalMine() - numberOfMineInCluster * numberOfCluster;

        //random position of mine into column coordinator of each row in each cluster until out of mine
        //loop through each cluster of Minesweeper board
        for (int clusterIndex = 0; clusterIndex < numberOfCluster; clusterIndex++) {
            int loopTime = 1;
            //case divided number of total mine divide for cluster equal or lesser than number of column in minesweeper board
            if (minesweeperBoard.getTotalMine() / numberOfCluster <= minesweeperBoard.getNumberOfColumn()) {
                mineSquareList = putRandomOneMineIntoEachColumn(clusterIndex,
                        horizonSizeOfCluster,
                        loopTime,
                        minesweeperBoard,
                        mineSquareList,
                        numberOfMineInCluster);
            } else { //case divided number of total mine divide for cluster more than number of column in minesweeper board
                mineSquareList = putRandomMultipleMineIntoEachColumn(clusterIndex,
                        horizonSizeOfCluster,
                        loopTime,
                        minesweeperBoard,
                        mineSquareList,
                        numberOfMineInCluster);
            }
        }

        return mineSquareList;
    }

    private List<MineSquare> putRandomMultipleMineIntoEachColumn(int clusterIndex, int horizonSizeOfCluster, int loopTime, MinesweeperBoard minesweeperBoard, List<MineSquare> mineSquareList, int numberOfMineInCluster) {
        return mineSquareList;
    }

    private List<MineSquare> putRandomOneMineIntoEachColumn(int clusterIndex,
                                                            int horizonSizeOfCluster,
                                                            int loopTime,
                                                            MinesweeperBoard minesweeperBoard,
                                                            List<MineSquare> mineSquareList,
                                                            int numberOfMineInCluster) {
        List<MineSquare> mineSquaresListOfMine = new ArrayList<>();
        //loop through each column in cluster
        for (int columnIndex = clusterIndex * horizonSizeOfCluster; loopTime <= horizonSizeOfCluster; loopTime += 1) {
            Random random = new Random();
            MineSquare mineSquare = new MineSquare();

            mineSquare.setX(columnIndex);
            columnIndex += 1;
            mineSquare.setY(random.nextInt(minesweeperBoard.getNumberOfRow()));
//            example for get index algorithm in list base on x and y
//            x=0, y=1 i=1
//            x=1, y=1 i=7
//            x=2, y=2 i=14
//            row = 3, column = 6
//            i == (x)*column+y
            mineSquareList.get(mineSquare.getX() * minesweeperBoard.getNumberOfColumn() + mineSquare.getY()).setMine(true);

            numberOfMineInCluster -= 1;
            if (numberOfMineInCluster == 0) {
                break;
            }
        }

        return mineSquareList;
    }

    private List<MineSquare> addMineIntoList(List<MineSquare> mineSquareList, List<MineSquare> mineSquaresListOfMine, MinesweeperBoard minesweeperBoard) {
        int totalSquare = minesweeperBoard.getNumberOfColumn() * minesweeperBoard.getNumberOfRow();
        int squareX = 0;
        int squareY = 0;

        for (int i = 0; i < totalSquare; i++) {
            MineSquare mineSquare = new MineSquare();
            mineSquare.setX(squareX);
            mineSquare.setY(squareY);
            mineSquare.setMine(false);

            //check if this mine square has mine or not
            for (MineSquare mineSquareHasMine :
                    mineSquaresListOfMine) {
                if (mineSquare.getX() == mineSquareHasMine.getX() && mineSquare.getY() == mineSquareHasMine.getY()) {
                    mineSquare.setMine(true);
                }
            }
            mineSquareList.add(mineSquare);
            squareX += 1;
            squareY += 1;
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
