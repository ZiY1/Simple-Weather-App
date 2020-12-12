package com.example.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context){
        super(context, "weatherDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String sql = "create table info(id integer primary key autoincrement, city varchar(20) unique not null, cityData text not null)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
