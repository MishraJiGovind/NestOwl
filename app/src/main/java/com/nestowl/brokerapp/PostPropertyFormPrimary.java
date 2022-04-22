package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nestowl.Fragment.BasicDetailsRqrmnt;
import com.nestowl.Fragment.PropertyFeaturesRqrmnt;
import com.google.android.material.tabs.TabLayout;

public class PostPropertyFormPrimary extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    ViewPager viewPager_filter;
    TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_form_primary);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }


        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);
        cardView=findViewById(R.id.POST_PHOTO_CONTINEUE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PostPropertyFormPrimary.this,CongratulationEmpty.class);
                startActivity(intent);
            }
        });

        tab_filters.addTab(tab_filters.newTab().setText("Basic Details"));
        tab_filters.addTab(tab_filters.newTab().setText("Property Features"));

        tab_filters.setOnTabSelectedListener(this);




    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetailsRqrmnt()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new PropertyFeaturesRqrmnt()).commit();

        }


    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
