package com.example.classicalgames.contracts;

public interface Do2048Contract {
    interface View{
        void onSucess(String message);
        void onError(String message);
    }
    interface Presenter{
        void update();
    }
}
