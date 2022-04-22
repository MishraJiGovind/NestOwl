package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

public class QueryPropertySelectedScreen extends AppCompatActivity {
    ImageView back_img;
    CardView cardView;
    RadioButton three, two,one;
    FrameLayout property_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_property_selected_screen);
        back_img=findViewById(R.id.ARTICLES_BACK);
        cardView=findViewById(R.id.card_submit);
        property_two=findViewById(R.id.property_two);
        property_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryPropertySelectedScreen.this,A2SecondQuery.class);
                startActivity(intent);
            }
        });

        three=findViewById(R.id.property_three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent=new Intent(QueryPropertySelectedScreen.this,A3SecondScreen.class);
                startActivity(intent);*/

            }
        });
        two=findViewById(R.id.properyt_by_two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent=new Intent(QueryPropertySelectedScreen.this,A2SecondQuery.class);
                startActivity(intent);*/
            }
        });
/*
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(QueryPropertySelectedScreen.this,MakeOfferCongratulationScreen.class);
                startActivity(intent);
            }
        });
*/

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
}
