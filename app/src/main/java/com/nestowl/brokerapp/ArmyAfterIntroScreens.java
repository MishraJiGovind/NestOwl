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

public class ArmyAfterIntroScreens extends AppCompatActivity {
    FrameLayout army;
    CardView carc_cont;
    FrameLayout f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11;
    ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army_after_intro_screens);
        carc_cont=findViewById(R.id.continue_army);
        army=findViewById(R.id.frm_army);
        f1=findViewById(R.id.frame_one);
        f2=findViewById(R.id.frame_two);
        f3=findViewById(R.id.frame_three);
        f4=findViewById(R.id.frame_four);
        f5=findViewById(R.id.frame_five);
        f6=findViewById(R.id.frame_six);
        f7=findViewById(R.id.frame_saven);
        f8=findViewById(R.id.frame_eight);
        f9=findViewById(R.id.frame_nine);
        f10=findViewById(R.id.frame_ten);
        f11=findViewById(R.id.frame_eleven);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        army.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        carc_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });
        f10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArmyAfterIntroScreens.this,IndianHerosForm.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
}
