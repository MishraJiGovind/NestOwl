package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.FiltersFragemetnLimit;
import com.nestowl.Fragment.FiltersFragmentSecond;
import com.nestowl.Fragment.FiltersOtherThird;
import com.google.android.material.tabs.TabLayout;

public class FiltersScreen extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    ViewPager viewPager_filter;
    TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location;
    CardView card_properties;
    NestedScrollView nested_properties;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_screen);
        imageView=findViewById(R.id.iv41);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);
        nested_properties=findViewById(R.id.nested_scroll_view_properties);
        card_properties=findViewById(R.id.card_view_one_k_properties);
        tv_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FiltersScreen.this,SearchViewActivity.class);
                startActivity(intent);
            }
        });

        tab_filters.addTab(tab_filters.newTab().setText("Residential"));
        tab_filters.addTab(tab_filters.newTab().setText("Commercial"));
        tab_filters.addTab(tab_filters.newTab().setText("Other"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        tab_filters.setOnTabSelectedListener(this);
getSupportFragmentManager().beginTransaction().add(R.id.frame_filter_id,new FiltersFragemetnLimit()).commit();

    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new FiltersFragemetnLimit(), "Residential");
        adapter.addFragment(new FiltersFragemetnLimit(), "Commercial");
        adapter.addFragment(new FiltersFragemetnLimit(), "Other");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragemetnLimit()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragmentSecond()).commit();

        } else if (tab.getPosition()==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new FiltersOtherThird()).commit();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void reset(View view) {


    }
}
