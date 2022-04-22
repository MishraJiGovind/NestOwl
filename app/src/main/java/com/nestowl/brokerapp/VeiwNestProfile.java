package com.nestowl.brokerapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.DashboardFragment;
import com.nestowl.Fragment.ReviewsFragment;
import com.nestowl.Fragment.SubsCription;
import com.google.android.material.tabs.TabLayout;

public class VeiwNestProfile extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    FrameLayout frame_see_if_you_are_eligible,back_img;
    TextView tv,tv1,tv_recent_enquiries,saved_listing,recent_search,tv_reviews,second_eligibility;

    LinearLayout lnd_dashboard,lnd_reviews,first_review,second_review,third_review,review_prsnl;
    TextView dashboard,reviews;
    Boolean is_Residential_click = false;
    FrameLayout fm1,fm2;
    TabLayout tab_filters;


    LinearLayout first,second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahil_dahiya_circle_view_nest);

        tab_filters = findViewById(R.id.tab_filter_screen);

        tab_filters.addTab(tab_filters.newTab().setText("Dashboard"));
        tab_filters.addTab(tab_filters.newTab().setText("Reviews"));
        tab_filters.addTab(tab_filters.newTab().setText("Subscription"));
        tab_filters.setOnTabSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_filter_id,new DashboardFragment()).commit();

        back_img=findViewById(R.id.ARTICLES_BACK);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}


    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment(), "Dashboard");
        adapter.addFragment(new ReviewsFragment(), "Reviews");
        adapter.addFragment(new SubsCription(), "Subscription");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new DashboardFragment()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new ReviewsFragment()).commit();
        }


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
