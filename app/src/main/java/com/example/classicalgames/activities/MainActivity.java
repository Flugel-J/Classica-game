package com.example.classicalgames.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.classicalgames.contracts.DoMainContract;
import com.example.classicalgames.R;
import com.example.classicalgames.presenters.DoMainPresenter;
import com.example.classicalgames.contracts.Menu;


public class MainActivity extends AppCompatActivity implements DoMainContract.View{

    private EditText inputname;
    private Button enterName;
    private DoMainContract.Presenter presenter;
    private Button sound;

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputname = findViewById(R.id.text_inputname);
        ImageView enterName = findViewById(R.id.enter_name);
        //enterName = findViewById(R.id.enter_name);
        //am thanh
        sound = findViewById(R.id.btn_sound);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_background);

        sound.setOnClickListener(new View.OnClickListener() {
            boolean isSoundOn = false;
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);


                if (isSoundOn) {
                    // Tắt âm thanh
                    mediaPlayer.pause();
                    isSoundOn = false;
                } else {
                    // Bật âm thanh
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isSoundOn = true;
                }
            }
        });
        //ket thuc am thanh


//nhap ten
        EditText text_inputname = findViewById(R.id.text_inputname);
        ImageView imageView = findViewById(R.id.enter_name);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text_inputname.getText().toString();
                String greeting = "Hello " + name;

                // Hiển thị "Hello + tên"
                text_inputname.setText(greeting);
                text_inputname.setEnabled(false); // Vô hiệu hóa EditText

            }
        });





// ket thuc nhap ten

    }
    //ket thuc ham onCreate
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
    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer=null;
    }
}