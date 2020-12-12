package com.example.weatherapp.city_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.database.DataBaseBean;
import com.example.weatherapp.database.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageViewAdd, imageViewReturn, imageViewEdit;
    ListView listViewCity;
    List<DataBaseBean> dataBaseBeanList; // data for list view
    private CityManagementAdapter cityManagementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);

        imageViewAdd = findViewById(R.id.city_manager_add_city);
        imageViewReturn = findViewById(R.id.city_manager_return_btn);
        imageViewEdit = findViewById(R.id.city_manager_editing_btn);
        listViewCity = findViewById(R.id.city_manager_list_view);
        dataBaseBeanList = new ArrayList<>();

        // Add OnClickListener event
        imageViewAdd.setOnClickListener(this);
        imageViewReturn.setOnClickListener(this);
        imageViewEdit.setOnClickListener(this);

        // Set Adapter
        cityManagementAdapter = new CityManagementAdapter(this, dataBaseBeanList);
        listViewCity.setAdapter(cityManagementAdapter);
    }

    // Query database all info, and notify adapter
    @Override
    protected void onResume() {
        super.onResume();
        List<DataBaseBean> list = DataBaseManager.queryAllInfo();
        dataBaseBeanList.clear();
        dataBaseBeanList.addAll(list);
        cityManagementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_manager_add_city:
                int cityCount = DataBaseManager.getCityCount();
                if (cityCount < 5) {
                    Intent intent = new Intent(this, SearchCityActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Maximum City Storage Reached!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.city_manager_return_btn:
                finish();
                break;
            case R.id.city_manager_editing_btn:
                Intent intent2 = new Intent(this, DeleteCityActivity.class);
                startActivity(intent2);
                break;

        }
    }
}