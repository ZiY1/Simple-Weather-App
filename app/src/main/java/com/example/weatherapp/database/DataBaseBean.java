package com.example.weatherapp.database;

public class DataBaseBean {
    private int id;
    private String city;
    private String cityData;

    public DataBaseBean() {
    }

    public DataBaseBean(int id, String city, String cityData) {
        this.id = id;
        this.city = city;
        this.cityData = cityData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityData() {
        return cityData;
    }

    public void setCityData(String cityData) {
        this.cityData = cityData;
    }
}
