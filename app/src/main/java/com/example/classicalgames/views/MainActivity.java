package com.example.classicalgames.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.classicalgames.contracts.DoMainContract;
import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Menu;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements DoMainContract.View{

    private EditText inputname;
    private Button enterName;
    private DoMainContract.Presenter presenter;
    private Button sound;

    private MediaPlayer mediaPlayer;
    private Button howToPlay;
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

// mo dau dau hoi
        ImageView view = findViewById(R.id.imageView2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(imageView, "", Snackbar.LENGTH_INDEFINITE );
                snackbar.setAction("Đóng", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                //tao doi tuong
                SpannableStringBuilder ssb = new SpannableStringBuilder();
                //chu TB
                SpannableString messageText = new SpannableString("Thông báo");
                messageText.setSpan(new StyleSpan(Typeface.BOLD), 0, messageText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.append(messageText);
                ssb.append("\n");
                //in ra thanh vien
                SpannableString additionalText = new SpannableString("Đây là sản phẩm của nhóm 5");
                additionalText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, additionalText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.append(additionalText);
                // Đặt nội dung vào Snackbar
                TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setText(ssb);



                //dat mau nen
                snackbar.getView().setBackgroundColor(Color.BLUE);
                //mau chu
                TextView textView1 = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                textView1.setTextColor(Color.BLACK);
                //
                snackbar.show();
            }
        });
// ket thuc dau hoi
        //how to play
        howToPlay = findViewById(R.id.howtoplay);
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMenu2048 menu = gameMenu2048.newInstance(Menu.HowToPlayMenu);
                menu.show(getSupportFragmentManager(),"HowToPlay");
            }
        });

    }
    //ket thuc ham onCreate
    public void onClicked2048(View view){
        Intent intent = new Intent(this, Activity2048.class);
        startActivity(intent);
    }


    public void onClickedMinesweeper(View view) {
        Intent intent = new Intent(this, ActivityMinesweeper.class);
        startActivity(intent);
        finish();
    }

    public void onClickedlatbai(View view){
        Intent intent = new Intent(this,ActivityLatbai.class);
    startActivity(intent);

    }

    public void onClickedTicTacToe(View view){
        Intent intent = new Intent(this,MainActivity1.class);
    startActivity(intent);
    }



    @Override
    public void displayGreeting(String greeting) {
        Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null)
            mediaPlayer.release();
    }

}