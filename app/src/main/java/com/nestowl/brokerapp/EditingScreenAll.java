package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nestowl.utils.PrefMananger;

public class EditingScreenAll extends AppCompatActivity {
    ImageView img_edit;
    FrameLayout fi,f2,f3,f4,f5,f6;
    ImageView imageView;
    String id,user_id;
    TextView ids;
    boolean awaiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_screen_all);
        img_edit=findViewById(R.id.EDIT_PROFILE_BACK);
        id=getIntent().getStringExtra("id");
        user_id=getIntent().getStringExtra("user_id");
        ids=findViewById(R.id.EDIT_PRO_ID);
        awaiable=false;
        if (id!=null){
            awaiable=true;
            ids.setText("Property id:"+id);
        }
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        PrefMananger.saveString(this,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,null);
        img_edit=findViewById(R.id.editing_top);
        fi=findViewById(R.id.basic_details_first);
        f2=findViewById(R.id.basic_details_second);
        f3=findViewById(R.id.basic_details_third);
        f6=findViewById(R.id.basic_details_six);
        f4=findViewById(R.id.basic_details_fourth);
        f5=findViewById(R.id.basic_details_five);
        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,0);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,1);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,2);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });


        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,3);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });

        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,4);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
                intent.putExtra(PrefMananger.MY_DATABASE,5);
                if (awaiable){
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE,id);
                    PrefMananger.saveString(EditingScreenAll.this,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                }
                startActivity(intent);
            }
        });
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingScreenAll.this,PlanBasicActivity.class);
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
