package com.example.classicalgames.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.classicalgames.R;
import com.example.classicalgames.contracts.CellDAO;
import com.example.classicalgames.contracts.Database2048;
import com.example.classicalgames.contracts.Direction;
import com.example.classicalgames.contracts.Do2048Contract;
import com.example.classicalgames.contracts.Menu;
import com.example.classicalgames.models.Cell;
import com.example.classicalgames.presenters.Do2048Presenter;
import java.util.List;

public class Activity2048 extends AppCompatActivity implements Do2048Contract.View,gameMenu_2048.NoticeDialogListener{
    Do2048Contract.Presenter presenter;
    LinearLayout gameBoard;
    TextView playerScore;
    TextView highScore;
    MediaPlayer mediaPlayer;
    SharedPreferences sharedPref;
    ImageView pauseButton;
    CellDAO cellDAO;
    float volume =1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        playerScore =(TextView) findViewById(R.id.txt_score);
        highScore = (TextView) findViewById(R.id.txt_highScore);
        pauseButton = (ImageView) findViewById(R.id.img_pause);
        sharedPref = this.getSharedPreferences("2048HighScore",Context.MODE_PRIVATE);
        int highscore;
        highscore= sharedPref.getInt("HighScore",0);
        highScore.setText(highscore+"");
        gameMenu_2048 menu_2048 = gameMenu_2048.newInstance(Menu.StartMenu);
        menu_2048.setCancelable(false);
        menu_2048.show(getSupportFragmentManager(),"2048Menu");
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMenu_2048 menu = gameMenu_2048.newInstance(Menu.StopMenu);
                menu.show(getSupportFragmentManager(),"pauseMenu");
            }
        });
        presenter = new Do2048Presenter(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.funky_town);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        gameBoard = findViewById(R.id.gameboard);
        Database2048 db = Room.databaseBuilder(this,
                Database2048.class, "saveGame2048").build();
        cellDAO = db.cellDAO();
        gameBoard.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeTop() {
                presenter.update(Direction.Top);
            }

            @Override
            public void onSwipeBottom() {
                presenter.update(Direction.Bottom);
            }

            @Override
            public void onSwipeLeft() {
                presenter.update(Direction.Left);
            }

            @Override
            public void onSwipeRight() {
                presenter.update(Direction.Right);
            }
        });
    }




    @Override
    public void Display(Cell array[][],int score, int[] newCellLocation) {
        playerScore.setText(score+"");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                ImageView imageView = findCoordinator(i, j);
                if (array[i][j].getValue() != 0) {
                    imageView.setImageResource(array[i][j].getSource());
                    }
                else {
                        imageView.setImageResource(0);
                }
            }
        }
        if(newCellLocation!=null){
            for (int i = 0; i<newCellLocation.length;i++){
                findCoordinator(newCellLocation[i]/10,newCellLocation[i]%10)
                        .setAnimation(popUpAnimation());
            }
        }

    }

    @Override
    public void gameOver(int score){
        sharedPref = this.getSharedPreferences("2048HighScore",Context.MODE_PRIVATE);
        if(sharedPref.getInt("HighScore",0)<score) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("HighScore", score);
            editor.apply();
        }
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, R.raw.game_over_event_sound);
        mediaPlayer.start();
        gameMenu_2048 menu_2048 = gameMenu_2048.newInstance(Menu.GameOverMenu,"Score "+score);
        menu_2048.setCancelable(false);
        menu_2048.show(getSupportFragmentManager(),"2048Menu");
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

    @Override
    public void loadSavedGame(DialogFragment dialog) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Cell> list = cellDAO.loadData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadSaved(list);
                    }
                });

            }
        });
        t.start();
        dialog.dismiss();

    }

    @Override
    public void saveAndExit(DialogFragment dialog) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                cellDAO.nukeTable();
                presenter.savedCurrentGame(Activity2048.this);
                Intent i = new Intent(Activity2048.this, MainActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        t.start();


    }

    @Override
    public void newGame(DialogFragment dialog) {
        dialog.dismiss();
        presenter.start();
    }

    @Override
    public void changeVolume(DialogFragment dialog, int v) {
        volume = (float) v/100;
        mediaPlayer.setVolume(volume,volume);
    }

    @Override
    public void settingMenu(DialogFragment dialog,Menu menu) {
        dialog.dismiss();
        gameMenu_2048 menu_2048 = gameMenu_2048.newInstance(Menu.SettingMenu);
        menu_2048.setCancelable(false);
        Bundle args = new Bundle();
        args.putSerializable("menu",Menu.SettingMenu);
        args.putFloat("volume",volume);
        args.putSerializable("destinationMenu",menu);
        menu_2048.setArguments(args);
        menu_2048.show(getSupportFragmentManager(),"Setting");


    }

    private Animation popUpAnimation() {
        // Create the animation
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        // Set the duration of the animation
        animation.setDuration(500);

        // Return the animation
        return animation;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer=null;
    }
}