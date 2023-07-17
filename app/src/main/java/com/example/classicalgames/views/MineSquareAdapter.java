package com.example.classicalgames.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMinesweeperContract;
import com.example.classicalgames.models.MineSquare;
import com.example.classicalgames.presenters.DoMinesweeperPresenter;

import java.util.List;

public class MineSquareAdapter extends ArrayAdapter<MineSquare> {

    int imv_width;
    ImageView imv_mine_square;
    DoMinesweeperContract.Presenter minesweeper_presenter = new DoMinesweeperPresenter();

    public MineSquareAdapter(@NonNull Context context, List<MineSquare> mineSquareArrayList, int itemWidth) {
        super(context, 0, mineSquareArrayList);
        imv_width = itemWidth;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.minesweeper_mine_square, parent, false);
        }

        MineSquare mineSquare = getItem(position);
        imv_mine_square = listitemView.findViewById(R.id.idImvMineSquare);
        imv_mine_square.getLayoutParams().height = imv_width;
        imv_mine_square.getLayoutParams().width = imv_width;
        imv_mine_square.setImageResource(R.drawable.minesweeper_square_covered);

        return listitemView;
    }
}