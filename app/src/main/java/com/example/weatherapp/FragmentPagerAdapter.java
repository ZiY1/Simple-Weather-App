package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;


    public FragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList,int behavior) {
        super(fm, behavior);
        this.fragmentList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    int childCount = 0; // Number of pages ViewPager has
    // When number of pages change in ViewPager, Override two methods


    @Override
    public void notifyDataSetChanged() {
        this.childCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if ((childCount > 0)) {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
