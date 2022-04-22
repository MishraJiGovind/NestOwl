package com.nestowl.brokerapp;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.Fragment.FiltersFragemetnLimit;
import com.nestowl.Fragment.FiltersFragemetnLimitSample;
import com.nestowl.Fragment.FiltersFragmentSecondSample;
import com.nestowl.Fragment.FiltersOtherThirdSample;
import com.google.android.material.tabs.TabLayout;

public class FiltersScreenSample extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    ViewPager viewPager_filter;
    TabLayout tab_filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_screen);

        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tab_filters.addTab(tab_filters.newTab().setText("Residential"));
        tab_filters.addTab(tab_filters.newTab().setText("Commercial"));
        tab_filters.addTab(tab_filters.newTab().setText("Other"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        tab_filters.setOnTabSelectedListener(this);
getSupportFragmentManager().beginTransaction().add(R.id.frame_filter_id,new FiltersFragemetnLimit()).commit();

    }
/*
    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new FiltersFragemetnLimit(), "Residential");
        adapter.addFragment(new FiltersFragemetnLimit(), "Commercial");
        adapter.addFragment(new FiltersFragemetnLimit(), "Other");
        viewPager.setAdapter(adapter);
    }*/

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragemetnLimitSample()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragmentSecondSample()).commit();

        } else if (tab.getPosition()==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new FiltersOtherThirdSample()).commit();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
