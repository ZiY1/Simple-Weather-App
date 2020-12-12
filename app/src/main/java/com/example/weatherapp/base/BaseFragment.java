package com.example.weatherapp.base;

import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * 1. BaseFragment is for acquiring and loading weather information from myAPI
 * 2. Use xutils to load the date
 * 1). Declare the xutils unite in Class UniteApp
 * 2). Acquire the date from internet
 * 3. Callback means we pass a reference to a function which will get called when an event occurs.
 */
public class BaseFragment extends Fragment implements Callback.CommonCallback<String> {

    public void loadDate (String path){
        RequestParams requestParams = new RequestParams(path);
        x.http().get(requestParams, this); // this refers to BaseFragment
    }

    // We get called when acquire data succeed
    @Override
    public void onSuccess(String result) {

    }

    // We get called when acquire data failed
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    // We get called when we cancel acquire data
    @Override
    public void onCancelled(CancelledException cex) {

    }

    // We get called when acquire data finished
    @Override
    public void onFinished() {

    }
}
