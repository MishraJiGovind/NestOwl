package com.nestowl.brokerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.Fragment.BasicDetailsRqrmnt;
import com.nestowl.Fragment.PropertyFeaturesRqrmnt;
import com.nestowl.model.aichat;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.android.material.tabs.TabLayout;

public class PostRequirementForm extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener{

    ViewPager viewPager_filter;
    TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location,clearall;
    CardView cardView;
    ImageView finish;
    LiveCommnication liveCommnication;
    String userId, pID;
    Intent i;
    View basic,feature,pricing,amenties,photo,verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_basic);
        i =  getIntent();
        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);
        cardView=findViewById(R.id.POST_PHOTO_CONTINEUE);
        clearall=findViewById(R.id.clearAll);
        finish=findViewById(R.id.DEALS_BACK);
        userId=null;
        pID=null;

        basic =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(0);
        feature =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(1);
        pricing =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(2);
        amenties =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(3);
        photo =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(4);
        verify =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(5);

        basic.setClickable(false);
        feature.setClickable(false);
        pricing.setVisibility(View.GONE);
        amenties.setVisibility(View.GONE);
        photo.setVisibility(View.GONE);
        verify.setVisibility(View.GONE);


        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat charSequence) {
                aichat d = new aichat();
                d=charSequence;
                if (d.getText().equals("cont")){
                    tab_filters.removeAllTabs();
                    tab_filters.addTab(tab_filters.newTab().setText("Basic Details"));
                    tab_filters.addTab(tab_filters.newTab().setText("Property Features"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new PropertyFeaturesRqrmnt()).commit();
                    tab_filters.selectTab(tab_filters.getTabAt(1));
                }
                if (d.getText().equals("set")){
                    pID=d.getValue();
                    userId=d.getValues();
                }
                if (d.getText().equals("get")){
                    aichat sa = new aichat();
                    sa.setText("ok");
                    sa.setValue(pID);
                    sa.setValues(userId);
                    liveCommnication.setText(sa);
                }
                if (d.getText().equals("getType")){
                    aichat sa = new aichat();
                    sa.setText("getOk");
                    sa.setValue(i.getStringExtra("type"));
                    sa.setValues(null);
                    liveCommnication.setText(sa);
                }

            }
        });
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab_filters.removeAllTabs();
                tab_filters.addTab(tab_filters.newTab().setText("Basic Details"));
                tab_filters.addTab(tab_filters.newTab().setText("Property Features"));
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetailsRqrmnt()).commit();
                tab_filters.setOnTabSelectedListener(PostRequirementForm.this);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetailsRqrmnt()).commit();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        tab_filters.setOnTabSelectedListener(this);
    }
/*
    private void setupViewPager(ViewPager viewPager) {
*//*
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new FiltersFragemetnLimit(), "Basic Details");
        adapter.addFragment(new FiltersFragemetnLimit(), "Commercial");
        adapter.addFragment(new FiltersFragemetnLimit(), "Other");*//*
        viewPager.setAdapter(adapter);
    }*/

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
