package com.example.classicalgames.contracts;

public interface DoMainContract {
    interface View {
        void displayGreeting(String greeting);
    }

    interface Presenter {
        void processName(String name);
    }

}
