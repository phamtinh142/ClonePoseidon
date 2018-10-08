package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arsenal.santiha.cloneposeidon.Adapter.AdapterViewPagerThucDon;
import com.arsenal.santiha.cloneposeidon.R;

public class ThucDonActivity extends AppCompatActivity {
    ViewPager viewPager;
    AdapterViewPagerThucDon adapterViewPagerThucDon;
    RadioButton rdo_do_an, rdo_do_uong;
    RadioGroup radioGroup;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don);

        AnhXa();
        ChangeTabViewPager();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdo_do_an.isChecked()){
                    viewPager.setCurrentItem(0);
                } else if (rdo_do_uong.isChecked()){
                    viewPager.setCurrentItem(1);
                }
            }
        });

    }

    private void ChangeTabViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rdo_do_an.setChecked(true);
                        break;
                    case 1:
                        rdo_do_uong.setChecked(true);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void AnhXa() {
        viewPager = findViewById(R.id.viewpager_thuc_don);
        adapterViewPagerThucDon = new AdapterViewPagerThucDon(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerThucDon);
        rdo_do_an = findViewById(R.id.radiobuttondoan);
        rdo_do_uong = findViewById(R.id.radiobuttondouong);
        toolbar = findViewById(R.id.toolbar_thuc_don);
        radioGroup = findViewById(R.id.radiogroupthucdon);
    }
}
