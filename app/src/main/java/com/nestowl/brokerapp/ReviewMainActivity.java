package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ReviewMainActivity extends AppCompatActivity {
    LinearLayout lnr_seller,lnr_review_first,lnr_nest_pro,lnr_second,lnr_seller_buyer,lnr_third_review,lnr_builder,lnr_fourth_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_main);
        lnr_seller =findViewById(R.id.buyer_owner_lnr);
        lnr_review_first=findViewById(R.id.owner_write_review_first_s);
        lnr_second =findViewById(R.id.owner_write_review_second_s);
        lnr_builder=findViewById(R.id.builder_lnr);
        lnr_fourth_review=findViewById(R.id.owner_write_review_third_s);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        lnr_review_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ReviewMainActivity.this,ReviewActivity.class);
                startActivity(intent);
            }
        });
        lnr_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ReviewMainActivity.this,ReviewBuyerActivity.class);
                startActivity(intent);
            }
        });
      /*  lnr_third_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ReviewMainActivity.this,ReviewBuyerActivity.class);
                startActivity(intent);
            }
        });*/
        lnr_fourth_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ReviewMainActivity.this,ReviewActivity.class);
                startActivity(intent);
            }
        });



    }
}
