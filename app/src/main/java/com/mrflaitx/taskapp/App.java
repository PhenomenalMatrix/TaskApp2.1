package com.mrflaitx.taskapp;

import android.app.Application;

import androidx.room.Room;

import com.mrflaitx.taskapp.data.local.AppDatabase;
import com.mrflaitx.taskapp.utils.Prefs;

public class App extends Application {

    public static Prefs prefs;
    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "dataBase")
                // Разршение на запросы в главном потоке
                .allowMainThreadQueries()
                // Игнор. миграции
                .fallbackToDestructiveMigration()
                .build();
        prefs = new Prefs(this);
    }
}
