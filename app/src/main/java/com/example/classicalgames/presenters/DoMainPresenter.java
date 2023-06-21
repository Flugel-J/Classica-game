package com.example.classicalgames.presenters;

import com.example.classicalgames.contracts.DoMainContract;
import com.example.classicalgames.models.MainModel;

public class DoMainPresenter implements DoMainContract.Presenter {
    private DoMainContract.View view;
    //private DoMainPresenter model;
    private MainModel model;


    public DoMainPresenter(DoMainContract.View view) {
        this.view = view;
        this.model = new MainModel();
    }

    @Override
    public void processName(String name) {
        String greeting = model.generateGreeting(name);
        view.displayGreeting(greeting);
    }


}
