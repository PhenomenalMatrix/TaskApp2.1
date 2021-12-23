package com.mrflaitx.taskapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mrflaitx.taskapp.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
