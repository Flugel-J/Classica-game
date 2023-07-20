package com.example.classicalgames.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Menu;


public class gameMenu2048 extends DialogFragment {
    private ImageView header;
    private TextView txt_header;
    private Button btnMenu1;
    private Button btnMenu2;
    private Button btnMenu3;
    private Button btnMenu4;
    private TextView score;
    private SeekBar volume;
    private Menu menu;

    private float currentVol;
    NoticeDialogListener listener;

    public static gameMenu2048 newInstance(Menu menu, String... score) {
        Bundle args = new Bundle();
        args.putSerializable("menu", menu);
        if(score.length>0)
            args.putString("score",score[0]);
        gameMenu2048 f = new gameMenu2048();
        f.setArguments(args);
        return f;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_game_menu_2048, null);
        btnMenu1 = view.findViewById(R.id.btn_1);
        btnMenu2 = view.findViewById(R.id.btn_2);
        btnMenu3 = view.findViewById(R.id.btn_3);
        btnMenu4 = view.findViewById(R.id.btn_4);
        score = view.findViewById(R.id.txt_score);
        volume = view.findViewById(R.id.sb_volume);

        menu =(Menu)getArguments().get("menu");
        switch (menu) {
            case StartMenu:
                btnMenu1.setText("Continue");
                btnMenu2.setText("New game");
                btnMenu3.setText("Setting");
                btnMenu4.setVisibility(View.GONE);
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.loadSavedGame(gameMenu2048.this);
                    }
                });
                btnMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.newGame(gameMenu2048.this);
                    }
                });
                btnMenu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.settingMenu(gameMenu2048.this,menu);
                   }
                });

                break;
            case StopMenu:
                btnMenu1.setText("Exit");
                btnMenu2.setText("Save and Exit");
                btnMenu3.setText("Resume");
                btnMenu4.setText("Setting");
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
                btnMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.saveAndExit(gameMenu2048.this);
                    }
                });
                btnMenu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                btnMenu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.settingMenu(gameMenu2048.this,menu);
                    }
                });
                break;
            case GameOverMenu:
                btnMenu1.setText("Try Again");
                btnMenu2.setText("Exit");
                btnMenu3.setVisibility(View.GONE);
                btnMenu4.setVisibility(View.GONE);
                score.setText(getArguments().getString("score"));
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.newGame(gameMenu2048.this);
                    }
                });
                btnMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
                break;
            case SettingMenu:
                score.setText("Music");
                volume.setVisibility(View.VISIBLE);
                currentVol = getArguments().getFloat("volume")*100;

                volume.setProgress((int)currentVol);
                volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        listener.changeVolume(gameMenu2048.this,i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                btnMenu1.setText("Return");
                btnMenu2.setVisibility(View.GONE);
                btnMenu3.setVisibility(View.GONE);
                btnMenu4.setVisibility(View.GONE);
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(getArguments().getSerializable("destinationMenu")==Menu.StartMenu) {
                            gameMenu2048 menu_2048 = gameMenu2048.newInstance(Menu.StartMenu);
                            menu_2048.setCancelable(false);
                            menu_2048.show(getParentFragmentManager(), "2048Menu");
                        }
                        dismiss();
                    }
                });
                break;
            case HowToPlayMenu:
                btnMenu1.setText("Return");
                btnMenu2.setVisibility(View.GONE);
                btnMenu3.setVisibility(View.GONE);
                btnMenu4.setVisibility(View.GONE);
                header = view.findViewById(R.id.menuHeader);
                txt_header = view.findViewById(R.id.txt_menuHeader);
                header.setImageResource(0);
                txt_header.setText("How to play");
                score.setText(R.string.howToPlay);

                score.setTextSize(20);
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                break;
            case LatBaiPauseMenu:
                btnMenu1.setText("Exit");
                btnMenu2.setText("Play Again");
                btnMenu3.setText("Resume");
                btnMenu4.setVisibility(View.GONE);
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
                btnMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.latBaiPlayAgain(gameMenu2048.this);
                    }
                });
                btnMenu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

                break;

        }
        builder.setView(view);
        return builder.create();
    }
    public interface NoticeDialogListener {
        void loadSavedGame(DialogFragment dialog);
        void saveAndExit(DialogFragment dialog);
        void newGame(DialogFragment dialog);
        void changeVolume(DialogFragment dialog,int volume);
        void settingMenu(DialogFragment dialog,Menu menu);
        void latBaiPlayAgain(DialogFragment dialog);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

            // Verify that the host activity implements the callback interface
            try {
                // Instantiate the NoticeDialogListener so we can send events to the host
                listener = (NoticeDialogListener) context;

            } catch (ClassCastException e) {

            }

    }
}