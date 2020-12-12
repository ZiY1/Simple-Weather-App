package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.city_management.CityManagerActivity;
import com.example.weatherapp.database.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageViewAdd; // holds the bottom-left add sign
    //ImageView imageViewMore; // holds the bottom-right more sign
    LinearLayout linearLayoutPoint;
    ViewPager viewPagerMain;
    // ViewPager list or data source
    List<Fragment> fragmentList;
    // City list
    List<String> cityList;
    // ViewPager pages' indicator points
    List<ImageView> imageViewList;

    private FragmentStatePagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1.FindViewById is a method that finds the view from the layout resource file that are
        // attached with current Activity.
        // 2. R is a Class in android that are having the id’s of all the view’s.
        // 3. R.id.something means a view that is defined in any layout having id name something.
        imageViewAdd = findViewById(R.id.main_image_view_add);
        //imageViewMore = findViewById(R.id.main_image_view_more);
        linearLayoutPoint = findViewById(R.id.main_linear_layout_point);
        viewPagerMain = findViewById(R.id.main_view_pager);
        // Click event, this refers to the onClick method
        imageViewAdd.setOnClickListener(this);
        //imageViewMore.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        cityList = DataBaseManager.queryAllCityName(); // query city list information in database
        imageViewList = new ArrayList<>();

        if (cityList.size() == 0){
            cityList.add("New York City");
        }

        // get info from click event from search activity
        try {
            Intent intent = getIntent();
            String city = intent.getStringExtra("city");// city could be null at first time
            if (!cityList.contains(city) && !TextUtils.isEmpty(city)) {
                cityList.add(city);
            }
        } catch (Exception ex){
            Log.i("Ziyi", "Crashed!");
        }

        // Initialize ViewPager by creating a method
        initializePager();
        adapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragmentList, 1);
        viewPagerMain.setAdapter(adapter);
        // Create ViewPager pages' indicator points
        initializePoint();
        // Latest added city
        viewPagerMain.setCurrentItem(fragmentList.size()-1);
        // Set ViewPager Listener for updating the points
        setPagerListener();

    }

    private void setPagerListener() {
        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imageViewList.size(); i++){
                    // setting every points to white
                    imageViewList.get(i).setImageResource(R.mipmap.white_dot);
                }
                // Change the point to black which indicates the selected page, or current page
                imageViewList.get(position).setImageResource(R.mipmap.green_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initializePoint() {
        for (int i = 0; i < fragmentList.size(); i++){
            ImageView imageViewPoints = new ImageView(this);
            imageViewPoints.setImageResource(R.mipmap.white_dot);
            imageViewPoints.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)imageViewPoints.getLayoutParams();
            layoutParams.setMargins(0,0,20,0);
            imageViewList.add(imageViewPoints);
            linearLayoutPoint.addView(imageViewPoints);
        }
        imageViewList.get(imageViewList.size()-1).setImageResource(R.mipmap.green_dot);
    }


    private void initializePager() {
        for (int i = 0; i < cityList.size(); i++){
            // Create Fragment object, add to ViewPager by using 'setArgument()'
            WeatherAppFragment weatherAppFragment = new WeatherAppFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", cityList.get(i)); // First param is the key("city")
            weatherAppFragment.setArguments(bundle);
            fragmentList.add(weatherAppFragment);
        }
    }


    // Since clicking add and more button needs system reaction, we need to implement and override
    // Interface View.onClickListener
    @Override
    public void onClick(View v) {
        //Intent is to perform an action.
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.main_image_view_add:
                intent.setClass(this, CityManagerActivity.class);
                break;
            //case R.id.main_image_view_more:
                //TODO: needs implementation
                //break;
        }
        startActivity(intent); // Perform redirecting to new page
    }

    // on Restart is called when ViewPager reload or number of pages changed, ViewPager update needed
    @Override
    protected void onRestart() {
        super.onRestart();
        // query the current cities in the database
        List<String> cityName = DataBaseManager.queryAllCityName();
        if ((cityName.size() == 0)) {
            cityName.add("New York City");
        }

        cityList.clear(); // clear all cities before update
        cityList.addAll(cityName);

        // Fragment need to be updated for the cities updated list
        fragmentList.clear();
        initializePager();
        adapter.notifyDataSetChanged();

        // indicator of the pages need to be updated as well
        imageViewList.clear();
        linearLayoutPoint.removeAllViews(); // remove all views in layout
        initializePoint();
        viewPagerMain.setCurrentItem(fragmentList.size()-1);

    }
}