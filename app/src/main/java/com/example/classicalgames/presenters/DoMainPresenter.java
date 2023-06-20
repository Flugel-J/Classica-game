package com.example.classicalgames.presenters;

import com.example.classicalgames.contracts.DoMainContract;
import com.example.classicalgames.models.Main;

public class DoMainPresenter implements DoMainContract.Presenter {
    private DoMainContract.View view;
    //private DoMainPresenter model;
    private Main model;


    public DoMainPresenter(DoMainContract.View view) {
        this.view = view;
        this.model = new Main();
    }

    @Override
    public void processName(String name) {
        String greeting = model.generateGreeting(name);
        view.displayGreeting(greeting);
    }


}
