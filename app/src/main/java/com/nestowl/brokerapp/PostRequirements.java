package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nestowl.Fragment.BasicDetailsRqrmnt;
import com.nestowl.Fragment.PropertyFeaturesRqrmnt;
import com.google.android.material.tabs.TabLayout;

public class PostRequirements extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener{

    ViewPager viewPager_filter;
    TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location;
    CardView cardView;
    ImageView back_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_requirements);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);
        cardView=findViewById(R.id.POST_PHOTO_CONTINEUE);

        tab_filters.addTab(tab_filters.newTab().setText("Basic Details"));
        tab_filters.addTab(tab_filters.newTab().setText("Property Features"));
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetailsRqrmnt()).commit();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
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

    public void reset(View view) {


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
