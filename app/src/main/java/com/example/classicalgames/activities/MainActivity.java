package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.DoMainContract;
import com.example.classicalgames.presenters.DoMainPresenter;

import org.jetbrains.annotations.Contract;

public class MainActivity extends AppCompatActivity implements DoMainContract.View{

    private EditText inputname;
    private Button enterName;
    private DoMainContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputname = findViewById(R.id.text_inputname);
        enterName = findViewById(R.id.enter_name);

        presenter = new DoMainPresenter(this);

        enterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputname.getText().toString();
                presenter.processName(name);
            }
        });

    }
    public void onClicked2048(View view){
        Intent intent = new Intent(this, Activity2048.class);
        startActivity(intent);
    }

    public void onClickedMinesweeper(View view) {
        Intent intent = new Intent(this, ActivityMinesweeper.class);
        startActivity(intent);
    }


    @Override
    public void displayGreeting(String greeting) {
        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show();
    }
}