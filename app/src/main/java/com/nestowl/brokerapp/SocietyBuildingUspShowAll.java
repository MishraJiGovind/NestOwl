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

public class SocietyBuildingUspShowAll extends AppCompatActivity {
    String show_all_society_building_usp="";
    CardView cardView;
    Context context;
    TextView tv_shopping_centre,tv_indoor_games,tv_atm_facilities,tv_cumunity_centre,tv_swiming_pool,tv_econ_friendly,tv_security_system,tv_movie_theatre,
    tv_air_rooms,tv_structural,tv_water_supply,tv_on_site,tv_car_charging,tv_no_open,tv_natural,tv_private_gym,tv_close;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_society_building_usp_show_all);
        data=new ArrayList<>();

        tv_shopping_centre=findViewById(R.id.shopping_centre_id);
        cardView=findViewById(R.id.card_submit);
        tv_indoor_games=findViewById(R.id.inoor_games_id);
        tv_atm_facilities=findViewById(R.id.atm_facilities_id);

        tv_cumunity_centre=findViewById(R.id.cumunity_centre_id);
        tv_swiming_pool=findViewById(R.id.swiming_pool_id);
        tv_econ_friendly=findViewById(R.id.econ_fiendly_id);

        tv_security_system=findViewById(R.id.security_system_id);
        tv_movie_theatre=findViewById(R.id.movie_theatre_id);
        tv_air_rooms=findViewById(R.id.air_rooms_id);
        tv_structural=findViewById(R.id.structural_aids_id);
        tv_water_supply=findViewById(R.id.water_supply_id);

        tv_on_site=findViewById(R.id.on_site_maintenance_id);
        tv_car_charging=findViewById(R.id.car_charging_id);
        tv_no_open=findViewById(R.id.no_open_drainange_id);
        tv_natural=findViewById(R.id.natural_light_id);

        tv_private_gym=findViewById(R.id.private_gym_spa_id);
        tv_close=findViewById(R.id.close_to_bank_id);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",data);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });





        tv_shopping_centre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_shopping_centre.getText().toString());
                selected(tv_shopping_centre);
            }
        });


        tv_indoor_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_indoor_games.getText().toString());
                selected(tv_indoor_games);
            }
        });



        tv_atm_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_atm_facilities.getText().toString());
                selected(tv_atm_facilities);
            }
        });



        tv_cumunity_centre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_cumunity_centre.getText().toString());
                selected(tv_cumunity_centre);
            }
        });


        tv_swiming_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_swiming_pool.getText().toString());
                selected(tv_swiming_pool);
            }
        });




        tv_econ_friendly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_econ_friendly.getText().toString());
                selected(tv_econ_friendly);
            }
        });

        tv_security_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_security_system.getText().toString());
                selected(tv_security_system);
            }
        });


        tv_movie_theatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_movie_theatre.getText().toString());
                selected(tv_movie_theatre);
            }
        });

        tv_air_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_air_rooms.getText().toString());
                selected(tv_air_rooms);
            }
        });


        tv_structural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_structural.getText().toString());
                selected(tv_structural);
            }
        });

        tv_water_supply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_water_supply.getText().toString());
                selected(tv_water_supply);
            }
        });


        tv_on_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_on_site.getText().toString());
                selected(tv_on_site);
            }
        });


        tv_car_charging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_car_charging.getText().toString());
                selected(tv_car_charging);
            }
        });





        tv_no_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_no_open.getText().toString());
                selected(tv_no_open);
            }
        });



        tv_natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_natural.getText().toString());
                selected(tv_natural);
            }
        });



        tv_private_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_private_gym.getText().toString());
                selected(tv_private_gym);
            }
        });




        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(tv_close.getText().toString());
                selected(tv_close);
            }
        });





        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
    }
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
}
