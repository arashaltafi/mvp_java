package com.arash.altafi.mvp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class} , version = 1 , exportSchema = false)
//@Database(entities = {News.class} , version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract LocalDataSource getLocalDataSource();

    private static AppDataBase instance;

    public static AppDataBase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context , AppDataBase.class , "db_news").allowMainThreadQueries().build();
        }
        return instance;
    }

}