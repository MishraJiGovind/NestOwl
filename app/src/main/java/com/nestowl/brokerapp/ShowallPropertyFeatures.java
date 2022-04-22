package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowallPropertyFeatures extends AppCompatActivity {
    String show_all_property_features="";
    CardView cardView;
    Context context;
    TextView tv_modular_kitchen,tv_electronics,tv_security_alarm,tv_water_storage,tv_piped_games,tv_private_garden,tv_air_condition,tv_conrner_property,
    tv_main_road_property;
    ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_showall_property_features);
        cardView=findViewById(R.id.card_submit);

        tv_modular_kitchen=findViewById(R.id.modular_kitchen_idd);
        tv_electronics=findViewById(R.id.electronics_idd);
        tv_security_alarm=findViewById(R.id.security_alarm_id);
        tv_water_storage=findViewById(R.id.water_storage_id);
        tv_piped_games=findViewById(R.id.piped_gas_id);
        tv_private_garden=findViewById(R.id.private_garden_id);
        tv_air_condition=findViewById(R.id.air_condition_id);
        tv_conrner_property=findViewById(R.id.corner_property_id);
        tv_main_road_property=findViewById(R.id.main_property_id);
        data=new ArrayList<>();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",data);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        tv_modular_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_modular_kitchen.getText().toString());
                selected(tv_modular_kitchen);
            }
        });


        tv_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_electronics.getText().toString());
                selected(tv_electronics);
            }
        });

        tv_security_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_security_alarm.getText().toString());
                selected(tv_security_alarm);
            }
        });


        tv_water_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_water_storage.getText().toString());
                selected(tv_water_storage);
            }
        });





        tv_piped_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_piped_games.getText().toString());
                selected(tv_piped_games);
            }
        });


        tv_private_garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_private_garden.getText().toString());
                selected(tv_private_garden);
            }
        });

        tv_air_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_air_condition.getText().toString());
                selected(tv_air_condition);
            }
        });




        tv_conrner_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_conrner_property.getText().toString());
                selected(tv_conrner_property);
            }
        });

        tv_main_road_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_main_road_property.getText().toString());
                selected(tv_main_road_property);
            }
        });



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
}