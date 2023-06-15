package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Do2048Contract;

public class Activity2048 extends AppCompatActivity implements Do2048Contract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);

    }

    @Override
    public void onSucess(String message) {

    }

    @Override
    public void onError(String message) {

    }
}