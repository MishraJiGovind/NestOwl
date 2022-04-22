package com.nestowl.brokerapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.Fragment.AmentiesFeatures;
import com.nestowl.Fragment.BasicDetails;
import com.nestowl.Fragment.PhotosSchedule;
import com.nestowl.Fragment.PricingOther;
import com.nestowl.Fragment.PropertyFeatures;
import com.nestowl.Fragment.VerifySubmit;
import com.google.android.material.tabs.TabLayout;

public class PlanBasicActivityEdit extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    ViewPager viewPager_filter;
    public TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location;
    CardView cardView;
    public  TextView tv_arshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_basic);




        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);



      /*  tv_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlanBasicActivity.this,SearchViewActivity.class);
                startActivity(intent);
            }
        });*/

        tab_filters.addTab(tab_filters.newTab().setText("Basic Details"));
        tab_filters.addTab(tab_filters.newTab().setText("Property Features"));
        tab_filters.addTab(tab_filters.newTab().setText("Pricing Other"));
        tab_filters.addTab(tab_filters.newTab().setText("Amenties Features"));

        tab_filters.addTab(tab_filters.newTab().setText("Photos Schedule"));
        tab_filters.addTab(tab_filters.newTab().setText("Verify Submit"));


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        tab_filters.setOnTabSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetails()).commit();



    }




    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetails()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new PropertyFeatures()).commit();

        }
        else if (tab.getPosition()==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PricingOther()).commit();
        }
        else if (tab.getPosition()==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new AmentiesFeatures()).commit();
        }
        else if (tab.getPosition()==4){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PhotosSchedule()).commit();
        } else if (tab.getPosition()==5){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new VerifySubmit()).commit();
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
