package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoLatBaiContract;
import com.example.classicalgames.models.latbai;
import com.example.classicalgames.presenters.DoLatBaiPresenter;

import java.util.ArrayList;
import java.util.List;

public class ActivityLatbai extends AppCompatActivity implements DoLatBaiContract.view {
    DoLatBaiContract.presenter l;

    int score = 0;
    List<latbai> capBai = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_latbai);
        l=new DoLatBaiPresenter(this);
        l.taoGame();
        l.chaoBai();

        ImageView im00 = findViewById(R.id.im00);
        ImageView im01 = findViewById(R.id.im01);
        ImageView im02 = findViewById(R.id.im02);
        ImageView im03 = findViewById(R.id.im03);
        ImageView im10 = findViewById(R.id.im10);
        ImageView im11 = findViewById(R.id.im11);
        ImageView im12 = findViewById(R.id.im12);
        ImageView im13 = findViewById(R.id.im13);
        ImageView im20 = findViewById(R.id.im20);
        ImageView im21 = findViewById(R.id.im21);
        ImageView im22 = findViewById(R.id.im22);
        ImageView im23 = findViewById(R.id.im23);
        ImageView im30 = findViewById(R.id.im30);
        ImageView im31 = findViewById(R.id.im31);
        ImageView im32 = findViewById(R.id.im32);
        ImageView im33 = findViewById(R.id.im33);
        im00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(0,00);
            }
        });
        im01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(1,01);
            }
        });
        im02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(2,02);
            }
        });
        im03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(3,03);
            }
        });
        im10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(4,10);

            }
        });
        im11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(5,11);
            }
        });
        im12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(6,12);
            }
        });
        im13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(7,13);
            }
        });
        im20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(8,20);
            }
        });
        im21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(9,21);
            }
        });
        im22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(10,22);
            }
        });
        im23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(11,23);
            }
        });
        im30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(12,30);
            }
        });
        im31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(13,31);
            }
        });
        im32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(14,32);
            }
        });
        im33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 latBai(15,33);
            }
        });

    }
    void latBai(int i,int img){
        latbai bai = l.latBai(i);
        bai.setLocation(img);
        findImage(img).setImageResource(bai.getSource());
        capBai.add(bai);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (capBai.size() == 2){
                    if (capBai.get(0).getId()==capBai.get(1).getId()){
                        score += 1;
                        TextView s  = findViewById(R.id.txt_score);
                        s.setText(score + "");
                        findImage(capBai.get(0).getLocation()).setOnClickListener(null);
                        findImage(capBai.get(1).getLocation()).setOnClickListener(null);
                    }
                    else {
                        findImage(capBai.get(0).getLocation()).setImageResource(0);
                        findImage(capBai.get(1).getLocation()).setImageResource(0);
                    }
                    capBai.clear();
                }
            }
        },1500);


    }
    ImageView findImage(int i) {
        String id;
        if (i < 10) {
            id = "im0" + i;
        } else {
            id = "im" + i;
        }

        Resources r = getResources();
        int ResourcesId = r.getIdentifier(id,"id",getPackageName());
        return findViewById(ResourcesId);

    }
}