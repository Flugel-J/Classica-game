package com.example.classicalgames.contracts;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.classicalgames.models.Cell;

import java.util.List;
@Dao
public interface CellDAO {
    @Query("Select * from Cell")
    List<Cell> loadData();
    @Query("DELETE FROM Cell")
    void nukeTable();
    @Insert
    void insertAll(Cell... c);
}
