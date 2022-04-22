package com.nestowl.brokerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.EmptyFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.tabs.TabLayout;

public class InterestedPropertyThirdUserSecond extends AppCompatActivity {
    FrameLayout frame_make_offer,frame_start_offer,frame_amenties,frame_four,frm_one,frm_two,no_frame,frm_yes;
    LinearLayout lnr_other_charges,lnr_hide,lnr_show;
    TextView tv_explore,tv_view,tv_maek,tv_read,read,tv_read_more,tv_first,tv_second,tv_third,tv_fourth,tv_five,tv_six,tv_saven,tv_eight,tv_nine;
    GoogleMap mMap;
    FrameLayout f1,f2,f3,f4;
    TabLayout tab_filters;
    ViewPager viewPager_filter;
    ScrollView scroll;
    LinearLayout lnr_parent;
    View frm_third;
    ImageView back_img;
    HorizontalScrollView horizontalScrollView;


    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();
            horizontalScrollView.setVisibility(View.VISIBLE);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    horizontalScrollView.setVisibility(View.GONE);
                    break;
                case MotionEvent.ACTION_MOVE:
                    horizontalScrollView.setVisibility(View.GONE);
                    break;
                case MotionEvent.ACTION_UP:
                    horizontalScrollView.setVisibility(View.GONE);
                    break;
            }

            return true;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_property_third_user_second);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        frame_make_offer = findViewById(R.id.LEADS_PROPERTY_DETA_MAKE_OFFER);
        frame_amenties=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_POWER_BACKUP);
        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        scroll=findViewById(R.id.scroll_tab_layout);
        f1=findViewById(R.id.make_first);
        f2=findViewById(R.id.make_second);
        f3=findViewById(R.id.make_third);
        f4=findViewById(R.id.LEADS_PROPERTY_DETA_CHAT);
        horizontalScrollView=findViewById(R.id.btm_horizontally);



        frm_third=findViewById(R.id.view_id);
        frm_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterestedPropertyThirdUserSecond.this, PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });
        tv_first=findViewById(R.id.first_first);
        tv_second=findViewById(R.id.second_second);
        tv_third=findViewById(R.id.third_third);
        tv_fourth=findViewById(R.id.fourth_fourth);
        tv_five=findViewById(R.id.five_five);
        tv_saven=findViewById(R.id.saven_saven);
        tv_eight=findViewById(R.id.eight_eight);
        tv_nine=findViewById(R.id.nine_nine);
        tv_six=findViewById(R.id.six_six);

        tab_filters = findViewById(R.id.tab_filter_screen);

        setupViewPager(viewPager_filter);

        tab_filters.setupWithViewPager(viewPager_filter);
        tab_filters.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==0){
                    scroll.scrollTo(0,400);
                }else if (tab.getPosition()==1){
                    scroll.scrollTo(0,1050);
                }else if (tab.getPosition()==2){
                    scroll.scrollTo(0,1700);
                }else if (tab.getPosition()==3){
                    scroll.scrollTo(0,2200);
                }else if (tab.getPosition()==4){
                    scroll.scrollTo(0,2500);
                }else if (tab.getPosition()==5){
                    scroll.scrollTo(0,2850);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        // scroll.setOnTouchListener(handleTouch);
        scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                tab_filters.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.GONE);

                if (scrollY>=300)
                {
                    frm_third.setVisibility(View.GONE);
                }else {
                    frm_third.setVisibility(View.VISIBLE);
                }


                if (scrollY>=400)
                {
                    horizontalScrollView.setVisibility(View.VISIBLE);
                }
                if (scrollY>=2850)
                {
                    viewPager_filter.setCurrentItem(5);

                }else

                if (scrollY>=2500)
                {
                    viewPager_filter.setCurrentItem(4);

                }else

                if (scrollY>=2200)
                {
                    viewPager_filter.setCurrentItem(3);

                }
                else
                if (scrollY>=1700)
                {
                    viewPager_filter.setCurrentItem(2);

                }else
                if (scrollY>=1050)
                {
                    viewPager_filter.setCurrentItem(1);

                }else

                if (scrollY>=400)
                {
                    viewPager_filter.setCurrentItem(0);

                }else {
                    viewPager_filter.setCurrentItem(0);
                    tab_filters.setVisibility(View.GONE);
                }
            }
        });


        frame_four=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_MORE);
        frm_yes=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBIT_YES);
        lnr_show=findViewById(R.id.lnr_visible_show);
        tv_read_more=findViewById(R.id.LEADS_PROPERTY_DETA_DESCRIPTION_READMORE);

        read=findViewById(R.id.LEADS_PROPERTY_DETA_DESCLAIMER);
        no_frame=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBIT_NO);



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        lnr_hide=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBITION_VIEW);
        tv_view=findViewById(R.id.view_all_sixteen);
        tv_read =findViewById(R.id.LEADS_PROPERTY_DETA_DESCLAIMER);
        tv_maek=findViewById(R.id.make_offer_sixteen);
        frm_two=findViewById(R.id.property_two_sixteen);
        frm_one=findViewById(R.id.property_one_sixteen);
        tv_explore=findViewById(R.id.explore_more_sixteen);
        lnr_other_charges=findViewById(R.id.other_charges);
        frame_start_offer=findViewById(R.id.LEADS_PROPERTY_DETA_BUYTHIS);
    }
    public  void secondSystem(){

        Intent intent=new Intent(InterestedPropertyThirdUserSecond.this, ScreenSixteenDetailsPage.class);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment(), "Overview");
        adapter.addFragment(new EmptyFragment(), "Description");
        adapter.addFragment(new EmptyFragment(), "Property Details");
        adapter.addFragment(new EmptyFragment(), "Amenties");
        adapter.addFragment(new EmptyFragment(), "Locality");
        adapter.addFragment(new EmptyFragment(), "Properties");

        viewPager.setAdapter(adapter);
    }
}
