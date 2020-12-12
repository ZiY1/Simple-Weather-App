package com.example.weatherapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    public static SQLiteDatabase sqLiteDatabase;
    // Initialize Database
    public static void initializeDataBase(Context context){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }

    // Search city list in data base
    public static List<String>queryAllCityName(){
        Cursor cursor = sqLiteDatabase.query("info", null, null, null, null, null, null);
        List<String>cityList = new ArrayList<>();
        while(cursor.moveToNext()){
            String city = cursor.getString(cursor.getColumnIndex("city"));
            cityList.add(city);
        }
        return cityList;
    }

    // Update the information based on city name
    public static int updateInfoByCity(String city, String cityData){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cityData", cityData);
        return sqLiteDatabase.update("info", contentValues, "city=?", new String[]{city});
    }

    // Add city info if there is no city yet
    public static long addCityInfo(String city, String cityData){
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("cityData", cityData);
        return sqLiteDatabase.insert("info", null, contentValues);
    }

    // Search database info based on city's name
    public static String queryInfoByCity(String city){
        Cursor cursor = sqLiteDatabase.query("info", null, "city=?", new String[]{city}, null, null, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            String cityData = cursor.getString(cursor.getColumnIndex("cityData"));
            return cityData;
        }
        return null;
    }

    // Add up to 5 cities, so query how many cities in database
    public static int getCityCount(){
        Cursor cursor = sqLiteDatabase.query("info", null, null, null, null, null, null);
        int count = cursor.getCount();
        return count;
    }

    // Search info in database
    public static List<DataBaseBean>queryAllInfo(){
        Cursor cursor = sqLiteDatabase.query("info", null,null,null,null,null,null);
        List<DataBaseBean>list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String cityData = cursor.getString(cursor.getColumnIndex("cityData"));
            DataBaseBean dataBaseBean = new DataBaseBean(id, city, cityData);
            list.add(dataBaseBean);
        }
        return list;
    }

    // Delete city in database based on the city name
    public static int deleteInfoByCity(String city){
        return sqLiteDatabase.delete("info", "city=?", new String[]{city});
    }

}
