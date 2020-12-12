package com.example.weatherapp.base;

import android.app.Application;

import com.example.weatherapp.database.DataBaseManager;

import org.xutils.x;

/**
 * UniteApp is for declaring the unite
 */
public class UniteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // Initialize DataBase, when our program opens, our data base get initialized as well
        DataBaseManager.initializeDataBase(this);
    }
}
