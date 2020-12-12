package com.example.weatherapp.city_management;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseActivity;
import com.example.weatherapp.bean.BeanWeatherInformation;
import com.google.gson.Gson;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener{
    ImageView imageViewSearchBtn, imageViewBackBtn;
    EditText editTextSearchBox;
    GridView gridViewSearchSuggestion;
    private ArrayAdapter<String> adapter;
    String [] suggestionCity = {"New York", "Chicago", "Houston", "Washington DC",
    "Paris", "Beijing", "Tokyo", "Seoul", "LondonUK"};

    String city;
    String urlBeforeCity ="https://api.weatherbit.io/v2.0/current?city=";
    String urlAfterCity ="&key=3367b992fb334937835991f178c882a2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        imageViewSearchBtn = findViewById(R.id.search_image_view_search_btn);
        imageViewBackBtn = findViewById(R.id.search_image_view_back_btn);
        editTextSearchBox = findViewById(R.id.search_edit_view_search_box);
        gridViewSearchSuggestion = findViewById(R.id.search_grid_view_suggestion);

        // Add OnClickListener event
        imageViewSearchBtn.setOnClickListener(this);
        imageViewBackBtn.setOnClickListener(this);

        // Create adaptor
        adapter = new ArrayAdapter<String>(this, R.layout.gridview_suggestion_search_city, suggestionCity);
        gridViewSearchSuggestion.setAdapter(adapter);
        setListener();
    }

    // Grid view on item click listener
    private void setListener() {
        gridViewSearchSuggestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = suggestionCity[position];
                String MyUrl = urlBeforeCity + city + urlAfterCity;
                loadData(MyUrl);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_image_view_search_btn:
                /* when search button is clicked, first check if the search box is empty.
                 * if search box is empty, show a message to user
                 * if search box is not empty, check if the city can be found? */
                if(editTextSearchBox.getText().toString().matches("[a-zA-Z]+([\\s][a-zA-Z]+)*")) {
                    city = editTextSearchBox.getText().toString();
                }
                if (!TextUtils.isEmpty(city)) {
                    String MyUrl = urlBeforeCity + city + urlAfterCity;
                    loadData(MyUrl);
                } else {
                    Toast.makeText(this, "Enter a valid city name!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search_image_view_back_btn:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        new Gson().fromJson(result, BeanWeatherInformation.class);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("city", city);
        startActivity(intent);
    }
}