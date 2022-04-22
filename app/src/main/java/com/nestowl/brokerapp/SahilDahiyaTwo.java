package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SahilDahiyaTwo extends AppCompatActivity {
    TextView second_eligible;
    TextView dashboard,reviews;
    Boolean is_Residential_click = false;
    LinearLayout lnd_dashboard,lnd_reviews;
    LinearLayout first,second;
    FrameLayout frm_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahil_dahiya_two);
        dashboard=findViewById(R.id.tv_dashboard);
        reviews=findViewById(R.id.reviews);
        frm_back=findViewById(R.id.back8);
        frm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        lnd_dashboard=findViewById(R.id.lnd_dashboard);
        lnd_reviews=findViewById(R.id.lnd_reviews);
        first=findViewById(R.id.owner_write_review_first);
        second=findViewById(R.id.owner_write_review_second);

        second_eligible=findViewById(R.id.view_second_eligibility);
        second_eligible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SahilDahiyaTwo.this,SaveMoreClickSeller.class);
                startActivity(intent);
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SahilDahiyaTwo.this,ReviewBuyerActivity.class);
                startActivity(intent);
            }
        });   second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SahilDahiyaTwo.this,ReviewBuyerActivity.class);
                startActivity(intent);
            }
        });




        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnd_reviews.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnd_dashboard.setVisibility(View.VISIBLE);
                    dashboard.setTextColor(getResources().getColor(R.color.black_color));




                } else {

                    is_Residential_click = true;
                    lnd_dashboard.setVisibility(View.VISIBLE);
                    dashboard.setTextColor(getResources().getColor(R.color.brown_color));
/*
                    frame_under.setBackgroundResource(R.drawable.selected_background_filter);
*/


                }
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnd_reviews.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnd_dashboard.setVisibility(View.GONE);
                    reviews.setTextColor(getResources().getColor(R.color.black_color));

/*
                    frame_ready_to.setBackgroundResource(R.drawable.employe_circle_rounded);
*/


                } else {

                    is_Residential_click = true;
                    reviews.setTextColor(getResources().getColor(R.color.brown_color));
/*
                    frame_ready_to.setBackgroundResource(R.drawable.selected_background_filter);
*/

                    lnd_reviews.setVisibility(View.VISIBLE);

                }
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}


    }
}
