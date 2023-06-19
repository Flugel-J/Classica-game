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

                mineBoard.setX(9);
                mineBoard.setY(9);
                mineBoard.setNumberOfMine(10);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard);
                break;
            case "medium16x16Area40Mines":
                numberOfCluster = 4;

                mineBoard.setX(16);
                mineBoard.setY(16);
                mineBoard.setNumberOfMine(40);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard);

                break;
            case "hard16x30Area90Mines":
                numberOfCluster = 5;

                mineBoard.setX(16);
                mineBoard.setY(30);
                mineBoard.setNumberOfMine(99);

                mineSquareList = RandomMineIntoClusterByDifficultLevel(numberOfCluster, mineBoard);
                break;
        }

        return mineSquareList;
    }

    private List<MineSquare> RandomMineIntoClusterByDifficultLevel(int numberOfCluster, MinesweeperBoard mineBoard) {
        List<MineSquare> mineSquareList = new ArrayList<>();
        int horizonSizeOfCluster = mineBoard.getX() / numberOfCluster;
        int numberOfMineInCluster = mineBoard.getNumberOfMine() / numberOfCluster;

        //random position of mine in column coordinator of each row in each cluster until out of mine
        for (int count = 0; count < numberOfCluster; count++) {
            for (int columnIndex = count * horizonSizeOfCluster; columnIndex < horizonSizeOfCluster; columnIndex++) {
                Random random = new Random();
                MineSquare mineSquare = new MineSquare();

                mineSquare.setX(columnIndex);
                mineSquare.setY(random.nextInt(mineBoard.getY()));
                mineSquare.setMine(true);

                mineSquareList.add(mineSquare);
            }
        }

        return mineSquareList;
    }

    @Override
    public boolean IsMine() {
        return false;
    }

    @Override
    public boolean IsWin() {
        return false;
    }
}
