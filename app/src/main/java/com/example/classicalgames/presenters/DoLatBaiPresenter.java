package com.example.classicalgames.presenters;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoLatBaiContract;
import com.example.classicalgames.models.latbai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DoLatBaiPresenter implements DoLatBaiContract.presenter {
    private List<latbai> bai = new ArrayList<>();
    DoLatBaiContract.view v ;

    public DoLatBaiPresenter(DoLatBaiContract.view v) {
        this.v = v;
    }

    latbai l1 = new latbai(1,R.drawable.hinhcute1);
    latbai l2 = new latbai(2,R.drawable.hinhcute2);
    latbai l3 = new latbai(3,R.drawable.hinhcute3);
    latbai l4 = new latbai(4,R.drawable.hinhcute4);
    latbai l5 = new latbai(5,R.drawable.hinhcute5);
    latbai l6 = new latbai(6,R.drawable.hinhcute6);
    latbai l7 = new latbai(7,R.drawable.hinhcute7);
    latbai l8 = new latbai(8,R.drawable.hinhcute8);

    public void taoGame(){
        bai.add(l1);
        bai.add(l1);
        bai.add(l2);
        bai.add(l2);
        bai.add(l3);
        bai.add(l3);
        bai.add(l4);
        bai.add(l4);
        bai.add(l5);
        bai.add(l5);
        bai.add(l6);
        bai.add(l6);
        bai.add(l7);
        bai.add(l7);
        bai.add(l8);
        bai.add(l8);

    }

    public void chaoBai(){
        Collections.shuffle(bai);
    }
    public latbai  latBai(int i){
        return (bai.get(i));
    }
}

