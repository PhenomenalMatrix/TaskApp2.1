package com.mrflaitx.taskapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
//    private static Prefs instance;
//
//    public static Prefs getInstance() {
//        return instance;
//    }

    public Prefs(Context context){
//        instance = this;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBordState(){
        preferences.edit().putBoolean("isShown",true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("isShown",false);
    }

}
