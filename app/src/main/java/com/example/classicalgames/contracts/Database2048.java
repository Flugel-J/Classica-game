package com.example.classicalgames.contracts;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.classicalgames.models.Cell;

@Database(entities = {Cell.class}, version = 1)
public abstract class Database2048 extends RoomDatabase {
    public abstract CellDAO cellDAO();
}
