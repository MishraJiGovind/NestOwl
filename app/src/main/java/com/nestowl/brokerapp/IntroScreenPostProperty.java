package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nestowl.AdapterClass.MyPager;

public class IntroScreenPostProperty extends AppCompatActivity {
    CardView card_select_package;
    ImageView im_back;
    MyPager myPager;
    private  ViewPager viewPager;

    private TextView tv_terms_condition;
    private ConstraintLayout rlParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen_post_property);

        llPagerDots = findViewById(R.id.id_circle_indicator);

        viewPager = findViewById(R.id.view_pager_login);
        card_select_package=findViewById(R.id.select_package_card);
        im_back=findViewById(R.id.iv33);
        setUpViewPager();
        int count = 0;
        final Handler handler = new Handler();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int current = viewPager.getCurrentItem();
                int size = viewPager.getAdapter().getCount();

                if (current < (size - 1)) {
                    viewPager.setCurrentItem(current + 1, true);

                } else {
                    viewPager.setCurrentItem(0, true);
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(task, 3000);
        im_back.setOnClickListener(new View.OnClickListener() {
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
        card_select_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IntroScreenPostProperty.this,PostPropertyModeSelect.class);
                startActivity(intent);
            }
        });
    }

    private LinearLayout llPagerDots;
    private ImageView[] ivArrayDotsPager;

    public void setUpViewPager() {
        myPager = new MyPager(this);
        viewPager.setAdapter(myPager);


        setupPagerIndidcatorDots();

        ivArrayDotsPager[0].setImageResource(R.drawable.page_indicator_selected);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.page_unselected_bg);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.page_indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[myPager.getCount()];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.page_unselected_bg);
//ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            llPagerDots.addView(ivArrayDotsPager[i]);
            llPagerDots.bringToFront();
        }
    }
}
