package com.example.weatherapp.city_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.bean.BeanWeatherInformation;
import com.example.weatherapp.database.DataBaseBean;
import com.google.gson.Gson;

import java.util.List;

public class DeleteCityAdapter extends BaseAdapter {
    Context context;
    List<String> stringList;
    List<String> deleteCity;

    List<DataBaseBean> dataBaseBeanList;

    public DeleteCityAdapter(Context context, List<String> stringList, List<String> deleteCity, List<DataBaseBean> dataBaseBeanList) { //List<DataBaseBean> dataBaseBeanList
        this.context = context;
        this.stringList = stringList;
        this.deleteCity = deleteCity;

        this.dataBaseBeanList = dataBaseBeanList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // convertView is used to reuse old view.
        // Eg: So if a list is of 15 items, but window can show only 5 items, then at first convertView would be null,
        // and we need to create new views for these five items, but when you scroll down, you have two options,
        // either create 6-10 views, or re-use old views and load new data into these views.
        // Adapter and convertView enables you to do the later method.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cardview_city_delete, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Above is the basic format

        final String city = stringList.get(position);
        viewHolder.textViewIndex.setText(city);



        DataBaseBean dataBaseBean = dataBaseBeanList.get(position);
        BeanWeatherInformation beanWeatherInformation = new Gson().fromJson(dataBaseBean.getCityData(), BeanWeatherInformation.class);
        BeanWeatherInformation.DataDTO dataDTO = beanWeatherInformation.getData().get(0);
        viewHolder.textView.setText(dataDTO.getCity_name());



        viewHolder.imageViewDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringList.remove(city);
                dataBaseBeanList.remove(dataBaseBean);
                deleteCity.add(city); // Real deletion happens after clicking save
                notifyDataSetChanged(); // notify adapter to update after deleting
            }
        });


        return convertView;
    }

    class ViewHolder{
        ImageView imageViewDeleteBtn;
        TextView textView, textViewIndex;

        public ViewHolder(View view){
            imageViewDeleteBtn = view.findViewById(R.id.city_delete_card_view_delete_btn);
            textView = view.findViewById(R.id.city_delete_card_view_text);
            textViewIndex = view.findViewById(R.id.city_delete_card_view_index_text);
        }
    }

}
