package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.presenters.Do2048Presenter;

import java.util.List;

public class Activity2048 extends AppCompatActivity implements Do2048Contract.View {
    Do2048Contract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        presenter = new Do2048Presenter(this);
        presenter.start();
//        int id = getResources().getIdentifier("funkyTown.mp3","raw",getPackageName());
//        MediaPlayer mediaPlayer = MediaPlayer.create(this,id);
    }




    @Override
    public void Display(List<Cell> list) {
        for(Cell c : list){
            findCoordinator(c.getX(),c.getY()).setImageResource(c.getSource());
        }
    }
    ImageView findCoordinator(int x,int y){
        int coordinator= (10*x)+y;
        String imageViewId;
        if(x!=0){
             imageViewId ="brick"+coordinator;
        }
        else{
            imageViewId ="brick0"+coordinator;
        }

        Resources resources = getResources();
        int resourceId = resources.getIdentifier(imageViewId, "id", getPackageName());
        return findViewById(resourceId);
    }
}