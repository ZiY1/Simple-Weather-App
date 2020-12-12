package com.example.weatherapp.city_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.bean.BeanWeatherInformation;
import com.example.weatherapp.database.DataBaseBean;
import com.google.gson.Gson;

import java.util.List;

public class CityManagementAdapter extends BaseAdapter {
    // It is the context of the current state of the application.
    // It can be used to get information regarding the activity and application.
    // It can be used to get access to resources, databases......
    Context context;
    List<DataBaseBean> dataBaseBeanList;

    public CityManagementAdapter(Context context, List<DataBaseBean> dataBaseBeanList) {
        this.context = context;
        this.dataBaseBeanList = dataBaseBeanList;
    }

    @Override
    public int getCount() {
        return dataBaseBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBaseBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cardview_city_manager, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DataBaseBean dataBaseBean = dataBaseBeanList.get(position);

        BeanWeatherInformation beanWeatherInformation = new Gson().fromJson(dataBaseBean.getCityData(), BeanWeatherInformation.class);
        BeanWeatherInformation.DataDTO dataDTO = beanWeatherInformation.getData().get(0);
        viewHolder.textViewCity.setText(dataDTO.getCity_name());
        viewHolder.textViewCondition.setText(dataDTO.getWeather().getDescription());
        int myTemp = (int) Math.round(dataDTO.getTemp());
        viewHolder.textViewTemperature.setText((String.format("%d%s", myTemp, "°C")));
        return convertView;
    }

    class ViewHolder{
        TextView textViewCity, textViewCondition, textViewTemperature;
        public ViewHolder(View view){
            textViewCity = view.findViewById(R.id.city_card_view_text_view_city);
            textViewCondition = view.findViewById(R.id.city_card_view_text_view_condition);
            textViewTemperature = view.findViewById(R.id.city_card_view_text_view_temp);
        }
    }


}
