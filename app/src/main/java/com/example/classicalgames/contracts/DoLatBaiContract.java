package com.example.classicalgames.contracts;

import com.example.classicalgames.models.latbai;

public interface DoLatBaiContract {
    interface view {

    }
    interface presenter{
        void taoGame();
        void chaoBai();
        latbai latBai(int i);
    }
}
