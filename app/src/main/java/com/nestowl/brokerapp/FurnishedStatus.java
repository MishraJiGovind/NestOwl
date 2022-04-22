package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class FurnishedStatus extends AppCompatActivity {
    ArrayList<String> arrayList;
    int defaults = R.drawable.employe_circle_rounded;
    CardView sumbt;
    int active = R.drawable.selected_background_filter;
    FrameLayout light,fan,ro,beds,wardrobe,gycer,washingmashing,stove,sofa,chinmey,dinigtabe,curtains,
            powerbackup,lift,madualarkictchen,waterRo,wifi,gymnasium,tv,fridge,microwave,ac,exhaust,gym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnished_status);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        arrayList=new ArrayList<>();
        sumbt=findViewById(R.id.Sumbit);
        sumbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList==null){
                    Toast.makeText(FurnishedStatus.this, "Nothing Selectd", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("data",arrayList);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });


        light=findViewById(R.id.lights);
        fan=findViewById(R.id.fan);
        ro=findViewById(R.id.ro);
        beds=findViewById(R.id.beds);
        wardrobe=findViewById(R.id.wardrobe);
        gycer=findViewById(R.id.gycer);
        washingmashing=findViewById(R.id.washing);
        stove=findViewById(R.id.stove);
        sofa=findViewById(R.id.sofa);
        chinmey=findViewById(R.id.chinmey);
        dinigtabe=findViewById(R.id.diningtable);
        curtains=findViewById(R.id.curtaines);
        powerbackup=findViewById(R.id.powerbackup);
        lift=findViewById(R.id.lift);
        madualarkictchen=findViewById(R.id.modularKichen);
        waterRo=findViewById(R.id.waterRo);
        wifi=findViewById(R.id.wifi);
        gymnasium=findViewById(R.id.gymasaium);
        tv=findViewById(R.id.tv);
        fridge=findViewById(R.id.fidge);
        microwave=findViewById(R.id.microwave);
        ac=findViewById(R.id.ac);
        exhaust=findViewById(R.id.exhuastfan);
        gym=findViewById(R.id.gym);

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                light.setBackgroundResource(active);
                arrayList.add("Light");
            }
        });
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fan.setBackgroundResource(active);
                arrayList.add("Fan");
            }
        });
        ro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ro.setBackgroundResource(active);
                arrayList.add("RO");
            }
        });
        beds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beds.setBackgroundResource(active);
                arrayList.add("Beds");
            }
        });
        wardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wardrobe.setBackgroundResource(active);
                arrayList.add("Wardrobe");
            }
        });
        gycer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gycer.setBackgroundResource(active);
                arrayList.add("Geyser");
            }
        });
        washingmashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                washingmashing.setBackgroundResource(active);
                arrayList.add("Washing Machine");
            }
        });
        stove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stove.setBackgroundResource(active);
                arrayList.add("Stove");
            }
        });
        sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sofa.setBackgroundResource(active);
                arrayList.add("Soda");
            }
        });
        chinmey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chinmey.setBackgroundResource(active);
                arrayList.add("Chimney");
            }
        });
        dinigtabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dinigtabe.setBackgroundResource(active);
                arrayList.add("Dining Table");
            }
        });
        curtains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curtains.setBackgroundResource(active);
                arrayList.add("Curtains");
            }
        });
        powerbackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powerbackup.setBackgroundResource(active);
                arrayList.add("Power Backup");
            }
        });
        lift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lift.setBackgroundResource(active);
                arrayList.add("Lift");
            }
        });
        madualarkictchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                madualarkictchen.setBackgroundResource(active);
                arrayList.add("Modular Kitchen");
            }
        });
        waterRo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterRo.setBackgroundResource(active);
                arrayList.add("Water Purifier");
            }
        });
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifi.setBackgroundResource(active);
                arrayList.add("Wi-Fi");
            }
        });
        gymnasium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gymnasium.setBackgroundResource(active);
                arrayList.add("Gymnasium");
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setBackgroundResource(active);
                arrayList.add("TV");
            }
        });
        fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fridge.setBackgroundResource(active);
                arrayList.add("Fridge");
            }
        });
        microwave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                microwave.setBackgroundResource(active);
                arrayList.add("Microwave");
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ac.setBackgroundResource(active);
                arrayList.add("AC");
            }
        });
        exhaust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exhaust.setBackgroundResource(active);
                arrayList.add("Exhaust Fan");
            }
        });
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gym.setBackgroundResource(active);
                arrayList.add("GYM");
            }
        });
    }
}
