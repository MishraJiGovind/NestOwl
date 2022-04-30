package com.nestowl.brokerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.ContactUs;
import com.nestowl.Fragment.DashboardFragment;
import com.nestowl.Fragment.ReviewsFragment;
import com.nestowl.Fragment.SubsCription;
import com.nestowl.model.DpModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

public class SahilDahiyaCircle extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    FrameLayout back_img,edit;
    TabLayout tab_filters;
    ScrollView nested;
    AppBarLayout appBarLayout;
    ImageView dp,editDp,cover,back,share;
    TextView name,city;
    User user;
    DpModal dpModal;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahil_dahiya_circle);
        back_img=findViewById(R.id.ARTICLES_BACK);
        tab_filters = findViewById(R.id.tab_filter_screen);
        appBarLayout=findViewById(R.id.sahil_dahiya);
        edit=findViewById(R.id.PROFILE_EDIT);
        dp=findViewById(R.id.PROFILE_DP);
        editDp=findViewById(R.id.PROFILE_DP_EDIT);
        cover=findViewById(R.id.PROFILE_COVER);
        back=findViewById(R.id.PROFILE_BACK);
        share=findViewById(R.id.PROFILE_SHARE);
        name=findViewById(R.id.PROFILE_NAME);
        city=findViewById(R.id.PROFILE_CITY);


        editDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SahilDahiyaCircle.this,SignUpPhotos.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SahilDahiyaCircle.this,SignUpPhotos.class));
            }
        });
        if (PrefMananger.getString(this,"user")!=null&&PrefMananger.getString(this,"photo")!=null){
            user =  new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
            dpModal =  new Gson().fromJson(PrefMananger.getString(this,"photo"),DpModal.class);
            name.setText(user.getFirst_name());
            Glide.with(this).load(UrlClass.BASE_URL+dpModal.getProfile_photo()).placeholder(R.drawable.profile_img_sahil).into(dp);
            Glide.with(this).load(UrlClass.BASE_URL+dpModal.getCover()).placeholder(R.drawable.top_image).into(cover);
        }


        nested=findViewById(R.id.scroll);
        nested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY>100){
                    appBarLayout.setVisibility(View.VISIBLE);

                }else {
                    appBarLayout.setVisibility(View.GONE);


                }
            }
        });

        tab_filters.addTab(tab_filters.newTab().setText("Dashboard"));
        tab_filters.addTab(tab_filters.newTab().setText("Subscription"));
        tab_filters.addTab(tab_filters.newTab().setText("Reviews"));
        tab_filters.addTab(tab_filters.newTab().setText("Contact Us"));
        tab_filters.setOnTabSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_filter_id,new DashboardFragment()).commit();

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
        adapter.addFragment(new SubsCription(), "Subscription");
        adapter.addFragment(new ReviewsFragment(), "Reviews");
        adapter.addFragment(new ContactUs(), "Contact Us");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new DashboardFragment()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new SubsCription()).commit();

        } else if (tab.getPosition()==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new ReviewsFragment()).commit();


        }else if (tab.getPosition()==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new ContactUs()).commit();



        }




    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
