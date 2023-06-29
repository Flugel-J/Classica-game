package com.example.classicalgames.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.classicalgames.R;
import com.example.classicalgames.contracts.Menu;

import java.util.Objects;


public class gameMenu_2048 extends DialogFragment {
    private Button btnMenu1;
    private Button btnMenu2;
    private Button btnMenu3;
    private Button btnMenu4;
    private Menu menu;

    NoticeDialogListener listener;
    public static gameMenu_2048 newInstance(Menu menu) {
        Bundle args = new Bundle();
        args.putSerializable("menu", menu);
        gameMenu_2048 f = new gameMenu_2048();
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
        menu =(Menu)getArguments().get("menu");
        switch (menu) {
            case StartMenu:
                btnMenu1.setText("Continue");
                btnMenu2.setText("New game");
                btnMenu3.setText("High Score");
                btnMenu4.setVisibility(View.GONE);
                btnMenu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.loadSavedGame(gameMenu_2048.this);
                    }
                });
                btnMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.newGame(gameMenu_2048.this);
                    }
                });
                btnMenu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
                        listener.saveAndExit(gameMenu_2048.this);
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

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }

}