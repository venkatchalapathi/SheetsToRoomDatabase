package com.example.sheetstoroomdatabase;

import android.app.Application;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@android.arch.persistence.room.Database(entities = {Sheet1.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final String DB_NAME = "demo_database";

    public abstract MyDao getDao();

    private static volatile Database INSTANCE;

    private static Database getINSTANCE(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
