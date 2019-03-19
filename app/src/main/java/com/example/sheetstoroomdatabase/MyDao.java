package com.example.sheetstoroomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Query("Select * from Sheet1")
    LiveData<List<Sheet1>> getAllData();

    @Query("Select * from Sheet1")
    List<Sheet1> getAll();

    @Insert
    void insertData(Sheet1 sheet1);


}
