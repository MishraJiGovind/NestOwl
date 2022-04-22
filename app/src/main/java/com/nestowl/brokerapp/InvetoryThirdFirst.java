package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InvetoryThirdFirst extends AppCompatActivity {
    ImageView back_img;
    RadioGroup radioGroup;
    RadioButton first,second,third;
    CardView cardView;
    TextView tv_near;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invetory_third_first);

        cardView=findViewById(R.id.POST_PHOTO_CONTINEUE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvetoryThirdFirst.this,InventoryThirdLast.class);
                startActivity(intent);
            }
        });

        tv_near=findViewById(R.id.show_all_nearby_places_post_property);
        tv_near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvetoryThirdFirst.this,QueryShowAll.class);
                startActivity(intent);
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        back_img=findViewById(R.id.ARTICLES_BACK);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }
}
