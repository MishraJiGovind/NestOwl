package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nestowl.Fragment.AmentiesFeatures;
import com.nestowl.brokerapp.databinding.ActivityShowAllAmentiesBinding;


import java.util.ArrayList;

public class ShowAllAmenties extends AppCompatActivity {
    ActivityShowAllAmentiesBinding binding;
    CardView cardView;
    Context context;
    String show_all_amenties="";
    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        show_all_amenties= AmentiesFeatures.show_all_amenties;
//        setContentView(R.layout.activity_show_all_amenties);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        list=new ArrayList<>();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_show_all_amenties);
        binding.cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",list);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        binding.lightId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.lightId);
                list.add(binding.lightId.getText().toString());
            }
        });
        binding.fanId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.fanId);
                list.add(binding.fanId.getText().toString());
            }
        });
        binding.roId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.roId);
                list.add(binding.roId.getText().toString());
            }
        });
        binding.bedsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(binding.bedsId.getText().toString());
                selected(binding.bedsId);
            }
        });
        binding.wardrobeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.wardrobeId);
                list.add(binding.wardrobeId.getText().toString());
            }
        });
        binding.gycerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.gycerId);
                list.add(binding.gycerId.getText().toString());
            }
        });
        binding.wahingMachineId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.wahingMachineId);
                list.add(binding.wahingMachineId.getText().toString());
            }
        });
        binding.stoveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.stoveId);
                list.add(binding.stoveId.getText().toString());
            }
        });
        binding.sofaId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.sofaId);
                list.add(binding.sofaId.getText().toString());
            }
        });
        binding.chimenyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.chimenyId);
                list.add(binding.chimenyId.getText().toString());
            }
        });
        binding.diningTableId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.diningTableId);
                list.add(binding.diningTableId.getText().toString());
            }
        });
        binding.curtainsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.curtainsId);
                list.add(binding.curtainsId.getText().toString());
            }
        });
        binding.powerBackupId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.powerBackupId);
                list.add(binding.powerBackupId.getText().toString());
            }
        });
        binding.liftId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.liftId);
                list.add(binding.liftId.getText().toString());
            }
        });
        binding.modularKitchenId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.modularKitchenId);
                list.add(binding.modularKitchenId.getText().toString());
            }
        });
        binding.waterPurifierId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.waterPurifierId);
                list.add(binding.waterPurifierId.getText().toString());
            }

        });
        binding.wifiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.wifiId);
                list.add(binding.wifiId.getText().toString());
            }
        });
        binding.gymanasiumId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(binding.gymanasiumId);
                list.add(binding.gymanasiumId.getText().toString());
            }
        });
        binding.tvId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(binding.tvId.getText().toString());
                selected(binding.tvId);
            }
        });

    }

    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }

    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
}