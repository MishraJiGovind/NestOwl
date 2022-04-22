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

public class SelectModePostRequirement extends AppCompatActivity {
    CardView card_select;
    RadioButton first,second;
    ImageView imageView;
    LinearLayout local, wholeciy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode_post_requirement);

        card_select =findViewById(R.id.post_sequence_first);
        first=findViewById(R.id.to_public);
        second=findViewById(R.id.whole_city);
        imageView=findViewById(R.id.iv19);
        local=findViewById(R.id.public_liner);
        wholeciy=findViewById(R.id.whole_city_liner);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setChecked(true);
                second.setChecked(false);
            }
        });
        wholeciy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setChecked(false);
                second.setChecked(true);
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setChecked(true);
                second.setChecked(false);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setChecked(false);
                second.setChecked(true);
            }
        });
        card_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.isChecked()){
                    Intent i=new Intent(SelectModePostRequirement.this,PostRequirementForm.class);
                    i.putExtra("type", "Locality Nest Pro's");
                    startActivity(i);
                    finish();
                } else if (second.isChecked()){
                    Intent i=new Intent(SelectModePostRequirement.this,PostRequirementForm.class);
                    i.putExtra("type", "Whole City Nest Pro's");
                    startActivity(i);
                    finish();
                }

            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
}
