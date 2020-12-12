package com.example.weatherapp.city_management;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.database.DataBaseBean;
import com.example.weatherapp.database.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageView imageViewCancel, imageViewSave;
    ListView listViewDelete;
    List<String> stringList;
    List<String> deleteCity; // info of cities that have been deleted

    List<DataBaseBean> dataBaseBeanList; // data for list view

    private DeleteCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_city);

        imageViewCancel = findViewById(R.id.city_delete_cancel);
        imageViewSave = findViewById(R.id.city_delete_save);
        listViewDelete = findViewById(R.id.city_delete_list_view);
        stringList = DataBaseManager.queryAllCityName();
        deleteCity = new ArrayList<>();

        dataBaseBeanList = DataBaseManager.queryAllInfo();

        // Add OnClickListener event
        imageViewCancel.setOnClickListener(this);
        imageViewSave.setOnClickListener(this);

        // Set Adapter
        adapter = new DeleteCityAdapter(this, stringList, deleteCity, dataBaseBeanList);
        listViewDelete.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_delete_cancel:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Do you want to discard the changes you made?").setMessage("Your changes will be lost if you don't save them")
                        .setPositiveButton("Don't save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish(); // don't save and return to last activity
                            }
                        });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();

                break;
            case R.id.city_delete_save:
                for (int i = 0; i < deleteCity.size(); i++) {
                    String city = deleteCity.get(i);
                    // call the method of delete city in Database manager
                    DataBaseManager.deleteInfoByCity(city);
                }
                // Return to last activity after saving the deletion
                finish();
                break;
        }
    }
}