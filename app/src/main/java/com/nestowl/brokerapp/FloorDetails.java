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

import java.util.ArrayList;

public class FloorDetails extends AppCompatActivity {
    FrameLayout polished,granite,cramic,mosaic,cement,stone,viny,wood,verified,spartex,ips,curtains;
    ArrayList<String> data;
    int defaults = R.drawable.employe_circle_rounded;
    int active =  R.drawable.selected_background_filter;
    boolean ispolished,isgranite,iscramic,ismosaic,iscement,isstone,isviny,iswood,isverified,isspartex,isips,iscurtains;
    CardView sumbit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_details);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        polished=findViewById(R.id.FLORS_DETAILS_POLISHED_CONC);
        granite=findViewById(R.id.FLORS_DETAILS_GRANITE);
        cramic=findViewById(R.id.FLORS_DETAILS_CARAMIC);
        mosaic=findViewById(R.id.FLORS_DETAILS_MOSIC);
        cement=findViewById(R.id.FLORS_DETAILS_CEMENTS);
        stone=findViewById(R.id.FLORS_DETAILS_STONE);
        viny=findViewById(R.id.FLORS_DETAILS_VINY);
        wood=findViewById(R.id.FLORS_DETAILS_WOOD);
        verified=findViewById(R.id.FLORS_DETAILS_VERIED);
        spartex=findViewById(R.id.FLORS_DETAILS_SPRATEX);
        ips=findViewById(R.id.FLORS_DETAILS_IPS);
        curtains=findViewById(R.id.FLORS_DETAILS_CURTAINS);
        sumbit=findViewById(R.id.FLORS_DETAILS_SUMBIT);

        data=new ArrayList<>();
        if (!getIntent().getStringArrayListExtra("data").isEmpty()){
            data=getIntent().getStringArrayListExtra("data");
            showSelected();
        }

        polished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ispolished){
                    polished.setBackgroundResource(defaults);
                    ispolished=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Polished")){
                            data.remove(i);
                        }
                    }
                }else {
                    ispolished=true;
                    data.add("Polished");
                    polished.setBackgroundResource(active);
                }
            }
        });
        granite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isgranite){
                    granite.setBackgroundResource(defaults);
                    isgranite=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Granite")){
                            data.remove(i);
                        }
                    }
                }else {
                    isgranite=true;
                    data.add("Granite");
                    granite.setBackgroundResource(active);
                }
            }
        });
        cramic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iscramic){
                    cramic.setBackgroundResource(defaults);
                    iscramic=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Ceramic")){
                            data.remove(i);
                        }
                    }
                }else {
                    iscramic=true;
                    data.add("Ceramic");
                    cramic.setBackgroundResource(active);
                }
            }
        });
        mosaic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ismosaic){
                    mosaic.setBackgroundResource(defaults);
                    ismosaic=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Mosaic")){
                            data.remove(i);
                        }
                    }
                }else {
                    ismosaic=true;
                    data.add("Mosaic");
                    mosaic.setBackgroundResource(active);
                }
            }
        });
        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iscement){
                    cement.setBackgroundResource(defaults);
                    iscement=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Cement")){
                            data.remove(i);
                        }
                    }
                }else {
                    iscement=true;
                    data.add("Cement");
                    cement.setBackgroundResource(active);
                }
            }
        });
        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isstone){
                    stone.setBackgroundResource(defaults);
                    isstone=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Stone")){
                            data.remove(i);
                        }
                    }
                }else {
                    isstone=true;
                    data.add("Stone");
                    stone.setBackgroundResource(active);
                }
            }
        });
        viny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isviny){
                    viny.setBackgroundResource(defaults);
                    isviny=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Vinyl")){
                            data.remove(i);
                        }
                    }
                }else {
                    isviny=true;
                    data.add("Vinyl");
                    viny.setBackgroundResource(active);
                }
            }
        });
        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iswood){
                    wood.setBackgroundResource(defaults);
                    iswood=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Wood")){
                            data.remove(i);
                        }
                    }
                }else {
                    iswood=true;
                    data.add("Wood");
                    wood.setBackgroundResource(active);
                }
            }
        });
        verified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isverified){
                    verified.setBackgroundResource(defaults);
                    isverified=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Verified")){
                            data.remove(i);
                        }
                    }
                }else {
                    isverified=true;
                    data.add("Verified");
                    verified.setBackgroundResource(active);
                }
            }
        });
        spartex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isspartex){
                    spartex.setBackgroundResource(defaults);
                    isspartex=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Spartex")){
                            data.remove(i);
                        }
                    }
                }else {
                    isspartex=true;
                    data.add("Spartex");
                    spartex.setBackgroundResource(active);
                }
            }
        });
        ips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isips){
                    ips.setBackgroundResource(defaults);
                    isips=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("IPSFinish")){
                            data.remove(i);
                        }
                    }
                }else {
                    isips=true;
                    data.add("IPSFinish");
                    ips.setBackgroundResource(active);
                }
            }
        });
        curtains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iscurtains){
                    curtains.setBackgroundResource(defaults);
                    iscurtains=false;
                    for (int i=0;i<data.size();i++){
                        String value = data.get(i);
                        if (value.equals("Curtains")){
                            data.remove(i);
                        }
                    }
                }else {
                    iscurtains=true;
                    data.add("Curtains");
                    curtains.setBackgroundResource(active);
                }
            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.isEmpty()){
                    Intent intent=new Intent();
                    setResult(Activity.RESULT_CANCELED,intent);
                    finish();
                }else {
                    Intent intent=new Intent();
                    intent.putExtra("data",data);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }

            }
        });



    }

    private void showSelected() {
        for (String name : data){
            if (name.equals("Polished")){
              ispolished=true;
              polished.setBackgroundResource(active);
            }
            if (name.equals("Granite")){
                isgranite=true;
                granite.setBackgroundResource(active);
            }
            if (name.equals("Ceramic")){
                iscramic=true;
                cramic.setBackgroundResource(active);
            }
            if (name.equals("Mosaic")){
                ismosaic=true;
                mosaic.setBackgroundResource(active);
            }
            if (name.equals("Cement")){
                iscement=true;
                cement.setBackgroundResource(active);
            }
            if (name.equals("Stone")){
                isstone=true;
                stone.setBackgroundResource(active);
            }
            if (name.equals("Vinyl")){
                isviny=true;
                viny.setBackgroundResource(active);
            }
            if (name.equals("Wood")){
                iswood=true;
                wood.setBackgroundResource(active);
            }
            if (name.equals("Verified")){
                isverified=true;
                verified.setBackgroundResource(active);
            }
            if (name.equals("Spartex")){
                isspartex=true;
                spartex.setBackgroundResource(active);
            }
            if (name.equals("IPSFinish")){
                isips=true;
                ips.setBackgroundResource(active);
            }
            if (name.equals("Curtains")){
                iscurtains=true;
                curtains.setBackgroundResource(active);
            }
        }
    }
}
