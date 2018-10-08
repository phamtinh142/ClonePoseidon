package com.arsenal.santiha.cloneposeidon.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arsenal.santiha.cloneposeidon.Fragment.FoodFragment;
import com.arsenal.santiha.cloneposeidon.Fragment.DrinkFragment;

public class AdapterViewPagerThucDon extends FragmentStatePagerAdapter {

    FoodFragment foodFragment;
    DrinkFragment drinkFragment;

    public AdapterViewPagerThucDon(FragmentManager fm) {
        super(fm);
        foodFragment = new FoodFragment();
        drinkFragment = new DrinkFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return foodFragment;
            case 1: return drinkFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
