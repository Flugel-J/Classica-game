package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classicalgames.R;
import com.example.classicalgames.activities.Activity2048;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClicked2048(View view){
        Intent intent = new Intent(this, Activity2048.class);
        startActivity(intent);
    }
}