package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.ProjectPhotos;
import com.google.android.material.tabs.TabLayout;

public class ViewAllBhkProfile extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabs;
    ScrollView scroll;
    ImageView back_img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bhk_profile);

        viewPager= findViewById(R.id.view_pager);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tabs = findViewById(R.id.tabs_properties);

        scroll=findViewById(R.id.scroll__scrolling);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }



        setupViewPager(viewPager);

        tabs.setupWithViewPager(viewPager);
        scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY>=1600)
                {
                    viewPager.setCurrentItem(1);

                }else {
                    viewPager.setCurrentItem(0);
                }
            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new ProjectPhotos(), "Project(12)");
        adapter.addFragment(new ProjectPhotos(), "Contact");
        viewPager.setAdapter(adapter);
    }
}
