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
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.nestowl.utils.PrefMananger;

public class SelectOptionMode extends AppCompatActivity {
    CardView card_select;
    RadioButton first,second,third;
    ImageView imageView;
    LinearLayout publics,locality,whole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_mode);

        card_select =findViewById(R.id.post_sequence_first);
        first=findViewById(R.id.to_public);
        second=findViewById(R.id.to_locality);
        third=findViewById(R.id.whole_city);
        imageView=findViewById(R.id.iv19);
        publics=findViewById(R.id.POST_PRO_SELECTION_PUBLIC);
        locality=findViewById(R.id.POST_PRO_SELECTION_LOACLITY);
        whole=findViewById(R.id.POST_PRO_SELECTION_WHOLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        card_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.isChecked()){
                    Intent i=new Intent(SelectOptionMode.this,SelectPackageSecondPostProperty.class);
                    PrefMananger.saveString(SelectOptionMode.this,"posted","Public");
                    startActivity(i);
                } else if (second.isChecked()){
                    Intent i=new Intent(SelectOptionMode.this,SelectPackageSecondPostProperty.class);
                    PrefMananger.saveString(SelectOptionMode.this,"posted","Locality Nest Pro's and Public");
                    startActivity(i);
                }else if (third.isChecked()){
                    Intent i=new Intent(SelectOptionMode.this,SelectPackageSecondPostProperty.class);
                    PrefMananger.saveString(SelectOptionMode.this,"posted","Whole City Nest Pro's and Public");
                    startActivity(i);
                }
            }
        });
        publics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setChecked(true);
                second.setChecked(false);
                third.setChecked(false);
            }
        });
        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setChecked(false);
                second.setChecked(true);
                third.setChecked(false);
            }
        });
        whole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setChecked(true);
                second.setChecked(false);
                third.setChecked(false);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setChecked(false);
                second.setChecked(true);
                third.setChecked(false);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
}
