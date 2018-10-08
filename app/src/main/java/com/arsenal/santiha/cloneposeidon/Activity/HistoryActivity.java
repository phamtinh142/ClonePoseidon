package com.arsenal.santiha.cloneposeidon.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arsenal.santiha.cloneposeidon.Adapter.HistoryPageAdapter;
import com.arsenal.santiha.cloneposeidon.Fragment.BillFragment;
import com.arsenal.santiha.cloneposeidon.Fragment.BookFragment;
import com.arsenal.santiha.cloneposeidon.R;

public class HistoryActivity extends AppCompatActivity {
    HistoryPageAdapter historyPageAdapter;
    private ViewPager viewPagerHistory;
    private TabLayout tabHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyPageAdapter = new HistoryPageAdapter(getSupportFragmentManager());
        viewPagerHistory = findViewById(R.id.viewpageHistory);
        setViewPager(viewPagerHistory);

        tabHistory = findViewById(R.id.tabs);
        tabHistory.setupWithViewPager(viewPagerHistory);

    }

    private void setViewPager(ViewPager viewPager){
        HistoryPageAdapter adapter = new HistoryPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new BookFragment(), "Đặt bàn");
        adapter.addFragment(new BillFragment(), "Hóa đơn");
        viewPager.setAdapter(adapter);

    }

}
