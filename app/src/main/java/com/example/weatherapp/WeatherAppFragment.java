package com.example.weatherapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.bean.BeanWeatherInformation;
import com.example.weatherapp.database.DataBaseManager;
import com.google.gson.Gson;

public class WeatherAppFragment extends BaseFragment { // implements View.OnclickListener when there is clicking event


    TextView textViewTemperature;
    TextView textViewCity;
    TextView textViewCondition;
    LinearLayout linearLayoutCenter;

    //API
    String urlBeforeCity ="https://api.weatherbit.io/v2.0/current?city=";
    String urlAfterCity ="&key=3367b992fb334937835991f178c882a2";

    String city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_app, container, false);
        initializeView(view); // Create a method for initialize Views because there are a lot
        // acquire specific city's weather from fragment
        // 1. Bundle is used to pass data between activities. The values that are to be passed are
        // mapped to String keys which are later used in the next activity to retrieve the values.
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        String myUrl = urlBeforeCity + city + urlAfterCity;
        // Loading data by calling the parent class BaseFragment
        loadDate(myUrl);
        return view;
    }

    @Override
    public void onSuccess(String result) {
        // Parse and show the data
        parseShowData(result);
        // Update data
        int i = DataBaseManager.updateInfoByCity(city, result);
        if (i <= 0){
            // Means update date fails, which indicates there is no this city info in datadase
            // so add city using DataBaseManager.addCityInfo
            DataBaseManager.addCityInfo(city, result);
        }

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        // If query data fails, we can search last query in database which succeed
        String myResult = DataBaseManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(myResult)){ //TextUtils a set of utility functions to do operations on String objects
            parseShowData(myResult);
        }

    }

    private void parseShowData(String result) {
        //Use Gson parse the data
        BeanWeatherInformation beanWeatherInformation = new Gson().fromJson(result, BeanWeatherInformation.class);

        BeanWeatherInformation.DataDTO dataDTO = beanWeatherInformation.getData().get(0);

        textViewCity.setText(dataDTO.getCityName());
        textViewCondition.setText(dataDTO.getWeather().getDescription());
        int myTemp = (int) Math.round(dataDTO.getTemp());
        textViewTemperature.setText(String.format("%d%s", myTemp, "°C"));

        //Center Layout
        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.main_center_future_weather, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutCenter.addView(itemView);
        TextView textViewTimezone = itemView.findViewById(R.id.main_center_text_timezone);
        TextView textViewAir = itemView.findViewById(R.id.main_center_text_air);
        TextView textViewWind = itemView.findViewById(R.id.main_center_text_wind);
        TextView textViewVisibility = itemView.findViewById(R.id.main_center_text_visibility);
        TextView textViewHumidity = itemView.findViewById(R.id.main_center_text_humidity);
        //Center Layout Set Text
        //TODO: format center better
        textViewTimezone.setText(String.format("%s%s", "Timezone: ", dataDTO.getTimezone()));

        //String[] arr = dataDTO.getOb_time().split(" ");
        //textViewAir.setText(String.format("%s%s", "Date: ", arr[0]));

        int airQualityIndex = dataDTO.getAqi();

        if (airQualityIndex >= 0 && airQualityIndex <= 50){
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Good"));
        } else if (airQualityIndex >= 51 && airQualityIndex <= 100) {
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Moderate"));
        } else if (airQualityIndex >= 101 && airQualityIndex <= 150) {
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Unhealthy for Sensitive Groups"));
        } else if (airQualityIndex >= 151 && airQualityIndex <= 200) {
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Unhealthy"));
        } else if (airQualityIndex >= 201 && airQualityIndex <= 300) {
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Very Unhealthy"));
        } else {
            textViewAir.setText(String.format("%s%s", "Air Quality: ", "Hazardous"));
        }



        textViewWind.setText(String.format("%s%s", "Wind Direction: ", dataDTO.getWind_cdir_full()));
        textViewVisibility.setText(String.format("%s%d%s", "Visibility: ",dataDTO.getVis()," Km"));
        int myHumidity = (int) Math.round(dataDTO.getRh().doubleValue());
        textViewHumidity.setText(String.format("%s%d%s", "Humidity: ", myHumidity, " %"));


    }

    private void initializeView(View view) {
        // Initialize Views
        textViewTemperature = view.findViewById(R.id.fragment_text_temperature);
        textViewCity = view.findViewById(R.id.fragment_text_city);
        textViewCondition = view.findViewById(R.id.fragment_text_condition);
        linearLayoutCenter = view.findViewById(R.id.fragment_linear_layout_center);

    }

}