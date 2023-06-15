package com.example.classicalgames.presenters;

import android.content.Intent;

import com.example.classicalgames.activities.Activity2048;
import com.example.classicalgames.contracts.Do2048Contract;

public class Do2048Presenter implements Do2048Contract.Presenter {
    Do2048Contract.View view;

    public Do2048Presenter(Do2048Contract.View view) {
        this.view = view;
    }

    @Override
    public void update() {

    }
}
